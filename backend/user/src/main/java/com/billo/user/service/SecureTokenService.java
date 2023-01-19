package com.billo.user.service;

import com.billo.user.model.SecureToken;
import com.billo.user.repository.SecureTokenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SecureTokenService {

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");

    @Autowired
    private SecureTokenRepository secureTokenRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

//    @Value("")
//    private int tokenValidityInSeconds;

    public SecureToken createSecureToken(){
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()), US_ASCII); // this is a sample, you can adapt as per your security need
        SecureToken secureToken = new SecureToken();
        secureToken.setToken(tokenValue);
//        secureToken.setExpireAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
        this.saveSecureToken(secureToken);
        return secureToken;
    }

    public void saveSecureToken(SecureToken token) {
        secureTokenRepository.save(token);
    }

    public Optional<SecureToken> getToken(String token) {
        return secureTokenRepository.findByToken(token);
    }

    public void removeToken(SecureToken token) {
        secureTokenRepository.delete(token);
    }

    public int setConfirmedAt(String token) {
        return secureTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    @Transactional
    public String confirmToken(String token) {
        SecureToken confirmationToken = getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpireAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        setConfirmedAt(token);
        userDetailsService.enableAppUser(
                confirmationToken.getUser().getUsername() );
        return "confirmed";
    }
}
