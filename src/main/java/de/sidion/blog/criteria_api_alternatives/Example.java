package de.sidion.blog.criteria_api_alternatives;

import de.sidion.blog.criteria_api_alternatives.model.Customer;
import de.sidion.blog.criteria_api_alternatives.model.PurchaseOrder;

public interface Example {

    Customer findByName(String firstName, String lastName);

    PurchaseOrder findOrderOfCustomer(String lastName);
}