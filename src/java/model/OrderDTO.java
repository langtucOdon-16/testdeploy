/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author KATANA15
 */
public class OrderDTO {

    private int id;
    private int accountId;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private String addressName;
    private List<CartItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(int id, int accountId, Date orderDate, double totalAmount, String status, String addressName, List<CartItemDTO> items) {
        this.id = id;
        this.accountId = accountId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.addressName = addressName;
        this.items = new ArrayList<>();
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

}
