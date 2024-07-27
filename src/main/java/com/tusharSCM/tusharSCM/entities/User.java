package com.tusharSCM.tusharSCM.entities;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "user")
@Table(name="users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    // Basic Information

    // For Primary Key we will use @Id
    @Id
    private String userId;

    // If we want to customize we will use @Column and then the constraints 
    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(length = 10000)
    @Lob
    private String about;

    @Column(length = 10000)
    @Lob
    private String profilePic;

    private String phoneNumber;

    // Validation
    @Builder.Default
    private boolean enabled = false;

    @Builder.Default
    private boolean emailVerified = false;

    @Builder.Default
    private boolean phoneVerified = false;

    // Providers
    @Builder.Default
    private Providers provider = Providers.SELF;

    private String providerUserId;

    //Add more information if needed

    // Contacts
    // Mapping Relationship
    // One to many show the relationship between the user and contact
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY ,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

}
