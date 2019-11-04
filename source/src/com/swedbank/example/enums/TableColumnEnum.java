package com.swedbank.example.enums;
/**
 * 
 * @author memrecaglan
 *
 */
public enum TableColumnEnum {
	
    NAME("Fuel Name"),
    PRICE("Fuel Price"),
    AMMOUNT("Fuel Ammount"),
    REFUELL_DATE("Refuell Date");
    
    private final String value;

    private TableColumnEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
