package org.example.sigimd.models;

import org.example.sigimd.enums.Formacao;
import org.example.sigimd.enums.Genero;
import org.example.sigimd.enums.Nivel;
import org.example.sigimd.interfaces.Funcionario;

import java.time.LocalDate;

public class TecnicoADM extends Person implements Funcionario {
    private Nivel nivelTecnico;
    private Formacao formacaoTecnico;
    private boolean insalubridade;
    private boolean funcaoGratificada;

    public TecnicoADM() {}

    public TecnicoADM(String name, String cpf, LocalDate dataNascimento, Genero genero, Endereco endereco, Long matricula, String departamento, int cargaHoraria, LocalDate dataIngresso, Nivel nivelTecnico, Formacao formacaoTecnico, boolean insalubridade, boolean funcaoGratificada) {
        super(name, cpf, dataNascimento, genero, endereco, matricula, departamento, cargaHoraria, dataIngresso);
        this.nivelTecnico = nivelTecnico;
        this.formacaoTecnico = formacaoTecnico;
        this.insalubridade = insalubridade;
        this.funcaoGratificada = funcaoGratificada;

        calculaSalario();
    }

    public Nivel getNivelTecnico() {
        return nivelTecnico;
    }

    public void setNivelTecnico(Nivel nivelTecnico) {
        this.nivelTecnico = nivelTecnico;
    }

    public Formacao getFormacaoTecnico() {
        return formacaoTecnico;
    }

    public void setFormacaoTecnico(Formacao formacaoTecnico) {
        this.formacaoTecnico = formacaoTecnico;
    }

    public boolean isInsalubridade() {
        return insalubridade;
    }

    public void setInsalubridade(boolean insalubridade) {
        this.insalubridade = insalubridade;
    }

    public boolean isFuncaoGratificada() {
        return funcaoGratificada;
    }

    public void setFuncaoGratificada(boolean funcaoGratificada) {
        this.funcaoGratificada = funcaoGratificada;
    }

    @Override
    public void calculaSalario() {
        double salarioBase = 2500.00;
        salarioBase *= Math.pow(1.03, (this.nivelTecnico.ordinal() + 1));
        switch (this.formacaoTecnico) {
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
        if(insalubridade) {
            salarioBase *= 1.5;
        }
        if(funcaoGratificada) {
            salarioBase *= 1.5;
        }
        this.setSalario(salarioBase);
    }
}
