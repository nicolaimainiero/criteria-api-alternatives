package de.sidion.blog.criteria_api_alternatives;

import com.querydsl.jpa.impl.JPAQueryFactory;
import de.sidion.blog.criteria_api_alternatives.model.Customer;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder;
import de.sidion.blog.criteria_api_alternatives.model.QCustomer;
import de.sidion.blog.criteria_api_alternatives.model.QPurchaseOrder;

import javax.persistence.EntityManager;

public class QueryDSLExample implements Example {

    private final EntityManager em;

    public QueryDSLExample(EntityManager em) {
        this.em = em;
    }

    @Override
    public Customer findByName(String firstName, String lastName) {
        QCustomer customer = QCustomer.customer;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        return jpaQueryFactory.selectFrom(customer)
                .where(customer.firstName.eq(firstName).and(customer.lastName.eq(lastName)))
                .fetchOne();
    }

    @Override
    public PurchaseOrder findOrderOfCustomer(String lastName) {
        QCustomer customer = QCustomer.customer;
        QPurchaseOrder purchaseOrder = QPurchaseOrder.purchaseOrder;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        return jpaQueryFactory.selectFrom(purchaseOrder)
                .innerJoin(purchaseOrder.customer, customer)
                .where(customer.lastName.eq(lastName))
                .fetchOne();
    }
}