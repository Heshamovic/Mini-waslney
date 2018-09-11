package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//Class responsible for window controller
public class mainwindowController {

	//Declaration
	@FXML
	private Button closing;
	@FXML
	Label sure3;
	@FXML
	private Button yes3;
	@FXML
	private Button no3;

	//Main window controller
	public void mywife(ActionEvent event)throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("nodesnames.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			Scene scene1=new Scene(root1);
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene1);
			stage.setResizable(false);
			stage.setTitle("Add");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/unnamed.png")));
			stage.show();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 1.85);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	//Path window controller
	public void findw(ActionEvent event)throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("findway.fxml"));
			Parent root2 = (Parent) fxmlLoader.load();
			Scene scene=new Scene(root2);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			stage2.setResizable(false);
			stage2.setTitle("Find Path");
			stage2.getIcons().add(new Image(getClass().getResourceAsStream("/unnamed.png")));
			stage2.show();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage2.setY((primScreenBounds.getHeight() - stage2.getHeight()) / 1.85);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Close .. window
	public void yess3(ActionEvent event)
	{
		Stage stage = (Stage) closing.getScene().getWindow();
		stage.close();
	}

	//Close .. window
	public void noo3(ActionEvent event)
	{
		yes3.setVisible(false);
		no3.setVisible(false);
		sure3.setVisible(false);
	}

	//Close .. window
	public void close(ActionEvent event)
	{
		yes3.setVisible(true);
		no3.setVisible(true);
		sure3.setVisible(true);
	}

	//Update window controller
	public void upd(ActionEvent event)throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("buildmap.fxml"));
			Parent root7 = (Parent) fxmlLoader.load();
			Scene scene7=new Scene(root7);
			scene7.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage7 = new Stage();
			stage7.setScene(scene7);
			stage7.setResizable(false);
			stage7.setTitle("Update");
			stage7.getIcons().add(new Image(getClass().getResourceAsStream("/unnamed.png")));
			stage7.show();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage7.setY((primScreenBounds.getHeight() - stage7.getHeight()) / 1.85);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
