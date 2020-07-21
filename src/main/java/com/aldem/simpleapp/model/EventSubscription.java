package com.aldem.simpleapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "event_user")
@ToString @EqualsAndHashCode
public class EventSubscription 
{
    @Id
    @GeneratedValue
    @Getter private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter private User user;
    
    @ManyToOne
    @JoinColumn(name = "event_id")
    @Getter @Setter private Event event;
    
    @Getter @Setter private boolean isUserConfirm;
    
    @Getter @Setter private boolean isManagerConfirm;

    public EventSubscription() {
    }

    public EventSubscription(
        User user, 
        Event event, 
        boolean isUserConfirm,
        boolean isManagerConfirm
    ) {
        this.user = user;
        this.event = event;
        this.isUserConfirm = isUserConfirm;
        this.isManagerConfirm = isManagerConfirm;
    }

}
