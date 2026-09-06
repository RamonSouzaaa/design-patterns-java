package br.com.criacionais.abstract_method.road;

import br.com.criacionais.abstract_method.Bike;

public class RoadBike extends Bike {

    public RoadBike(RoadHandlebars handlebars, 
                    RoadPedals pedals, 
                    RoadTire frontTire, 
                    RoadTire backTire) {
        super(handlebars, pedals, frontTire, backTire);
    }
}
