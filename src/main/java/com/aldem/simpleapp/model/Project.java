package com.aldem.simpleapp.model;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String slug;

    @Column(length = 50)
    private String name;

    private String description;
    
    @OneToMany(mappedBy = "project")
    private Set<Subscription> subscriptions;

    public Project() {
    }

    public Project(String name, String description) { 
        this.name = name;
        if (description != null) this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.slug, other.slug)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.subscriptions, other.subscriptions)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", slug=" + slug + ", name=" + name + ", description=" + description + ", subscriptions=" + subscriptions + '}';
    }

    
}
