package com.aldem.simpleapp.repository;
 
import com.aldem.simpleapp.model.EventSubscription; 
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventSubscriptionRepository extends JpaRepository<EventSubscription, Long> 
{
    public EventSubscription findByUserIdAndEventId(Long userId, Long eventId);
    public boolean existsByUserIdAndEventId(Long userId, Long eventId);
    public List<EventSubscription> findByUserId(Long userId);
    public List<EventSubscription> findByEventId(Long eventId);
   
}
