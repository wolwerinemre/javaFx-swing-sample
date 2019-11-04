package com.swedbank.example;

import com.swedbank.example.constants.ApplicationConstants;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * 
 * @author memrecaglan
 *
 */
public class MainApplication extends Application {
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle(ApplicationConstants.MAIN_APPLICATION_NAME);
        final StackPane root = new StackPane();
        root.getChildren().add(Table.setMainPanel());
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setResizable(Boolean.TRUE);
        primaryStage.show();
    }

	public static void main(String[] args) {
		launch(args);
	}

}
