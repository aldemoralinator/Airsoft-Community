package com.aldem.simpleapp.model;
 
import javax.persistence.*; 
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "guild")
@ToString @EqualsAndHashCode
public class Guild
{
    @Id
    @GeneratedValue
    @Getter private Long id;

    @Column(unique = true)
    @Getter @Setter private String slug;

    @Column(length = 50) 
    @Getter @Setter private String name;

    @Getter @Setter private String description;
    
    @OneToMany(mappedBy = "guild") 
    @Getter @Setter private Set<Subscription> subscriptions;

    public Guild() {
    }

    public Guild(String name, String description) { 
        this.name = name;
        if (description != null) this.description = description;
    }

}
