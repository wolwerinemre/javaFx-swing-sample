package com.swedbank.example;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.swedbank.example.constants.ApplicationConstants;
import com.swedbank.example.enums.MainButtonEnum;
import com.swedbank.example.enums.TableColumnEnum;
import com.swedbank.example.listener.ButtonActionEvent;
import com.swedbank.example.util.Utils;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * 
 * @author memrecaglan
 *
 */
public class Table {

	public static BorderPane setMainPanel() {
		final BorderPane border = new BorderPane();
		final HBox headerPanel = headerPanel();

		border.setTop(headerPanel);
		border.setCenter(dataPanel());
		border.autosize();
		headerPanel.autosize();
		IntStream.range(ApplicationConstants.ONE, headerPanel.getChildren().size())
				.mapToObj(index -> headerPanel.getChildren().get(index))
				.forEach(component -> ((Button) component).setDisable(Boolean.TRUE));

		return border;
	}

	private static HBox headerPanel() {
		final HBox hbox =Utils.headerBoxStyle();
	
		Stream.of(MainButtonEnum.values()).map(value -> new Button(value.getValue())).forEach(component -> {
			component.setOnAction(new ButtonActionEvent());
			hbox.getChildren().add(component);
		});

		return hbox;
	}

	@SuppressWarnings("rawtypes")
	private static VBox dataPanel() {
		final VBox grid = new VBox();
		final TableView<?> table = new TableView();
		
		Stream.of(TableColumnEnum.values()).map(item -> item.getValue())
				.forEach(value -> table.getColumns().add(new TableColumn<>(value)));

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY,Insets.EMPTY)));
		grid.getChildren().add(table);
		grid.autosize();
		return grid;
	}
}
