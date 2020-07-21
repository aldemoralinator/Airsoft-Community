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
    @Getter @Setter private Long id;
    
    @Column(unique = true)
    @Getter @Setter private String openId;

    @Column(unique = true, length = 30)
    @Getter @Setter private String username;

    @Column(unique = true, length = 255)
    @Getter @Setter private String email;

    @Getter @Setter private String introduction;
    
    @OneToMany(mappedBy = "creator")
    @Getter @Setter private Set<Event> createdEvents;

    @OneToMany(mappedBy = "user")
    @Getter @Setter private Set<EventSubscription> eventSubscriptions;

    public User() {
    }

    public User(String openId, String username, String email) {
        this.openId = openId;
        this.username = username;
        this.email = email;
    }
    
}
