package com.aldem.simpleapp.repository;

import com.aldem.simpleapp.model.Project;
import com.aldem.simpleapp.model.Subscription;
import com.aldem.simpleapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    public Subscription findByUserIdAndProjectId(Long userId, Long projectId);
    public boolean existsByUserIdAndProjectId(Long userId, Long projectId);
}
