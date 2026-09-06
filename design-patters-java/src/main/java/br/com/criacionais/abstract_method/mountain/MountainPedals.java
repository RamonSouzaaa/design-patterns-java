package br.com.criacionais.abstract_method.mountain;

import br.com.criacionais.abstract_method.Pedals;

public class MountainPedals implements Pedals {
    
    @Override
    public String getType() {
        return "SPD";
    }
}
