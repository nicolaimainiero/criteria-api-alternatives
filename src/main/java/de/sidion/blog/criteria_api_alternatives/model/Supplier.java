package de.sidion.blog.criteria_api_alternatives.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Supplier {

    @Id
    private Long id;

    private String name;

    @Column(name = "contact_name")
    private String contactName;

    private String city;

    private String country;

    private String phone;

    private String fax;

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactName='" + contactName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}