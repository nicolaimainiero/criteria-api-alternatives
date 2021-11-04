package de.sidion.blog.criteria_api_alternatives;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import de.sidion.blog.criteria_api_alternatives.model.Customer;
import de.sidion.blog.criteria_api_alternatives.model.Customer$;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder$;

import java.util.Optional;

import static com.speedment.jpastreamer.streamconfiguration.StreamConfiguration.of;

public class JPAStreamerExample implements Example {

    private final JPAStreamer jpaStreamer;

    public JPAStreamerExample(JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    @Override
    public Customer findByName(String firstName, String lastName) {
        Optional<Customer> customer = jpaStreamer
                .stream(Customer.class)
                .filter(Customer$.firstName.equal(firstName).and(Customer$.lastName.equal(lastName)))
                .findFirst();
        return customer.orElse(null);
    }

    @Override
    public PurchaseOrder findOrderOfCustomer(String lastName) {
        StreamConfiguration<PurchaseOrder> joining = of(PurchaseOrder.class).joining(PurchaseOrder$.customer);
        Optional<PurchaseOrder> order = jpaStreamer
                .stream(of(PurchaseOrder.class).joining(PurchaseOrder$.customer))
                .filter(po -> po.getCustomer().getLastName().equals(lastName))
                .findFirst();
        return order.orElse(null);
    }
}