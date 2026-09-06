package br.com.criacionais.abstract_method;

public abstract class Bike {
    
    private Handlebars handlebars;
    private Pedals pedals;
    private Tire frontTire;
    private Tire backTire;

    public Bike(Handlebars handlebars, 
                Pedals pedals, 
                Tire frontTire, 
                Tire backTire) {
        this.handlebars = handlebars;
        this.pedals = pedals;
        this.frontTire = frontTire;
        this.backTire = backTire;
    }

    public Handlebars getHandlebars() {
        return handlebars;
    }

    public void setHandlebars(Handlebars handlebars) {
        this.handlebars = handlebars;
    }

    public Pedals getPedals() {
        return pedals;
    }

    public void setPedals(Pedals pedals) {
        this.pedals = pedals;
    }

    public Tire getFrontTire() {
        return frontTire;
    }

    public void setFrontTire(Tire frontTire) {
        this.frontTire = frontTire;
    }

    public Tire getBackTire() {
        return backTire;
    }

    public void setBackTire(Tire backTire) {
        this.backTire = backTire;
    }

    @Override
    public String toString() {
        return "[handlebars=" + handlebars.getType() + "," +
                "pedals=" + pedals.getType() + "," +
                "frontTire=" + frontTire.getWidth() + "," + 
                "backTire=" + backTire.getWidth() + "]";
    }
}
