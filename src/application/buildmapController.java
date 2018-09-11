package application;

import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;

public class buildmapController {

    //Declarations
	@FXML
    private TextField no;
    @FXML
    private TextField ed;
    @FXML
    private Button done;
    @FXML
    private Button close4;
    public static int nodes,edges;
    public Dijkstra d = new Dijkstra();

    //
	public void done(ActionEvent event){
		try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("nodesnames.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene1=new Scene(root1);	
			 scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene1); 
            stage.setResizable(false);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//
    public void done1(ActionEvent event){
		try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("deleting.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene1=new Scene(root1);	
			 scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setScene(scene1); 
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//
	public void closing4(ActionEvent event){
		Stage stage = (Stage) close4.getScene().getWindow();
   	    stage.close();
	}
}
