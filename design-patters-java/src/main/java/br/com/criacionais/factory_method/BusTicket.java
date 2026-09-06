package br.com.criacionais.factory_method;

public class BusTicket extends Ticket {
    private final double PRICE = 3.0;
    
    @Override
    public double getPrice() {
        return this.PRICE;
    }
    
    @Override
    public void buyTicket() {
        System.out.println("Bus ticket buyed..");
    }
}
