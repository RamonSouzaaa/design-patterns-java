package br.com.criacionais.singleton;

public class ImagePrinter {
    
    public void print() {
        PrinterEnum printer = PrinterEnum.getInstance();
        printer.print();
    }
}
