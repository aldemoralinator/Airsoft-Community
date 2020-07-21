package com.aldem.simpleapp.repository;

import com.aldem.simpleapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> 
{    
    public Event findByName(String name);
    public Event findBySlug(String slug);
    public boolean existsByName(String name);
    public boolean existsBySlug(String slug);
}
