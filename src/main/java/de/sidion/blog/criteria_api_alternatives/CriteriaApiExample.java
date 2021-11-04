package de.sidion.blog.criteria_api_alternatives;

import de.sidion.blog.criteria_api_alternatives.model.Customer;
import de.sidion.blog.criteria_api_alternatives.model.Customer_;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;


public class CriteriaApiExample implements Example {

    private final EntityManager em;

    public CriteriaApiExample(EntityManager em) {
        this.em = em;
    }

    public Customer findByName(String lastName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = cq.from(Customer.class);
        CriteriaQuery<Customer> customerCriteriaQuery = cq
                .select(customerRoot)
                .where(cb.equal(customerRoot.get(Customer_.LAST_NAME), lastName));
        return em.createQuery(customerCriteriaQuery).getSingleResult();
    }

    public PurchaseOrder findOrderOfCustomer(String lastName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PurchaseOrder> query = cb.createQuery(PurchaseOrder.class);
        Root<PurchaseOrder> order = query.from(PurchaseOrder.class);
        Join<PurchaseOrder, Customer> customer = order.join(PurchaseOrder_.CUSTOMER);
        query.select(order).where(cb.equal(customer.get(Customer_.LAST_NAME), lastName));
        return em.createQuery(query).getSingleResult();
    }
}