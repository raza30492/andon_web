/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.dto;

/**
 *
 * @author Md Zahid Raza
 */
public class IssueDto {
    private Long id;
    private Integer lineNo;
    private Integer secId;
    private Integer probId;
    private char critical;
    private int operatorNo;
    private String description;  
    private Long raisedAt;
    private Integer raisedBy;
    private Long ackAt;
    private Integer ackBy;
    private Long fixAt;
    private Integer fixBy;
    private Short processingAt;
    private char status;
    private Short seekHelp;
   
    public IssueDto(){}

    public IssueDto(Integer lineNo, Integer secId, Integer probId, char critical, int operatorNo, String description, Integer raisedBy) {
        this.lineNo = lineNo;
        this.secId = secId;
        this.probId = probId;
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

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        this.secId = secId;
    }

    public Integer getProbId() {
        return probId;
    }

    public void setProbId(Integer probId) {
        this.probId = probId;
    }

    public char getCritical() {
        return critical;
    }

    public void setCritical(char critical) {
        this.critical = critical;
    }

    public int getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(int operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRaisedAt() {
        return raisedAt;
    }

    public void setRaisedAt(Long raisedAt) {
        this.raisedAt = raisedAt;
    }

    public Integer getRaisedBy() {
        return raisedBy;
    }

    public void setRaisedBy(Integer raisedBy) {
        this.raisedBy = raisedBy;
    }

    public Long getAckAt() {
        return ackAt;
    }

    public void setAckAt(Long ackAt) {
        this.ackAt = ackAt;
    }

    public Integer getAckBy() {
        return ackBy;
    }

    public void setAckBy(Integer ackBy) {
        this.ackBy = ackBy;
    }

    public Long getFixAt() {
        return fixAt;
    }

    public void setFixAt(Long fixAt) {
        this.fixAt = fixAt;
    }

    public Integer getFixBy() {
        return fixBy;
    }

    public void setFixBy(Integer fixBy) {
        this.fixBy = fixBy;
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
    public String toString() {
        return "IssueDto{" + "id=" + id + ", lineNo=" + lineNo + ", secId=" + secId + ", probId=" + probId + ", critical=" + critical + ", operatorNo=" + operatorNo + ", description=" + description + ", raisedAt=" + raisedAt + ", raisedBy=" + raisedBy + ", ackAt=" + ackAt + ", ackby=" + ackBy + ", fixAt=" + fixAt + ", fixBy=" + fixBy + ", processingAt=" + processingAt + ", status=" + status + ", seekHelp=" + seekHelp + '}';
    }
   
}
