package br.com.criacionais.abstract_method.factory;

import br.com.criacionais.abstract_method.road.RoadBike;
import br.com.criacionais.abstract_method.road.RoadHandlebars;
import br.com.criacionais.abstract_method.road.RoadPedals;
import br.com.criacionais.abstract_method.road.RoadTire;

public class RoadFactory implements BikeFactory {
    
     @Override
    public RoadBike createBike() {
        return new RoadBike(new RoadHandlebars(),
                            new RoadPedals(),
                            new RoadTire(),
                            new RoadTire());
    }
}
