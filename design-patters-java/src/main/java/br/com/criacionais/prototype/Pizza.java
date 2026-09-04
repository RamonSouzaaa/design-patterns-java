package br.com.criacionais.prototype;

public class Pizza extends Meal {
    
    private PizzaFlavor flavor;
    
    public Pizza(double price, PizzaFlavor pizzaFlavor) {
        this.flavor = pizzaFlavor;
        super(price);
    }

    public PizzaFlavor getFlavor() {
        return flavor;
    }

    public void setFlavor(PizzaFlavor flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return "[" + "flavor=" + this.flavor + "]";
    }
    
    @Override
    public Pizza clone() {
        return new Pizza(this.getPrice(), this.getFlavor());
    }
}
