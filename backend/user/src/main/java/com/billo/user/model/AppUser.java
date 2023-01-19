package com.billo.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean accountVerified = false;
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name ="role_id"))
    private Set<AppRole> roles = new HashSet<>();

//    @OneToMany(mappedBy = "user")
//    private Set tokens;


    @OneToMany
    @JsonManagedReference
    private List<Order> orders;

    @OneToMany
    @JsonManagedReference
    private List<Invoice> invoices;

//    @ManyToMany(mappedBy = "subscribedCustomers", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<Order> subscribedOrders = new HashSet<>();
}
