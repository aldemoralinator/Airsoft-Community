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
@Table(name = "guild_user")
@ToString @EqualsAndHashCode
public class Subscription 
{
    @Id
    @GeneratedValue
    @Getter private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter private User user;
    
    @ManyToOne
    @JoinColumn(name = "guild_id")
    @Getter @Setter private Guild guild;
    
    @Getter @Setter private boolean isPending;

    public Subscription() {
    }

    public Subscription(User user, Guild guild, boolean isPending) {
        this.user = user;
        this.guild = guild;
        this.isPending = isPending;
    }

}
