package br.com.criacionais.abstract_method;

import br.com.criacionais.abstract_method.factory.BikeFactory;
import br.com.criacionais.abstract_method.factory.MountainFactory;
import br.com.criacionais.abstract_method.factory.RoadFactory;

public class App {
    
    public static void main(String[] args) {
        
        BikeFactory mountainFactory = new MountainFactory();
        BikeFactory roadFactory = new RoadFactory();
        
        System.out.println(mountainFactory.createBike().toString());
        System.out.println(roadFactory.createBike().toString());
    }
}
