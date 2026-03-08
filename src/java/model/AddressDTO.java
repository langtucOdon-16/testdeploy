/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author : GiangLT
 * @date : 7/2/2026
 * @description: Lop mo hinh Address dai dien cho dia chi lien he hoac giao hang cua nguoi dung
 */
public class AddressDTO {
    private int addressId;
    private int accountId;
    private String addressName;
    private String phone;

    public AddressDTO() {
    }

    public AddressDTO(int addressId, int accountId, String addressName, String phone) {
        this.addressId = addressId;
        this.accountId = accountId;
        this.addressName = addressName;
        this.phone = phone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
