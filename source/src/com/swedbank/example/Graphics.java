package com.swedbank.example;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.swedbank.example.constants.ApplicationConstants;
import com.swedbank.example.enums.MainButtonEnum;
import com.swedbank.example.util.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author memrecaglan
 *
 */
public class Graphics {

	@SuppressWarnings("unchecked")
	public  Stage create(HBox headerBox, Map<String, Map<Month, Double>> collection) {
		final Stage stage = new Stage();
		final Scene scene = new Scene(new Group());
		final VBox root = new VBox();
		final HBox hbox = Utils.headerBoxStyle();

		ObservableList<String> fuelTypeList = FXCollections.observableArrayList();
		stage.setTitle(MainButtonEnum.STATISTICS.getValue());
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.sizeToScene();
		stage.setResizable(Boolean.TRUE);

		final NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel(ApplicationConstants.PRICE_TEXT);

		final CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel(ApplicationConstants.MONTH_STRING);
		xAxis.setCategories(FXCollections.<String>observableArrayList(Stream.of(Month.values())
				.map(month -> month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)).collect(Collectors.toList())));

		final BarChart barChart = new BarChart(xAxis, yAxis);
		final List<?> list = initAllData(collection);

		fuelTypeList.add(ApplicationConstants.ALL);
		fuelTypeList.addAll(collection.keySet());

		Label fuelTypeLabel = new Label(ApplicationConstants.STATISTICS_FIELD_LABEL);
		ComboBox<String> fuelTypeCombo = new ComboBox<>(fuelTypeList);
		fuelTypeCombo.setValue(ApplicationConstants.ALL);
		fuelTypeCombo.setOnAction(comboBoxActionEvent(collection, barChart, fuelTypeCombo));

		barChart.getData().addAll(list);
		hbox.getChildren().addAll(fuelTypeLabel, fuelTypeCombo);
		root.getChildren().add(hbox);
		root.getChildren().add(barChart);
		scene.setRoot(root);
		stage.setScene(scene);
		return stage;
	}
	
	private  XYChart.Series<String, Number> loadDataChart(final Map<String, Map<Month, Double>> collection,
			final String fuelName) {
		final XYChart.Series<String, Number> fuelStatistics = new XYChart.Series<>();
		fuelStatistics.setName(fuelName);

		Stream.of(Month.values()).forEach(month -> {
			fuelStatistics.getData().add(new XYChart.Data<>(month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
					collection.get(fuelName).getOrDefault(month, 0d)));

			final Tooltip tooltip = new Tooltip(String.format(ApplicationConstants.FLOAT_VALUE_FORMAT,
					collection.get(fuelName).getOrDefault(month, 0d)));

			Tooltip.install(fuelStatistics.nodeProperty().get(), tooltip);
		});

		return fuelStatistics;
	}

	@SuppressWarnings("unchecked")
	private  EventHandler<ActionEvent> comboBoxActionEvent(Map<String, Map<Month, Double>> collection,
			final BarChart barChart, ComboBox<String> fuelTypeCombo) {
		return new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				barChart.getData().clear();
				if (fuelTypeCombo.getValue().equals(ApplicationConstants.ALL)) {
					barChart.getData().addAll(initAllData(collection));
				} else {
					barChart.getData().addAll(loadDataChart(collection, fuelTypeCombo.getValue()));
				}
			}
		};
	}

	private  List<?> initAllData(Map<String, Map<Month, Double>> collection) {
		final List<?> list = collection.keySet().stream().map(item -> loadDataChart(collection, item))
				.collect(Collectors.toList());
		return list;
	}

}
