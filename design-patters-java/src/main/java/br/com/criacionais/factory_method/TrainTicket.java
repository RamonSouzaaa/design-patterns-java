package br.com.criacionais.factory_method;

public class TrainTicket extends Ticket {
    private final double PRICE = 5.0;
    
    @Override
    public double getPrice() {
        return this.PRICE;
    }
    
    @Override
    public void buyTicket() {
        System.out.println("Train ticket buyed..");
    }
}
