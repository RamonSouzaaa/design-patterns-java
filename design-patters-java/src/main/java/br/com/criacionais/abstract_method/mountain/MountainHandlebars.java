package br.com.criacionais.abstract_method.mountain;

import br.com.criacionais.abstract_method.Handlebars;

public class MountainHandlebars implements Handlebars {
    
    @Override
    public String getType() {
        return "FLAT";
    }
}
