package com.learning.jspringapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Table(name = "account_categories")
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class AccountCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accountcategoryid")
    public Long accountcategoryid;

    @Column(name = "accountcategoryname")
    public String accountcategoryname;

    public Long getAccountcategoryid() {
        return accountcategoryid;
    }

    public void setAccountcategoryid(Long accountcategoryid) {
        this.accountcategoryid = accountcategoryid;
    }

    public String getAccountcategoryname() {
        return accountcategoryname;
    }

    public void setAccountcategoryname(String accountcategoryname) {
        this.accountcategoryname = accountcategoryname;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    @Column(name = "amount")
    public Long amount;


}
