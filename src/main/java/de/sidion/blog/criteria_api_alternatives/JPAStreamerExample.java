package de.sidion.blog.criteria_api_alternatives;

import com.speedment.jpastreamer.application.JPAStreamer;
import de.sidion.blog.criteria_api_alternatives.model.Customer;
import de.sidion.blog.criteria_api_alternatives.model.Customer$;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder$;

import java.util.List;

import static com.speedment.jpastreamer.streamconfiguration.StreamConfiguration.of;

public class JPAStreamerExample implements Example {

    private final JPAStreamer jpaStreamer;

    public JPAStreamerExample(JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    @Override
    public Customer findByName(String lastName) {
        List<Customer> limit = jpaStreamer.stream(Customer.class).filter(Customer$.lastName.equal(lastName)).toList();
        return limit.get(0);
    }

    @Override
    public PurchaseOrder findOrderOfCustomer(String lastName) {
        List<PurchaseOrder> orderCustomerMap = jpaStreamer.stream(of(PurchaseOrder.class).joining(PurchaseOrder$.customer)).toList();
        return orderCustomerMap.get(0);
    }
}