package org.example.sigimd.database;

import org.example.sigimd.models.Person;

import java.util.ArrayList;

public class BancoDAO {
    private ArrayList<Person> funcionarios;

    private static BancoDAO bancoDAO;

    private BancoDAO() {
        funcionarios = new ArrayList<>();
    }
    public static BancoDAO getInstance() {
        if(bancoDAO == null) {
            bancoDAO = new BancoDAO();
        }
        return bancoDAO;
    }
    public ArrayList<Person> getFuncionarios() {
        return funcionarios;
    }

}
