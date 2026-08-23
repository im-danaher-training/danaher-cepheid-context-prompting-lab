package com.cepheid.training.order;

public class Customer {
    private final String customerId;
    private final String name;
    private final String region;

    public Customer(String customerId, String name, String region) {
        this.customerId = customerId;
        this.name = name;
        this.region = region;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }
}
