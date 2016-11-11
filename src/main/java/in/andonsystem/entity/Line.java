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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Md Zahid Raza
 */
@Entity
@Table(name = "LINE")
public class Line implements Serializable{

    @Id
    @Column(name = "LINE_NO",nullable = false,unique = true)
    private Integer lineNo;

    @ManyToMany
    @JoinTable(name = "DESGN_LINE",
            joinColumns = @JoinColumn(name = "LINE_NO"),
            inverseJoinColumns = @JoinColumn(name = "DESGN_ID") 
    )
    private Set<Designation> designations = new HashSet<>();

    public Line(){}
    //For Instantiation while testing
    public Line(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Set<Designation> getDesignations() {
        return designations;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.lineNo);
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
        final Line other = (Line) obj;
        return Objects.equals(lineNo, other.lineNo);
    }

    @Override
    public String toString() {
        return "Line{" + "lineNo=" + lineNo + '}';
    }
 
}
