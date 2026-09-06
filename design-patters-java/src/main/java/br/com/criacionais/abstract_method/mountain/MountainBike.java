package br.com.criacionais.abstract_method.mountain;

import br.com.criacionais.abstract_method.Bike;

public class MountainBike extends Bike {
   
    public MountainBike(MountainHandlebars handlebars,
                         MountainPedals pedals,
                         MountainTire frontTire,
                         MountainTire backTire ) {
        super(handlebars,
              pedals,
              frontTire,
              backTire);
        
    }
}
