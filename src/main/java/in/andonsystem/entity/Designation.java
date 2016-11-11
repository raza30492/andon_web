/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Md Zahid Raza
 */
@Entity
@Table(name = "DESIGNATION")
public class Designation implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DESGN_ID",nullable = false,unique = true)
    private int id;

    @Column(name = "NAME",nullable = false,unique = true)
    private String name;

    @Column(name = "LEVEL")
    private int level;

    @OneToMany(mappedBy = "designation",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "designations")
    private Set<Problem> problems = new HashSet<>();

    @ManyToMany(mappedBy = "designations")
    private Set<Line> lines = new HashSet<>();

    public Designation(){}

    public Designation(int id) {
       this.id = id;
    }

    public Designation(String name, int level) {
       this.name = name;
       this.level = level;
    }
    //For Testing instantiation
    public Designation(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public Set<Line> getLines() {
        return lines;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final Designation other = (Designation) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Designation{" + "id=" + id + ", name=" + name + ", level=" + level + '}';
    }

}
