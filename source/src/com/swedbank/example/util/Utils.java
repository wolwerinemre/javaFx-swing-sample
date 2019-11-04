package com.swedbank.example.util;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.swedbank.example.constants.ApplicationConstants;
import com.swedbank.example.enums.MainButtonEnum;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * 
 * @author memrecaglan
 *
 */
public class Utils {

	public static Double checkNumber(String stringNumber) throws NumberFormatException {
		String string = stringNumber.replace(ApplicationConstants.COMMA, ApplicationConstants.CURRENT_DIRECTORY_PATH);
		Double result = Double.parseDouble(string);

		if (result < ApplicationConstants.ZERO) {
			throw new NumberFormatException(ApplicationConstants.INCORRECT_FILE);
		} else {
			return result;
		}
	}

	public static HBox headerBoxStyle() {
		final HBox hbox = new HBox();
		hbox.setPadding(new Insets(ApplicationConstants.GRID_SPACE_WITH, ApplicationConstants.GRID_SPACE_HEIGHT,
				ApplicationConstants.GRID_SPACE_WITH, ApplicationConstants.GRID_SPACE_HEIGHT));

		hbox.setSpacing(ApplicationConstants.GRID_SPACE_HEIGHT);
		hbox.autosize();
		return hbox;
	}

	public static MainButtonEnum getButtonProperties(String buttonName) {
		return Stream.of(MainButtonEnum.values()).filter(button -> button.getValue().equalsIgnoreCase(buttonName))
				.findFirst().get();
	}

	public static void initButtonComponents(final HBox headerBox, Boolean isDisabled) {
		IntStream.range(ApplicationConstants.ONE, ApplicationConstants.THREE)
				.mapToObj(index -> headerBox.getChildren().get(index))
				.forEach(component -> component.setDisable(isDisabled));
	}

	public static void messagesDialog(String title, String message) {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.APPLICATION_MODAL);
		dialogStage.setTitle(title);

		VBox vbox = new VBox();
		Text text = new Text(message);
		Button button = new Button(ApplicationConstants.OK);
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				dialogStage.close();
			}
		});
		vbox.getChildren().addAll(text, button);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(30));

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

}
