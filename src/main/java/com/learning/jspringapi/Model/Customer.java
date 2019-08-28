package com.learning.jspringapi.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Table(name = "customers")
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerid")
    public Long customerid;

    @Column(name = "mnemonic")
    public String mnemonic;

    @Column(name = "customername")
    public String customername;

    @Column(name = "phonenumber")
    public Long phonenumber;

    @Column(name = "nationality")
    public String nationality;

    @Column(name = "gender")
    public String gender;

    @Column(name = "address")
    public String address;

    @Column(name = "kinname")
    public String kinname;

    @Column(name = "kinaddress")
    public String kinaddress;

    @OneToOne
    @JoinColumn(name = "industry_id")
    private Industries industries;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Industries getIndustries() {
        return industries;
    }

    public void setIndustries(Industries industries) {
        this.industries = industries;
    }

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKinname() {
        return kinname;
    }

    public void setKinname(String kinname) {
        this.kinname = kinname;
    }

    public String getKinaddress() {
        return kinaddress;
    }

    public void setKinaddress(String kinaddress) {
        this.kinaddress = kinaddress;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }



}
