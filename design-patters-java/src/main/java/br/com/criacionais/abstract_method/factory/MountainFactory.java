package br.com.criacionais.abstract_method.factory;

import br.com.criacionais.abstract_method.mountain.MountainBike;
import br.com.criacionais.abstract_method.mountain.MountainHandlebars;
import br.com.criacionais.abstract_method.mountain.MountainPedals;
import br.com.criacionais.abstract_method.mountain.MountainTire;

public class MountainFactory implements BikeFactory {

    @Override
    public MountainBike createBike() {
        return new MountainBike(new MountainHandlebars(),
                                new MountainPedals(),
                                new MountainTire(),
                                new MountainTire());
    }
    
    
}
