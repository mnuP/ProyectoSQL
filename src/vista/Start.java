/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vista;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Start extends Application{
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage ventana) throws Exception{
        //ventana.setOnCloseRequest(event -> {event.consume();});
        Parent root= FXMLLoader.load(getClass().getResource("principal.fxml"));
        Scene scene=new Scene(root);
        ventana.setScene(scene);
        
        ventana.setTitle("SQL Manager");
        ventana.setResizable(false);
        ventana.show();
        
    }
 
}
