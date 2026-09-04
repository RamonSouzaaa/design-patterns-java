package br.com.criacionais.prototype;

import java.util.List;
import java.util.ArrayList;

public class App {
    
    public static void main(String[] args) {
        
        PizzaFlavor f1 = new PizzaFlavor("Bacon");
        PizzaFlavor f2 = new PizzaFlavor("Calabresa");
        
        Pizza p1 = new Pizza(50d, f1);
        Pizza p2 = p1.clone();
        Pizza p3 = p1.clone();
        Pizza p4 = p1.clone();
        Pizza p5 = p1.clone();
        
        f1.setName("Bacon with cheese extra");
        
        Pizza p6 = new Pizza(60d, f2);
        Pizza p7 = p6.clone();
        Pizza p8 = p6.clone();
        Pizza p9 = p6.clone();
        Pizza p10 = p6.clone();
        
        List<Meal> order = new ArrayList<>();
        order.add(p1);
        order.add(p2);
        order.add(p3);
        order.add(p4);
        order.add(p5);
        order.add(p6);
        order.add(p7);
        order.add(p8);
        order.add(p9);
        order.add(p10);
        
        order.forEach(System.out::println);
        
        
    }
}
