package org.example.sigimd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.sigimd.database.BancoDAO;
import org.example.sigimd.enums.Formacao;
import org.example.sigimd.enums.Genero;
import org.example.sigimd.enums.Nivel;
import org.example.sigimd.models.Endereco;
import org.example.sigimd.models.Person;
import org.example.sigimd.models.Professor;
import org.example.sigimd.models.TecnicoADM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.example.sigimd.handler.FileHandler.read;
import static org.example.sigimd.handler.FileHandler.write;

public class Main extends Application {

    BancoDAO banco = BancoDAO.getInstance();


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        read(banco.getFuncionarios());

       // banco.getFuncionarios().removeAll(banco.getFuncionarios());

//        Professor p1 = new Professor("Ana Souza", "12345678901", LocalDate.of(1985, 5, 15), Genero.FEM, new Endereco("Rua A", 123, "Centro", "Natal", "59000-000"), 12345L, "Matemática", 20, LocalDate.of(2010, 3, 1), Nivel.III, Formacao.MESTRADO, Arrays.asList("Álgebra", "Geometria"));
//        Professor p2 = new Professor("Carlos Silva", "23456789012", LocalDate.of(1978, 7, 22), Genero.MASC, new Endereco("Rua B", 456, "Bairro das Flores", "Mossoró", "59600-000"), 23456L, "Física", 18, LocalDate.of(2009, 5, 12), Nivel.IV, Formacao.DOUTORADO, Arrays.asList("Mecânica", "Termodinâmica"));
//        Professor p3 = new Professor("Roberta Lima", "34567890123", LocalDate.of(1992, 2, 3), Genero.FEM, new Endereco("Rua C", 789, "Jardim", "Parnamirim", "59100-000"), 34567L, "Química", 22, LocalDate.of(2015, 8, 22), Nivel.II, Formacao.ESPECIALIZACAO, Arrays.asList("Química Orgânica", "Bioquímica"));
//        Professor p4 = new Professor("João Pereira", "45678901234", LocalDate.of(1980, 10, 10), Genero.MASC, new Endereco("Rua D", 321, "Vila Nova", "Caicó", "59200-000"), 45678L, "Biologia", 25, LocalDate.of(2007, 1, 15), Nivel.V, Formacao.MESTRADO, Arrays.asList("Ecologia", "Genética"));
//        Professor p5 = new Professor("Mariana Costa", "56789012345", LocalDate.of(1988, 12, 30), Genero.FEM, new Endereco("Rua E", 654, "São João", "Currais Novos", "59300-000"), 56789L, "História", 24, LocalDate.of(2012, 6, 18), Nivel.III, Formacao.DOUTORADO, Arrays.asList("História Contemporânea", "História do Brasil"));
//        Professor p6 = new Professor("Ricardo Oliveira", "67890123456", LocalDate.of(1975, 4, 5), Genero.MASC, new Endereco("Rua F", 987, "Lagoa", "Serra do Mel", "59500-000"), 67890L, "Geografia", 30, LocalDate.of(2003, 11, 9), Nivel.VI, Formacao.ESPECIALIZACAO, Arrays.asList("Cartografia", "Geopolítica"));
//        Professor p7 = new Professor("Fernanda Rocha", "78901234567", LocalDate.of(1990, 11, 1), Genero.FEM, new Endereco("Rua G", 432, "Centro", "João Câmara", "59400-000"), 78901L, "Literatura", 15, LocalDate.of(2017, 7, 23), Nivel.II, Formacao.MESTRADO, Arrays.asList("Literatura Brasileira", "Literatura Portuguesa"));
//        Professor p8 = new Professor("Eduardo Martins", "89012345678", LocalDate.of(1982, 9, 14), Genero.MASC, new Endereco("Rua H", 567, "Santa Maria", "Açu", "59510-000"), 89012L, "Filosofia", 20, LocalDate.of(2010, 2, 2), Nivel.IV, Formacao.DOUTORADO, Arrays.asList("Filosofia Contemporânea", "Ética"));
//        Professor p9 = new Professor("Juliana Costa", "90123456789", LocalDate.of(1995, 1, 28), Genero.FEM, new Endereco("Rua I", 135, "Novo Horizonte", "São Gonçalo do Amarante", "59120-000"), 90123L, "Artes", 20, LocalDate.of(2020, 10, 10), Nivel.I, Formacao.ESPECIALIZACAO, Arrays.asList("Artes Plásticas", "Escultura"));
//        Professor p10 = new Professor("Ricardo Mendes", "11223344556", LocalDate.of(1983, 6, 18), Genero.MASC, new Endereco("Rua J", 246, "Boa Vista", "Assu", "59210-000"), 11234L, "Sociologia", 22, LocalDate.of(2008, 4, 5), Nivel.V, Formacao.MESTRADO, Arrays.asList("Sociologia Política", "Sociologia da Educação"));
//
//        // Adicionando os professores ao banco ou lista, por exemplo:
//        banco.getFuncionarios().add(p1);
//        banco.getFuncionarios().add(p2);
//        banco.getFuncionarios().add(p3);
//        banco.getFuncionarios().add(p4);
//        banco.getFuncionarios().add(p5);
//        banco.getFuncionarios().add(p6);
//        banco.getFuncionarios().add(p7);
//        banco.getFuncionarios().add(p8);
//        banco.getFuncionarios().add(p9);
//        banco.getFuncionarios().add(p10);
//
//        TecnicoADM t1 = new TecnicoADM("Lucas Almeida", "21345678901", LocalDate.of(1990, 8, 12), Genero.MASC, new Endereco("Rua L", 101, "Centro", "Mossoró", "59610-000"), 32145L, "Administração", 40, LocalDate.of(2012, 2, 14), Nivel.III, Formacao.MESTRADO, true, false);
//        TecnicoADM t2 = new TecnicoADM("Beatriz Oliveira", "32456789012", LocalDate.of(1985, 6, 23), Genero.FEM, new Endereco("Rua M", 202, "Vila Verde", "Natal", "59010-000"), 43256L, "Recursos Humanos", 35, LocalDate.of(2015, 4, 6), Nivel.IV, Formacao.ESPECIALIZACAO, false, true);
//        TecnicoADM t3 = new TecnicoADM("Ricardo Silva", "43567890123", LocalDate.of(1982, 5, 10), Genero.MASC, new Endereco("Rua N", 303, "Pau dos Ferros", "Pau dos Ferros", "59900-000"), 54367L, "Financeiro", 30, LocalDate.of(2010, 11, 20), Nivel.V, Formacao.DOUTORADO, false, true);
//        TecnicoADM t4 = new TecnicoADM("Joana Lima", "54678901234", LocalDate.of(1992, 4, 15), Genero.FEM, new Endereco("Rua O", 404, "Jardim de Alah", "São Gonçalo do Amarante", "59130-000"), 65478L, "Secretaria", 38, LocalDate.of(2017, 1, 2), Nivel.II, Formacao.MESTRADO, true, false);
//        TecnicoADM t5 = new TecnicoADM("Carlos Santos", "65789012345", LocalDate.of(1994, 9, 25), Genero.MASC, new Endereco("Rua P", 505, "Conjunto Candelária", "Parnamirim", "59100-100"), 76589L, "Logística", 45, LocalDate.of(2020, 7, 18), Nivel.VI, Formacao.DOUTORADO, false, false);
//        TecnicoADM t6 = new TecnicoADM("Fernanda Pereira", "76890123456", LocalDate.of(1988, 3, 11), Genero.FEM, new Endereco("Rua Q", 606, "Bairro do Sol", "Ceará-Mirim", "59560-000"), 87690L, "Contabilidade", 40, LocalDate.of(2012, 7, 5), Nivel.VI, Formacao.ESPECIALIZACAO, false, true);
//        TecnicoADM t7 = new TecnicoADM("Gustavo Oliveira", "87901234567", LocalDate.of(1990, 1, 20), Genero.MASC, new Endereco("Rua R", 707, "Vila Real", "Mossoró", "59620-000"), 98701L, "Tecnologia", 37, LocalDate.of(2015, 9, 12), Nivel.III, Formacao.MESTRADO, true, false);
//        TecnicoADM t8 = new TecnicoADM("Patrícia Costa", "98012345678", LocalDate.of(1984, 7, 30), Genero.FEM, new Endereco("Rua S", 808, "Novo Horizonte", "São João de Meriti", "59150-000"), 10901L, "Marketing", 42, LocalDate.of(2009, 12, 10), Nivel.IV, Formacao.DOUTORADO, false, true);
//        TecnicoADM t9 = new TecnicoADM("Eduardo Martins", "12345678901", LocalDate.of(1989, 11, 5), Genero.MASC, new Endereco("Rua T", 909, "Centro", "Assu", "59220-000"), 21012L, "Suprimentos", 34, LocalDate.of(2011, 8, 3), Nivel.II, Formacao.ESPECIALIZACAO, true, false);
//        TecnicoADM t10 = new TecnicoADM("Larissa Almeida", "13456789012", LocalDate.of(1993, 12, 18), Genero.FEM, new Endereco("Rua U", 1010, "Parque das Árvores", "Parnamirim", "59130-100"), 11223L, "Infraestrutura", 40, LocalDate.of(2014, 4, 17), Nivel.V, Formacao.MESTRADO, false, true);
//
//
//        // Adicionando os técnicos ao banco ou lista, por exemplo:
//        banco.getFuncionarios().add(t1);
//        banco.getFuncionarios().add(t2);
//        banco.getFuncionarios().add(t3);
//        banco.getFuncionarios().add(t4);
//        banco.getFuncionarios().add(t5);
//        banco.getFuncionarios().add(t6);
//        banco.getFuncionarios().add(t7);
//        banco.getFuncionarios().add(t8);
//        banco.getFuncionarios().add(t9);
//        banco.getFuncionarios().add(t10);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Main menu");
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            event.consume();

            handleExit(event);
        });
        stage.show();
    }

    private void handleExit(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar saída");
        alert.setHeaderText("Deseja sair da aplicação?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Salvando dados no arquivo...");
            write(banco.getFuncionarios());
            System.exit(0);
        }else{
            System.out.println("Saída cancelada");
        }
    }
}