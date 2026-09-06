package br.com.criacionais.factory_method;

public class TrainTicketMachine implements TicketMachine {
    
    public TrainTicket createTicket() {
        return new TrainTicket();
    }
}
