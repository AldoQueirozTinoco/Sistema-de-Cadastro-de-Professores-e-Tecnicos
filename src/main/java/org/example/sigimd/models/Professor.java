package org.example.sigimd.models;

import org.example.sigimd.enums.Formacao;
import org.example.sigimd.enums.Genero;
import org.example.sigimd.enums.Nivel;
import org.example.sigimd.interfaces.Funcionario;

import java.time.LocalDate;
import java.util.List;

public class Professor extends Person implements Funcionario {

    private Nivel nivelProfessor;
    private Formacao formacaoProfessor;
    private List<String> disciplinas;

    public Professor() {}

    public Professor(String name, String cpf, LocalDate dataNascimento, Genero genero, Endereco endereco, Long matricula, String departamento, int cargaHoraria, LocalDate dataIngresso, Nivel nivelProfessor, Formacao formacaoProfessor, List<String> disciplinas) {
        super(name, cpf, dataNascimento, genero, endereco, matricula, departamento, cargaHoraria, dataIngresso);
        this.nivelProfessor = nivelProfessor;
        this.formacaoProfessor = formacaoProfessor;
        this.disciplinas = disciplinas;

        calculaSalario();
    }

    public Nivel getNivelProfessor() {
        return nivelProfessor;
    }

    public void setNivelProfessor(Nivel nivelProfessor) {
        this.nivelProfessor = nivelProfessor;
    }

    public Formacao getFormacaoProfessor() {
        return formacaoProfessor;
    }

    public void setFormacaoProfessor(Formacao formacaoProfessor) {
        this.formacaoProfessor = formacaoProfessor;
    }

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public void calculaSalario() {
        double salarioBase = 4000.00;
        salarioBase *= Math.pow(1.05, (this.nivelProfessor.ordinal() + 1));

        switch (this.formacaoProfessor) {
            case Formacao.ESPECIALIZACAO:
                salarioBase *= 1.25;
                break;
            case Formacao.MESTRADO:
                salarioBase *= 1.5;
                break;
            case Formacao.DOUTORADO:
                salarioBase *= 1.75;
                break;
        }
        this.setSalario(salarioBase);

    }
}
