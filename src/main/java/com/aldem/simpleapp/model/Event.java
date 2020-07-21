package com.aldem.simpleapp.model;
 
import javax.persistence.*; 
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "event")
@ToString @EqualsAndHashCode
public class Event
{    
    @Id
    @GeneratedValue
    @Getter private Long id;

    @Column(unique = true)
    @Getter @Setter private String slug;

    @Column(length = 50) 
    @Getter @Setter private String name;
    
    @Getter @Setter private String location;

    @Getter @Setter private String description;
    
    @ManyToOne
    @JoinColumn(name = "creator_id")
    @Getter @Setter private User creator;
    
    @OneToMany(mappedBy = "event") 
    @Getter @Setter private Set<EventSubscription> eventSubscriptions;
    
    public Event() {
    }

    public Event(
        String name, 
        String location,  
        String description,
        User creator
    ) {
        this.name = name;
        this.location = location; 
        this.description = description; 
        this.creator = creator;
    }
}
