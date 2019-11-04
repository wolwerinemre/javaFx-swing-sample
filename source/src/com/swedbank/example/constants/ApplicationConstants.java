package com.swedbank.example.constants;

import java.time.format.DateTimeFormatter;

/**
 * 
 * @author memrecaglan
 *
 */
public class ApplicationConstants {
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;

	public static String STATISTICS_FIELD_LABEL = "Fuel Type :";
	public static String HEADER_PANEL_NAME = "Data Manipulation";
	public static String CENTRAL_PANEL_NAME = "Information";
	public static String MAIN_APPLICATION_NAME = "Fuel Refulling Tracking System";
	public static String CURRENT_DIRECTORY_PATH = ".";
	public static String TEXT_FILE_DELIMETER = "\\|";
	public static String INCORRECT_FILE = "Incorrect File Data !!!";
	public static String FILE_UPLOAD_COMPLETE = "File Upload Complete";
	public static String COMMA = ",";
	public static String FLOAT_VALUE_FORMAT = "%.3f";
	public static String EMPTY_STRING = "";
	public static String MONTH_STRING= "Month";
	public static String PRICE_TEXT = "Total ammount";
	public static String WARNING = "Warning!";
	public static String ERROR = "Error!";
	public static String SUCCESS = "Success!";
	public static String ALL = "All";
	public static String OK = "OK";

	public static int GRID_SPACE_WITH = 25;
	public static int GRID_SPACE_HEIGHT = 55;
	public static int ARRAY_MAX_LENGTH = 25;
}
