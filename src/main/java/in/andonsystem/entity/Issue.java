/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Md Zahid Raza
 */
@Entity
@Table(name = "ISSUE")
public class Issue implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false,unique = true)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "LINE_NO")
    private Line line;

    @ManyToOne(optional = false)
    @JoinColumn(name = "SEC_ID")
    private Section section;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROB_ID")
    private Problem problem;

    @Column(name = "CRITICAL")
    private char critical;

    @Column(name = "OPERATOR_NO")
    private Integer operatorNo;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RAISED_AT",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date raisedAt;

    @Column(name = "RAISED_BY",nullable = false)
    private Integer raisedBy;

    @Column(name = "ACK_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ackAt;

    @Column(name = "ACK_BY")
    private Integer ackBy;

    @Column(name = "FIX_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fixAt;

    @Column(name = "FIX_BY")
    private Integer fixBy;

    @Column(name = "MOD_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modAt;

    @Column(name = "PROCESSING_AT")
    private Short processingAt;     //Level processing at: Initial(1)

    @Column(name = "STATUS")
    private char status;  //Whether Open or Closed. Values['O'/'C']

    @Column(name = "SEEK_HELP")
    private Short seekHelp; //User level which has seeked help. Values: null = No one, 1 = Level 1, 2 = Level 2

    public Issue(){}

    //For Testing instantiation
    public Issue(Line line, Section section, Problem problem, char critical, Integer operatorNo, String description, Integer raisedBy) {
        this.line = line;
        this.section = section;
        this.problem = problem;
        this.critical = critical;
        this.operatorNo = operatorNo;
        this.description = description;
        this.raisedBy = raisedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public char getCritical() {
        return critical;
    }

    public void setCritical(char critical) {
        this.critical = critical;
    }

    public Integer getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(Integer operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRaisedAt() {
        return raisedAt;
    }

    public void setRaisedAt(Date raisedAt) {
        this.raisedAt = raisedAt;
    }

    public Integer getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(Integer raisedBy) {
        this.raisedBy = raisedBy;
    }

    public Date getAckAt() {
        return ackAt;
    }

    public void setAckAt(Date ackAt) {
        this.ackAt = ackAt;
    }

    public Integer getAckBy() {
        return ackBy;
    }

    public void setAckBy(Integer ackBy) {
        this.ackBy = ackBy;
    }

    public Date getFixAt() {
        return fixAt;
    }

    public void setFixAt(Date fixAt) {
        this.fixAt = fixAt;
    }

    public Integer getFixBy() {
        return fixBy;
    }

    public void setFixBy(Integer fixBy) {
        this.fixBy = fixBy;
    }

    public Date getModAt() {
        return modAt;
    }

    public void setModAt(Date modAt) {
        this.modAt = modAt;
    }

    public Short getProcessingAt() {
        return processingAt;
    }

    public void setProcessingAt(Short processingAt) {
        this.processingAt = processingAt;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Short getSeekHelp() {
        return seekHelp;
    }

    public void setSeekHelp(Short seekHelp) {
        this.seekHelp = seekHelp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Issue other = (Issue) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Issue{" + "id=" + id + ", line=" + line + ", section=" + section + ", problem=" + problem + ", critical=" + critical + ", operatorNo=" + operatorNo + ", description=" + description + ", raisedAt=" + raisedAt + ", raisedBy=" + raisedBy + ", ackAt=" + ackAt + ", ackBy=" + ackBy + ", fixAt=" + fixAt + ", fixBy=" + fixBy + ", modAt=" + modAt + ", processingAt=" + processingAt + ", status=" + status + ", seekHelp=" + seekHelp + '}';
    }
    
}
