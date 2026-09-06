package br.com.criacionais.factory_method;

public class App {
    
    public static void main(String[] args) {
        BusTicketMachine busTicketMachine = new BusTicketMachine();
        TrainTicketMachine trainTicketMachine = new TrainTicketMachine();
        
        busTicketMachine.createTicket().buyTicket();
        trainTicketMachine.createTicket().buyTicket();
        
        
        
    }
}
