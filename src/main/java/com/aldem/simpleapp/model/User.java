package com.aldem.simpleapp.model;

import javax.persistence.*; 
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@EqualsAndHashCode @ToString
public class User
{
    @Id
    @GeneratedValue
    @Getter private Long id;

    @Column(unique = true, length = 30)
    @Getter @Setter private String username;

    @Column(unique = true, length = 255)
    @Getter @Setter private String email;

    @Getter @Setter private String introduction;

    @OneToMany(mappedBy = "user")
    @Getter @Setter private Set<Subscription> subscriptions;

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    
}
