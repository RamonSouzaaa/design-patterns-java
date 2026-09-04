package br.com.criacionais.builder;

public class App {
    
    public static void main(String[] args) {
        
        var personBuilder = new Person.PersonBuilder();
        
        Person p1 = personBuilder.firstName("João")
                                 .lastName("Silva")
                                 .build();
        
        System.out.println(p1.toString());
    }
}
