package com.Instapay;

public class Bills {

    // Content of the bill.
    private String name;
    private String address;
    private int id;
    private float amount;
    private String number;

    //------------------------------------------------------------------------------------------------------------------

    // Constructor
    Bills(String name, String address, int id, float amount, String number) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.amount = amount;
        this.number = number;
    }

    //----------------------------------------------------------------

    // Setters.
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    //----------------------------------------------------------------

    // Getters.
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public String getNumber() {
        return number;
    }

    //----------------------------------------------------------------

    public void print() {
        System.out.println("Bill ID : " + this.id);
        System.out.println("Bill Name : " + this.name );
        System.out.println("Address : " + this.address);
        System.out.println("Amount : " + this.amount);
    }

    //____________________________________________________________________

}
