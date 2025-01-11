package org.example.sigimd.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.sigimd.database.BancoDAO;
import org.example.sigimd.models.Person;
import org.example.sigimd.models.Professor;
import org.example.sigimd.models.TecnicoADM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.example.sigimd.handler.FileHandler.write;

public class PersonController  {

    BancoDAO banco = BancoDAO.getInstance();

    @FXML
    private Label bairro;

    @FXML
    private Label cargaHoraria;

    @FXML
    private Label cep;

    @FXML
    private Label cidade;

    @FXML
    private Label cpf;

    @FXML
    private Label dataIngresso;

    @FXML
    private Label dataNascimento;

    @FXML
    private Label departamento;

    @FXML
    private Label disciplinas;

    @FXML
    private Label formacao;

    @FXML
    private Label genero;

    @FXML
    private Label matricula;

    @FXML
    private Label nivel;

    @FXML
    private Label nome;

    @FXML
    private Label numero;

    @FXML
    private Label rua;

    @FXML
    private Label salario;

    @FXML
    private Label profissao;

    @FXML
    private Label disciplinasesalubridade;

    private Stage stage;
    private Scene scene;
    private Parent root;
    Person tempPerson;


    @FXML
    void voltarAoMenu(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sigimd/hello-view.fxml"));
            root = loader.load();

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void showDetailsProfessor(Person person) {
        tempPerson = person;
        Professor professor = (Professor) person;
        nome.setText(professor.getName());
        cpf.setText(professor.getCpf());
        rua.setText(professor.getEndereco().getRua());
        cidade.setText(professor.getEndereco().getCidade());
        bairro.setText(professor.getEndereco().getBairro());
        cep.setText(professor.getEndereco().getCep());
        departamento.setText(professor.getDepartamento());

        profissao.setText("Professor");


        salario.setText(String.format("R$ %.2f",professor.getSalario()));

        if(professor.getGenero() != null) {
        genero.setText(professor.getGenero().toString());
        }
        if(professor.getDisciplinas() != null) {
        disciplinas.setText(( professor).getDisciplinas().toString());
        }
        if(professor.getFormacaoProfessor() != null) {
        formacao.setText(( professor).getFormacaoProfessor().toString());
        }
        if(professor.getDataNascimento() != null) {
        dataNascimento.setText(professor.getDataNascimento().toString());
        }
        if(professor.getDataIngresso() != null) {
        dataIngresso.setText(professor.getDataIngresso().toString());
        }
        if(professor.getNivelProfessor() != null) {
        nivel.setText((professor).getNivelProfessor().toString());
        }
        if(professor.getMatricula() != null) {
        matricula.setText(professor.getMatricula().toString());
        }
        cargaHoraria.setText(Integer.toString(professor.getCargaHoraria()));
        numero.setText(Integer.toString(professor.getEndereco().getNumero()));
    }

    public void showDetailsTecnico(Person person) {
        tempPerson = person;

        TecnicoADM tecnicoADM = (TecnicoADM) person;
        nome.setText(tecnicoADM.getName());
        cpf.setText(tecnicoADM.getCpf());
        rua.setText(tecnicoADM.getEndereco().getRua());
        cidade.setText(tecnicoADM.getEndereco().getCidade());
        bairro.setText(tecnicoADM.getEndereco().getBairro());
        cep.setText(tecnicoADM.getEndereco().getCep());
        departamento.setText(tecnicoADM.getDepartamento());

        salario.setText(String.format("R$ %.2f",tecnicoADM.getSalario()));

        profissao.setText("Tecnico");
        disciplinasesalubridade.setText("Salubridade/Gratificação");
        if(tecnicoADM.getGenero() != null) {
            genero.setText(tecnicoADM.getGenero().toString());
        }
        if(tecnicoADM.isInsalubridade() && tecnicoADM.isFuncaoGratificada()){
            disciplinas.setText("Insalubre Gratificada");
        }else if(tecnicoADM.isInsalubridade() && !tecnicoADM.isFuncaoGratificada()){
            disciplinas.setText("Insalubre não-gratificada");
        }else if(!tecnicoADM.isInsalubridade() && tecnicoADM.isFuncaoGratificada()){
            disciplinas.setText("Salubre Gratificada");
        }else if(!tecnicoADM.isInsalubridade() && !tecnicoADM.isFuncaoGratificada()){
            disciplinas.setText("Salubre não-gratificada");
        }

        if(tecnicoADM.getFormacaoTecnico() != null) {
            formacao.setText(( tecnicoADM).getFormacaoTecnico().toString());
        }
        if(tecnicoADM.getNivelTecnico() != null) {
            nivel.setText((tecnicoADM).getNivelTecnico().toString());
        }
        if(tecnicoADM.getDataNascimento() != null) {
            dataNascimento.setText(tecnicoADM.getDataNascimento().toString());
        }
        if(tecnicoADM.getDataIngresso() != null) {
            dataIngresso.setText(tecnicoADM.getDataIngresso().toString());
        }
        if(tecnicoADM.getMatricula() != null) {
            matricula.setText(tecnicoADM.getMatricula().toString());
        }
        cargaHoraria.setText(Integer.toString(tecnicoADM.getCargaHoraria()));
        numero.setText(Integer.toString(tecnicoADM.getEndereco().getNumero()));
    }

    @FXML
    public void remover(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar remoção");
        alert.setHeaderText("Deseja remover "+ tempPerson.getName() +"?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Removendo " + tempPerson.getName());
            banco.getFuncionarios().remove(tempPerson);
            System.out.println("Removido!");
            voltarAoMenu(event);
        }else{
            System.out.println("Remoção cancelada");
        }
    }

}
