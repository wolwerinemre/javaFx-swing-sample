package com.swedbank.example.enums;
/**
 * 
 * @author memrecaglan
 *
 */
public enum TableDataEnum {
	
    NAME_PROPERTY("fuelName"),
    PRICE_PROPERTY("fuelPrice"),
    AMMOUNT_PROPERTY("fuelAmount"),
    REFUELL_DATE_PROPERTY("refuellingDate");
    
    private final String value;

    private TableDataEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
