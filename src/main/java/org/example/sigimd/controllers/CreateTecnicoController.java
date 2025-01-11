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
import org.example.sigimd.models.TecnicoADM;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class CreateTecnicoController implements Initializable {

    BancoDAO banco = BancoDAO.getInstance();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private RadioButton GratificadoNao;

    @FXML
    private Label bairroErrorLabel;

    @FXML
    private TextField bairroTextField;

    @FXML
    private Label cargaErrorLabel;

    @FXML
    private TextField cargaTextField;

    @FXML
    private Label cepErrorLabel;

    @FXML
    private TextField cepTextField;

    @FXML
    private Label cidadeErrorLabel;

    @FXML
    private TextField cidadeTextField;

    @FXML
    private Label cpfErrorLabel;

    @FXML
    private TextField cpfTextField;

    @FXML
    private Label departamentoErrorLabel;

    @FXML
    private TextField departamentoTextField;

    @FXML
    private Label insalubreErrorLabel;

    @FXML
    private Label funcaoErrorLabel;

    @FXML
    private ChoiceBox<Formacao> formacaoChoiceBox;

    @FXML
    private Label formacaoErrorLabel;

    @FXML
    private ChoiceBox<Genero> generoChoiceBox;

    @FXML
    private Label generoErrorLabel;

    @FXML
    private RadioButton gratificadoSim,gratificadoNao;

    @FXML
    private DatePicker ingressoDatePicker;

    @FXML
    private Label ingressoErrorLabel;

    @FXML
    private RadioButton insalubreSim,insalubreNao;

    @FXML
    private Label matriculaErrorLabel;

    @FXML
    private TextField matriculaTextField;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private DatePicker nascimentoDatePicker;

    @FXML
    private Label nascimentoErrorLabel;

    @FXML
    private ChoiceBox<Nivel> nivelChoiceBox;

    @FXML
    private Label nivelErrorLabel;

    @FXML
    private TextField nomeTextField;

    @FXML
    private Label numeroErrorLabel;

    @FXML
    private TextField numeroTextField;

    @FXML
    private Label ruaErrorLabel;

    @FXML
    private TextField ruaTextField;

    private Map<Label,TextField> labelTextFieldMap = new HashMap<>();

    private  Map<Label,DatePicker> labelDatePickers = new HashMap<>();

    private final ToggleGroup insalubreTg = new ToggleGroup();

    private final ToggleGroup gratificadoTg = new ToggleGroup();

    //------------------------Fim Campos de Registro ------------------------------------------------

    public void voltarAoMenu(ActionEvent event) {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Inicializando Maps
        initMapsEListas();

        //Inicializando as mensagens de erro como invisiveis
        desativaErros();

        insalubreSim.setToggleGroup(insalubreTg);
        insalubreNao.setToggleGroup(insalubreTg);

        gratificadoSim.setToggleGroup(gratificadoTg);
        gratificadoNao.setToggleGroup(gratificadoTg);
    }

    public void addTecnico(ActionEvent event){
        if(faltouAlgumCampo()){
            return;
        }
        boolean salubridade=false,funcaoGratificada=false;

        //    public TecnicoADM(String name, String cpf, LocalDate dataNascimento, Genero genero,
        //    Endereco endereco, Long matricula, String departamento, int cargaHoraria, LocalDate dataIngresso,
        //    Nivel nivelTecnico, Formacao formacaoTecnico, boolean insalubridade, boolean funcaoGratificada) {

       if(insalubreSim.isSelected()){
           salubridade=true;
       }
       if(insalubreNao.isSelected()){
           salubridade =false;
       }
       if(gratificadoSim.isSelected()){
           funcaoGratificada=true;
       }
       if(gratificadoNao.isSelected()){
           funcaoGratificada=false;
       }

        TecnicoADM tecnico = new TecnicoADM(nomeTextField.getText(),cpfTextField.getText(),nascimentoDatePicker.getValue(),generoChoiceBox.getValue(),
                new Endereco(ruaTextField.getText(),Integer.parseInt(numeroTextField.getText()),bairroTextField.getText(),cidadeTextField.getText(),cepTextField.getText()),
                Long.parseLong(matriculaTextField.getText()),departamentoTextField.getText(),Integer.parseInt(cargaTextField.getText()),ingressoDatePicker.getValue(),nivelChoiceBox.getValue(),
                formacaoChoiceBox.getValue(),salubridade,funcaoGratificada
                );


        banco.getFuncionarios().add(tecnico);

        voltarAoMenu(event);

    }

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
        if(!gratificadoSim.isSelected() &&!gratificadoNao.isSelected()){
            temError=true;
        }
        if(!insalubreNao.isSelected() &&!insalubreSim.isSelected()){
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

        funcaoErrorLabel.setVisible(false);
        insalubreErrorLabel.setVisible(false);

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


        generoChoiceBox.getItems().addAll(Genero.values());
        nivelChoiceBox.getItems().addAll(Nivel.values());
        formacaoChoiceBox.getItems().addAll(Formacao.values());
    }

}
