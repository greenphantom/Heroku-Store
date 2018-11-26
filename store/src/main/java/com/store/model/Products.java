package com.store.model;

public class Products {

    long itemId;
    String name;
    double msrp;
    double salePrice;
    int upc;
    String shortDescription;
    String brandName;
    String size;
    String color;
    String gender;
    public Products(){

    }

     public Products(long itemId, String name, double msrp, double salePrice, int upc, String shortDescription, String brandName, String size, String color, String gender ){
         this.itemId=itemId;
         this.name=name;
         this.salePrice=salePrice;
         this.upc=upc;
         this.shortDescription=shortDescription;
         this.brandName=brandName;
         this.size=size;
         this.color=color;
         this.gender=gender;
     }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMsrp() {
        return msrp;
    }

    public void setMsrp(double msrp) {
        this.msrp = msrp;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
