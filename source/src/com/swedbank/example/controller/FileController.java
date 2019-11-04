package com.swedbank.example.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.swedbank.example.constants.ApplicationConstants;
import com.swedbank.example.model.FuelDTO;
import com.swedbank.example.util.Utils;

import javafx.fxml.Initializable;
/**
 * 
 * @author memrecaglan
 *
 */
public class FileController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public List<FuelDTO> readDataFromFile(final File file) {

		List<FuelDTO> temporaryCollection = new ArrayList<>();
		
		try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = reader.readLine()) != null) {

				final String lineString[] = line.split(ApplicationConstants.TEXT_FILE_DELIMETER);
				final String price = String.format(ApplicationConstants.FLOAT_VALUE_FORMAT,
						Utils.checkNumber(lineString[ApplicationConstants.ONE]));
				final String ammount = String.format(ApplicationConstants.FLOAT_VALUE_FORMAT,
						Utils.checkNumber(lineString[ApplicationConstants.TWO]));

				FuelDTO dto = new FuelDTO();
				dto.setFuelName(lineString[ApplicationConstants.ZERO]);
				dto.setFuelAmount(ammount.replace(ApplicationConstants.COMMA, ApplicationConstants.CURRENT_DIRECTORY_PATH));
				dto.setFuelPrice(price.replace(ApplicationConstants.COMMA, ApplicationConstants.CURRENT_DIRECTORY_PATH));
				dto.setRefuellingDate(lineString[ApplicationConstants.THREE]);
				temporaryCollection.add(dto);
			}
		} catch (Exception ex) {
			temporaryCollection.clear();
			System.out.println(ex);
			Utils.messagesDialog(ApplicationConstants.ERROR, ApplicationConstants.INCORRECT_FILE);
		}
		Utils.messagesDialog(ApplicationConstants.SUCCESS, ApplicationConstants.FILE_UPLOAD_COMPLETE);
		return temporaryCollection;
	}

	

}
