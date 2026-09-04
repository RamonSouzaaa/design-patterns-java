package br.com.criacionais.singleton;

public enum PrinterEnum {
    //thread-safe por padrão
    
    INSTANCE;
    
    private PrinterEnum() {}
    
    public static PrinterEnum getInstance() {
        return INSTANCE;
    }
    
    public void print() {
        System.out.println("Printing..");
    }
}
