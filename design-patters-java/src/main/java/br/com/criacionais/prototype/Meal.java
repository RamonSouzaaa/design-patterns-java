package br.com.criacionais.prototype;

public abstract class Meal {
    private double price;
    
    public Meal(double price) {
        this.price = price;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public abstract Meal clone();
}
