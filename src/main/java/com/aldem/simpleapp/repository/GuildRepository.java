package com.aldem.simpleapp.repository;

import com.aldem.simpleapp.model.Guild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuildRepository extends JpaRepository<Guild, Long> 
{    
    public Guild findByName(String name);
    public Guild findBySlug(String slug);
    public boolean existsByName(String name);
    public boolean existsBySlug(String slug);
}
