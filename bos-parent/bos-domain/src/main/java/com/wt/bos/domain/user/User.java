package com.wt.bos.domain.user;
// Generated 2016-12-30 15:02:16 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wt.bos.domain.qp.NoticeBill;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="t_user"
    ,catalog="mavenbos"
)
public class User  implements java.io.Serializable {


     private Integer id;
     private Date birthday;
     private String gender;
     private String password;
     private String remark;
     private Integer salary;
     private String station;
     private String telephone;
     private String username;
     private Set<NoticeBill> noticeBills = new HashSet<NoticeBill>(0);

    public User() {
    }

	
    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }
    public User(Date birthday, String gender, String password, String remark, Integer salary, String station, String telephone, String username, Set<NoticeBill> noticeBills) {
       this.birthday = birthday;
       this.gender = gender;
       this.password = password;
       this.remark = remark;
       this.salary = salary;
       this.station = station;
       this.telephone = telephone;
       this.username = username;
       this.noticeBills = noticeBills;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDAY", length=0)
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="GENDER", length=10)
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @Column(name="PASSWORD", nullable=false, length=32)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="REMARK")
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="SALARY")
    public Integer getSalary() {
        return this.salary;
    }
    
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    
    @Column(name="STATION", length=40)
    public String getStation() {
        return this.station;
    }
    
    public void setStation(String station) {
        this.station = station;
    }
    
    @Column(name="TELEPHONE", length=11)
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="USERNAME", nullable=false, length=20)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
    public Set<NoticeBill> getNoticeBills() {
        return this.noticeBills;
    }
    
    public void setNoticeBills(Set<NoticeBill> noticeBills) {
        this.noticeBills = noticeBills;
    }




}

