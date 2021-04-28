package com.freenow.domainobject;

public enum Manufacturer {
    TOYOTA_MOTOR_CORP("Toyota"),
    VOLKSWAGEN("Volkswagen"),
    FORD_MOTOR("Ford"),
    GENERAL_MOTORS_CO("GM");

    private String label;

    private Manufacturer(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
