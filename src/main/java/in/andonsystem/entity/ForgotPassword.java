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
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "FORGOT_PASSWORD")
public class ForgotPassword implements Serializable{
    
    @Id
    @Column(name = "EMP_ID",nullable = false,unique = true)
    private Integer empId;
    
    @Column(name = "OTP",nullable = false)
    private Integer otp;
    
    @Column(name = "TIME",nullable = false)
    private Date time;
    
    public ForgotPassword(){}

    public ForgotPassword(Integer empId, Integer otp, Date time) {
        this.empId = empId;
        this.otp = otp;
        this.time = time;
    }
    
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ForgotPassword{" + "empId=" + empId + ", otp=" + otp + ", time=" + time + '}';
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
        final ForgotPassword other = (ForgotPassword) obj;
        return Objects.equals(this.empId, other.empId);
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

}
