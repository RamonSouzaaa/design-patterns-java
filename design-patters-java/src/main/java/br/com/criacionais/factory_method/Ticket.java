package br.com.criacionais.factory_method;

public abstract class Ticket {
    protected abstract double getPrice();
    
    public void buyTicket() {
        System.out.println("Ticket buyed..");
    }
}
