package br.com.criacionais.singleton;

public class DocumentPrinter {
    
    public void print() {
        PrinterEnum.INSTANCE.print();
    }
}
