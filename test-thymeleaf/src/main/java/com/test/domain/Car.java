package com.test.domain;

public class Car {
    private Long cid ;
    private String cname ;
    private String color ;
    private double price ;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car(Long cid, String cname, String color, double price) {
        this.cid = cid;
        this.cname = cname;
        this.color = color;
        this.price = price;
    }

    public Car() {
    }
}
