package com.swedbank.example.enums;
/**
 * 
 * @author memrecaglan
 *
 */
public enum MainButtonEnum {
	
    LOAD("Load"),
    STATISTICS("Statistics"),
    CLEAR("Clear");
    
    private final String value;

    private MainButtonEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
