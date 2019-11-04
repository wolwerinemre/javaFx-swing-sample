package com.swedbank.example.listener;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.swedbank.example.Graphics;
import com.swedbank.example.constants.ApplicationConstants;
import com.swedbank.example.controller.FileController;
import com.swedbank.example.enums.MainButtonEnum;
import com.swedbank.example.enums.TableDataEnum;
import com.swedbank.example.model.FuelDTO;
import com.swedbank.example.util.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * @author memrecaglan
 *
 */
public class ButtonActionEvent implements EventHandler<ActionEvent> {
	
	@FXML
	private FileController fileContoller =  new FileController(); 
	@FXML
	private Graphics graphics = new Graphics();
	

	@SuppressWarnings("unchecked")
	@Override
	public void handle(final ActionEvent event) {
		final Button clickedButton = (Button) event.getSource();
		final MainButtonEnum clickedButtonProperties = Utils.getButtonProperties(clickedButton.getText());
		final HBox headerBox = (HBox) clickedButton.getParent();
		final FileChooser fileChooser = new FileChooser();

		final TableView<FuelDTO> table = (TableView<FuelDTO>) ((VBox) headerBox.getParent().getChildrenUnmodifiable()
				.get(ApplicationConstants.ONE)).getChildren().get(ApplicationConstants.ZERO);
		final ObservableList<FuelDTO> data = FXCollections.observableArrayList();

		switch (clickedButtonProperties) {
		case STATISTICS:
			statisticsAction(headerBox, table);
			break;
		case CLEAR:
			clearAction(headerBox, table);
			break;
		case LOAD:
			loadAction(clickedButton, headerBox, fileChooser, table, data);
			break;
		}
	}

	private void statisticsAction(HBox headerBox, TableView<FuelDTO> table) {
		final List<FuelDTO> fuels = IntStream.range(ApplicationConstants.ZERO, table.getItems().size())
				.mapToObj(index -> table.getItems().get(index)).collect(Collectors.toList());

		final Stage dialog = graphics.create(headerBox, fuels.stream().collect(Collectors.groupingBy(
				FuelDTO::getFuelName,
				Collectors.groupingBy(FuelDTO::getDateValue, Collectors.summingDouble(FuelDTO::getTotalPrice)))));
		dialog.show();
	}

	private void clearAction(HBox headerBox, TableView<FuelDTO> table) {
		table.getItems().clear();
		headerBox.getChildren().get(ApplicationConstants.ZERO).setDisable(Boolean.FALSE);
		Utils.initButtonComponents(headerBox, Boolean.TRUE);
	}

	private void loadAction(final Button clickedButton, final HBox headerBox, final FileChooser fileChooser,
			final TableView<FuelDTO> table, final ObservableList<FuelDTO> data) {
		final File file = fileChooser.showOpenDialog(null);

		if (file != null) {
			final List<FuelDTO> fuelList = fileContoller.readDataFromFile(file);

			if (fuelList.size() > ApplicationConstants.ZERO) {
				try {
					data.addAll(fuelList);
					clickedButton.setDisable(Boolean.TRUE);
					Utils.initButtonComponents(headerBox, Boolean.FALSE);
				} catch (Exception e) {
					clearAction(headerBox, table);
					Utils.messagesDialog(ApplicationConstants.ERROR, ApplicationConstants.INCORRECT_FILE);
				}
			} else
				Utils.initButtonComponents(headerBox, Boolean.TRUE);
		}

		final String dataProperties[] = Stream.of(TableDataEnum.values()).map(value -> value.getValue())
				.toArray(String[]::new);

		IntStream.rangeClosed(ApplicationConstants.ZERO, ApplicationConstants.THREE)
				.forEach(index -> ((TableColumn<?, ?>) table.getColumns().get(index))
						.setCellValueFactory(new PropertyValueFactory<>(dataProperties[index])));

		table.setItems(data);
	}
}
