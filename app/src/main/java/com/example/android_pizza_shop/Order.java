package com.example.android_pizza_shop;

public class Order {
    private String orderDate;
    private double totalAmount;

    public Order(String orderDate, double totalAmount) {
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
