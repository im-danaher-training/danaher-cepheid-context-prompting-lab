package com.cepheid.training.order;

public class Product {
    private final String productId;
    private final String name;
    private final double unitPrice;
    private final int availableStock;

    public Product(String productId, String name, double unitPrice, int availableStock) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.availableStock = availableStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getAvailableStock() {
        return availableStock;
    }
}
