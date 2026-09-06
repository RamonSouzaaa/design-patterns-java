package br.com.criacionais.factory_method;

public class BusTicketMachine implements TicketMachine {
    
    public BusTicket createTicket() {
        return new BusTicket();
    }
}
