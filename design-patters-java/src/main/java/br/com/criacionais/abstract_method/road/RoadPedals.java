package br.com.criacionais.abstract_method.road;

import br.com.criacionais.abstract_method.Pedals;

public class RoadPedals implements Pedals {
    
    @Override
    public String getType() {
        return "SPD-SL";
    }
}
