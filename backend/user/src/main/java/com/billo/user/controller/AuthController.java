package com.billo.user.controller;


import com.billo.clients.models.Message;
import com.billo.user.jwt.JwtConfig;
import com.billo.user.model.AppRole;
import com.billo.user.model.AppUser;
import com.billo.user.model.ERole;
import com.billo.user.model.SecureToken;
import com.billo.user.payload.request.LoginRequest;
import com.billo.user.payload.request.SignUpRequest;
import com.billo.user.payload.response.JwtResponse;
import com.billo.user.repository.RoleRepository;
import com.billo.user.repository.UserRepository;
import com.billo.user.service.SecureTokenService;
import com.billo.user.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/auth",consumes= MediaType.APPLICATION_JSON_VALUE,
        produces=MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    SecureTokenService service;

    @PostMapping("/signin")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtConfig.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
        //TODO: For Provider, if the account isn't enabled, then a message should be displayed to inform him
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("RECEIVED:" + signUpRequest);
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already in use!");
        }

//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity.badRequest().body("Email is already in use");
//        }

        AppUser user = AppUser
                .builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .phone(signUpRequest.getPhone())
                .accountVerified(false)
                .build();

        Set<String> setRoles = signUpRequest.getRole();
        Set<AppRole> roles = new HashSet<>();

        Message message = new Message();
        if (setRoles == null) {
            AppRole userRole = roleRepository.findByName(ERole.ROLE_CONSUMER)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(userRole);

        } else {
            setRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        AppRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "provider":
                        AppRole modRole = roleRepository.findByName(ERole.ROLE_PROVIDER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        AppRole userRole = roleRepository.findByName(ERole.ROLE_CONSUMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
//        org.springframework.messaging.Message<Message> message1 = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC,"user_topics")
//                .build();

        if (!user.getRoles().contains(roleRepository.findByName(ERole.ROLE_PROVIDER).get())) {

            String token = UUID.randomUUID().toString();
            SecureToken secureToken = new SecureToken();
            secureToken.setToken(token);
            secureToken.setLocalDateTime(LocalDateTime.now());
            secureToken.setExpireAt(LocalDateTime.now().plusMinutes(15));
            secureToken.setUser(user);

            service.saveSecureToken(secureToken);

            String link = "http://localhost:8070/api/auth/confirm?token=" + token;

            message.setToEmail(user.getEmail());
            message.setToPhone(user.getPhone());
            message.setUsername(user.getUsername());
            message.setMessage("Welcome to Billo Application. This is a test message");
            message.setToken(link);
            kafkaTemplate.send("user_topics2",message);
        }




        //TODO: Must find a way to add providers. Providers shouldn't have access to their account, until the admin approved their request. Make the account enabled when the admin approves
        //TODO: Send an email, that will enable the account for the ROLE_CONSUMER, the ROLE_PROVIDER, it must be approved by an admin
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return service.confirmToken(token);
    }

//    @GetMapping("/verify")
//    public String verifyCustomer(@RequestParam(required = false) String token){
//
//        try {
//            verifyUser(token);
//        } catch (Exception e) {
//
//        }
//    }
//
//    public boolean verifyUser(String token) {
//        SecureToken secureToken = service.findByToken(token);
//        AppUser user = userRepository.getOne(secureToken.getUser().getId());
//        if (Objects.isNull(user)) {
//            return false;
//        }
//        user.setAccountVerified(true);
//        userRepository.save(user); // let’s same user details
//
//        // we don’t need invalid password now
//        service.removeToken(secureToken);
//        return true;
//    }

}
