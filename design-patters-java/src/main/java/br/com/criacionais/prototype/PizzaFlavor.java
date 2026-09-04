package br.com.criacionais.prototype;

public class PizzaFlavor {
    private String name;
    
    public PizzaFlavor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
