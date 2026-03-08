/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author KATANA15
 */
public class PaymentDTO {

    private int paymentId;
    private String namePayment;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentId, String namePayment) {
        this.paymentId = paymentId;
        this.namePayment = namePayment;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getNamePayment() {
        return namePayment;
    }

    public void setNamePayment(String namePayment) {
        this.namePayment = namePayment;
    }

}
