package com.aldem.simpleapp.repository;
 
import com.aldem.simpleapp.model.Subscription; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> 
{
    public Subscription findByUserIdAndGuildId(Long userId, Long guildId);
    public boolean existsByUserIdAndGuildId(Long userId, Long guildId);
}
