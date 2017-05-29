package com.purchase.manager.entity;
import javax.persistence.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table
public class CostsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date date;
    @Column
    private BigDecimal sum;
    @Column
    private String currency;
    @Column
    private String product;

    public CostsEntity() {
    }

    public CostsEntity(Long id, Date date, BigDecimal sum, String currency, String product) {
        this.id=id;
        this.date = date;
        this.sum = sum;
        this.currency = currency;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CostsEntity{" +
                "id="+id+
                "date=" + date +
                ", sum=" + sum +
                ", currency='" + currency + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}
