package de.sidion.blog.criteria_api_alternatives.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    private String packaging;

    @Column(name = "is_discontinued")
    private boolean discontinued;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", supplier=" + supplier +
                ", unitPrice=" + unitPrice +
                ", packaging='" + packaging + '\'' +
                ", discontinued=" + discontinued +
                '}';
    }
}