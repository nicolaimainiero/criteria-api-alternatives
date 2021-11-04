package de.sidion.blog.criteria_api_alternatives.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class PurchaseOrderItem {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseOrder order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    private int quantity;

    @Override
    public String toString() {
        return "PurchaseOrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}