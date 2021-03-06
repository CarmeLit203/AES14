package client.control;

import com.Contract;
import com.data.Message;
import com.data.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentControl extends Application {
    public Student student;
    public Client client;


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Contract.clientFXML + "StudentMenu.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource(Contract.css).toExternalForm());
            primaryStage.setTitle("AES - "+student.getFirst_name()+" "+student.getLast_name());
            primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.show();
            //primaryStage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Set Close event handle
        primaryStage.setOnCloseRequest(event -> {
            try {
                //Close connection to server.
                client.sendToServer(new Message(Contract.LOG_OFF,client.user));
                client.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
