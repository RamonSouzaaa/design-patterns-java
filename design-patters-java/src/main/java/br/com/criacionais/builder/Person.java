package br.com.criacionais.builder;

import java.time.LocalDate;

public class Person {
    private final String firstName;
    private String middleName;
    private final String lastName;
    private LocalDate dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    
    private Person(PersonBuilder personBuilder) {
        this.firstName = personBuilder.firstName;
        this.middleName = personBuilder.middleName;
        this.lastName = personBuilder.lastName;
        this.dateOfBirth = personBuilder.dateOfBirth;
        this.emailAddress = personBuilder.emailAddress;
        this.phoneNumber = personBuilder.phoneNumber;
    }

    @Override
    public String toString() {
        return "[" + 
                "firstName=" + firstName + ", " +
                "middleName=" + middleName + ", " +
                "lastName=" + lastName + ", " + 
                "dateOfBirth=" + dateOfBirth + ", " +
                "emailAddress=" + emailAddress + ", " + 
                "phoneNumber=" + phoneNumber + "]";
    }

    public static class PersonBuilder {
        private String firstName;
        private String middleName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String emailAddress;
        private String phoneNumber;
        
        public PersonBuilder() { }
        
        public PersonBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        
        public PersonBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }
        
        public PersonBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        
        public PersonBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        
        public PersonBuilder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }
        
        public PersonBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        
        public Person build() {
            return new Person(this);
        }
    }
}
