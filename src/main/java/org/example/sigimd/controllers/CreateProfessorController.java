package org.example.sigimd.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.sigimd.database.BancoDAO;
import org.example.sigimd.enums.Formacao;
import org.example.sigimd.enums.Genero;
import org.example.sigimd.enums.Nivel;
import org.example.sigimd.models.Endereco;
import org.example.sigimd.models.Professor;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class CreateProfessorController {

    BancoDAO banco = BancoDAO.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    //-----------------------Error Labels----------------------//
    @FXML
    private Label nameErrorLabel;
    @FXML
    private Label cpfErrorLabel;
    @FXML
    private Label nascimentoErrorLabel;
    @FXML
    private Label generoErrorLabel;
    @FXML
    private Label disciplinaErrorLabel;
    @FXML
    private Label departamentoErrorLabel;
    @FXML
    private Label formacaoErrorLabel;
    @FXML
    private Label nivelErrorLabel;

    @FXML
    private Label matriculaErrorLabel;
    @FXML
    private Label cargaErrorLabel;
    @FXML
    private Label ingressoErrorLabel;
    @FXML
    private Label ruaErrorLabel;
    @FXML
    private Label numeroErrorLabel;
    @FXML
    private Label cidadeErrorLabel;
    @FXML
    private Label bairroErrorLabel;
    @FXML
    private Label cepErrorLabel;

//--------------------------- Fim Error Labels ------------------------------//

//---------------------------- Campos de Registro -----------------------------//
    @FXML
    private ListView<String> disciplinasListView;
    @FXML
    private ChoiceBox<Nivel> nivelChoiceBox;
    @FXML
    private ChoiceBox<Formacao> formacaoChoiceBox;
    @FXML
    private ChoiceBox<Genero> generoChoiceBox;
    @FXML
    private DatePicker nascimentoDatePicker;
    @FXML
    private DatePicker ingressoDatePicker;

    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField cpfTextField;

    @FXML
    private TextField matriculaTextField;
    @FXML
    private TextField cargaTextField;
    @FXML
    private TextField departamentoTextField;
    @FXML
    private TextField ruaTextField;
    @FXML
    private TextField numeroTextField;
    @FXML
    private TextField cidadeTextField;
    @FXML
    private TextField bairroTextField;
    @FXML
    private TextField cepTextField;

    private Map<Label,TextField> labelTextFieldMap = new HashMap<>();

    private  Map<Label,DatePicker> labelDatePickers = new HashMap<>();

//-------------------------------------Fim Campos de Registro----------------------------------------------//
    @FXML
    public void initialize() {

        //Inicializando Maps
        initMapsEListas();

        //Inicializando as mensagens de erro como invisiveis
        desativaErros();

    }

    public void addProfessor(ActionEvent event){
        if (faltouAlgumCampo()){
            return;
        }

//    public Professor(String name, String cpf, LocalDate dataNascimento, Genero genero, Endereco endereco,
//    Long matricula, String departamento, int cargaHoraria, LocalDate dataIngresso,
//    Nivel nivelProfessor, Formacao formacaoProfessor, List<String> disciplinas)
//
        Professor professor = new Professor(nomeTextField.getText(),cpfTextField.getText(),nascimentoDatePicker.getValue(),generoChoiceBox.getValue(),
                new Endereco(ruaTextField.getText(),Integer.parseInt(numeroTextField.getText()),bairroTextField.getText(),cidadeTextField.getText(),cepTextField.getText()),
                Long.parseLong(matriculaTextField.getText()),departamentoTextField.getText(),Integer.parseInt(cargaTextField.getText()),ingressoDatePicker.getValue(),
                nivelChoiceBox.getValue(),formacaoChoiceBox.getValue(), disciplinasListView.getSelectionModel().getSelectedItems()
        );

       banco.getFuncionarios().add(professor);

        voltarAoMenu(event);
    }

    public  void voltarAoMenu(ActionEvent event){
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

    //Verificar se existe algum campo vazio para acionar a ErrorLabel e parar a criação de um professor
    public boolean faltouAlgumCampo(){
        boolean temError=false;
        //Desativa todos os errorlabels

        desativaErros();

        //--------------------------Confere se tem algum campo de texto vazio--------------------//

        //Confere text fields
        for(Map.Entry<Label,TextField> e : labelTextFieldMap.entrySet()) {
            if(e.getValue().getText().isEmpty()){
                temError=true;
            e.getKey().setVisible(true);
            }
        }
        for(Map.Entry<Label,DatePicker> e : labelDatePickers.entrySet()) {
            if(e.getValue().getValue() == null){
            e.getKey().setVisible(true);
            temError=true;
            }
        }

        //Confere se cada uma das choiceBoxes tem elementos nulos
        if(generoChoiceBox.getValue() == null){
            generoErrorLabel.setVisible(true);
            temError=true;
        }
        if(formacaoChoiceBox.getValue() == null){
            formacaoErrorLabel.setVisible(true);
            temError=true;
        }
        if(nivelChoiceBox.getValue() == null){
            nivelErrorLabel.setVisible(true);
            temError=true;
        }

        //Se a lista de disciplinas não tiver nenhum selecionado ativa a error label
        if(disciplinasListView.getSelectionModel().isEmpty()){
            disciplinaErrorLabel.setVisible(true);
            temError=true;
        }

        //--------------------------FIM se tem algum campo de texto vazio--------------------//


        return temError;

    }
    public void desativaErros(){
        //Desativa text fields
        for(Map.Entry<Label,TextField> e : labelTextFieldMap.entrySet()) {
            e.getKey().setVisible(false);
        }
        //Desativa date pickers
        for(Map.Entry<Label,DatePicker> e : labelDatePickers.entrySet()) {
            e.getKey().setVisible(false);
        }

        disciplinaErrorLabel.setVisible(false);
        generoErrorLabel.setVisible(false);
        formacaoErrorLabel.setVisible(false);
        nivelErrorLabel.setVisible(false);

    }
    public void initMapsEListas(){

        ObservableList<String> disciplinas = FXCollections.observableArrayList(
                "Estruturas de dados I","Cálculo I","Linguagem de Programação I","FMC I","Lingua Portuguesa I"
        );

        labelTextFieldMap.put(nameErrorLabel,nomeTextField);
        labelTextFieldMap.put(cpfErrorLabel,cpfTextField);
        labelTextFieldMap.put(matriculaErrorLabel,matriculaTextField);
        labelTextFieldMap.put(cargaErrorLabel,cargaTextField);
        labelTextFieldMap.put(departamentoErrorLabel,departamentoTextField);
        labelTextFieldMap.put(ruaErrorLabel,ruaTextField);
        labelTextFieldMap.put(numeroErrorLabel,numeroTextField);
        labelTextFieldMap.put(cidadeErrorLabel,cidadeTextField);
        labelTextFieldMap.put(bairroErrorLabel,bairroTextField);
        labelTextFieldMap.put(cepErrorLabel,cepTextField);

        labelDatePickers.put(ingressoErrorLabel,ingressoDatePicker);
        labelDatePickers.put(nascimentoErrorLabel,nascimentoDatePicker);

        disciplinasListView.setItems(disciplinas);
        disciplinasListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);

        generoChoiceBox.getItems().addAll(Genero.values());
        nivelChoiceBox.getItems().addAll(Nivel.values());
        formacaoChoiceBox.getItems().addAll(Formacao.values());
    }

}
