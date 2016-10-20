/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cefisi.modeles;

/**
 *
 * @author Vladimir
 */
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion")
    private Long id;

    @Column(name = "nom")
    private String name;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
    private Set<Projet> projects = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "membre_promotion",
            joinColumns = @JoinColumn(name = "id_promotion"),
            inverseJoinColumns = @JoinColumn(name = "id_personne")
    )
    private Set<Personne> etudiants = new HashSet<>();

    public Promotion() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Projet> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projet> projects) {
        this.projects = projects;
    }

    public Set<Personne> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Set<Personne> etudiants) {
        this.etudiants = etudiants;
    }
}
