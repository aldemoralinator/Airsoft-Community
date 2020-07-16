package com.aldem.simpleapp.repository;

import com.aldem.simpleapp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> 
{    
    public Project findByName(String name);
    public Project findBySlug(String slug);
    public boolean existsByName(String name);
    public boolean existsBySlug(String slug);
}
