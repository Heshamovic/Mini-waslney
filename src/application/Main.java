package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("mainwindow.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.show();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) * 1.1);
			primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/unnamed.png")));
            primaryStage.setTitle("mini وصلنى");
            primaryStage.setScene(scene);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
