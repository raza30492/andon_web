/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Md Zahid Raza
 */
@Entity
@Table(name = "USER")
public class User implements Serializable{
   
   @Id
   @Column(name = "EMP_ID",nullable = false,unique = true)
   private Integer id;
   
   @Column(name = "NAME",nullable = false)
   private String name;
   
   @Column(name = "EMAIL")
   private String email;
   
   @Column(name = "PASSWORD",nullable = false)
   private String password;
   
   @Column(name = "MOBILE",nullable = false)
   private String mobile;

   @Column(name = "INSTANCE_TOKEN")
   private String instanceToken;
   
   @ManyToOne(optional = false)
   @JoinColumn(name = "DESGN_ID")
   private Designation designation;
   
   public User(){}

   public User(Integer id, String name, String passwd, String mobile) {
      this.id = id;
      this.name = name;
      this.password = passwd;
      this.mobile = mobile;
   }
   //For Testing instantiation
    public User(Integer id, String name, String email, String passwd, String mobile, Designation designation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = passwd;
        this.mobile = mobile;
        this.designation = designation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return password;
    }

    public void setPasswd(String passwd) {
        this.password = passwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInstanceToken() {
        return instanceToken;
    }

    public void setInstanceToken(String instanceToken) {
        this.instanceToken = instanceToken;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", passwd=" + password + ", mobile=" + mobile + ", instanceToken=" + instanceToken + ", designation=" + designation + '}';
    }
    
   
}
