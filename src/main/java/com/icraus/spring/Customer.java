package com.icraus.spring;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, name = "name")
    private String name = "";

    @Column(nullable = false, name = "phone")
    private String phone = "";

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Customer customer = (Customer) o;
        return getName().equals(customer.getName()) && getPhone().equals(customer.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone());
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer(){
    }
    public Customer(long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
