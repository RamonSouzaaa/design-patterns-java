package br.com.criacionais.singleton;

public class PrinterSynchronized {

    private static PrinterSynchronized INSTANCE;

    private PrinterSynchronized() { }

    // O synchronized garante que duas threads não criem duas impressoras ao mesmo tempo
    public static synchronized PrinterSynchronized getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrinterSynchronized(); // Cria apenas na primeira chamada (Lazy)
        }
        return INSTANCE;
    }

    public void print() {
        System.out.println("Printing..");
    }
}
