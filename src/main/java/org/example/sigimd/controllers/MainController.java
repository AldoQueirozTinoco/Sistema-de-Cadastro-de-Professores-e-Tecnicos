package org.example.sigimd.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.sigimd.database.BancoDAO;
import org.example.sigimd.models.Person;
import org.example.sigimd.models.Professor;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class MainController implements Initializable {
    BancoDAO banco = BancoDAO.getInstance();

    @FXML
    private ListView<String> myListView;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addProfessor(ActionEvent event) {
    try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sigimd/create-professor-view.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }catch(IOException e){
        e.printStackTrace();
    }
    }

    public void addTecnico(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sigimd/create-tecnico-view.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for(Person p : banco.getFuncionarios()){
            myListView.getItems().add(p.getName() );
        }

    }

    public void showChoice(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sigimd/person-view.fxml"));
         root = loader.load();

        PersonController controller = loader.getController();
            for(Person p: banco.getFuncionarios()){
                if((myListView.getSelectionModel().getSelectedItem()).equals(p.getName())){
                    if(p instanceof Professor){
                        controller.showDetailsProfessor(p);
                    }else{
                        controller.showDetailsTecnico(p);
                    }
                }
            }

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
