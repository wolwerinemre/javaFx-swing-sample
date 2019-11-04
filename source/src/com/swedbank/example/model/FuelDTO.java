package com.swedbank.example.model;

import java.time.LocalDate;
import java.time.Month;

import com.swedbank.example.constants.ApplicationConstants;

import javafx.beans.property.SimpleStringProperty;
/**
 * 
 * @author memrecaglan
 *
 */
public class FuelDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private SimpleStringProperty fuelName;
    private SimpleStringProperty fuelPrice;
    private SimpleStringProperty fuelAmount;
    private SimpleStringProperty refuellingDate;

    public FuelDTO() {
		super();
	}

	public String getFuelName() {
        return fuelName.get();
    }

    public void setFuelName(String fuelName) {
        this.fuelName = new SimpleStringProperty(fuelName);
    }

    public double getFuelPrice() {
        return Double.parseDouble(fuelPrice.get());
    }

    public void setFuelPrice(String fuelPrice) {
        this.fuelPrice = new SimpleStringProperty(fuelPrice);
    }

    public double getFuelAmount() {
        return Double.parseDouble(fuelAmount.get());
    }

    public void setFuelAmount(String fuelAmount) {
        this.fuelAmount = new SimpleStringProperty(fuelAmount);
    }

    public String getRefuellingDate() {             
        return refuellingDate.get();
    }

    public void setRefuellingDate(String refuellingDate) {
        this.refuellingDate = new SimpleStringProperty(refuellingDate);
    }
    
    public Month getDateValue() {
        return LocalDate.parse(getRefuellingDate(),ApplicationConstants.DATE_FORMATTER).getMonth();
    }
    
    public double getTotalPrice() {
        return getFuelAmount() * getFuelPrice();
   }
}
