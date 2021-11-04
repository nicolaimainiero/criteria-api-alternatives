package de.sidion.blog.criteria_api_alternatives;

import de.sidion.blog.criteria_api_alternatives.model.Customer;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.jooq.generated.Tables.CUSTOMER;
import static org.jooq.generated.Tables.PURCHASE_ORDER;


public class JOOQExample implements Example {

    private final EntityManager em;

    public JOOQExample(EntityManager em) {
        this.em = em;
    }

    public Customer findByName(String firstName, String lastName) {
        SQLDialect configuration = SQLDialect.POSTGRES;
        SelectConditionStep<Record> query = DSL.using(configuration)
                .select()
                .from(CUSTOMER)
                .where(CUSTOMER.FIRST_NAME.eq(firstName).and(CUSTOMER.LAST_NAME.eq(lastName)));
        return nativeQuery(em, query, Customer.class);
    }

    public PurchaseOrder findOrderOfCustomer(String lastName) {
        SQLDialect configuration = SQLDialect.POSTGRES;
        SelectConditionStep<Record> query = DSL.using(configuration)
                .select()
                .from(PURCHASE_ORDER)
                .join(CUSTOMER).on(PURCHASE_ORDER.CUSTOMER_ID.eq(CUSTOMER.ID))
                .where(CUSTOMER.LAST_NAME.eq(lastName));
        return nativeQuery(em, query, PurchaseOrder.class);
    }

    public static <E> E nativeQuery(EntityManager em, org.jooq.Query query, Class<E> type) {

        // Extract the SQL statement from the jOOQ query:
        Query result = em.createNativeQuery(query.getSQL(), type);

        // Extract the bind values from the jOOQ query:
        List<Object> values = query.getBindValues();
        for (int i = 0; i < values.size(); i++) {
            result.setParameter(i + 1, values.get(i));
        }

        // There's an unsafe cast here, but we can be sure that we'll get the right type from JPA
        return (E) result.getSingleResult();
    }
}