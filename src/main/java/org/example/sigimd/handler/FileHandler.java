package org.example.sigimd.handler;

import org.example.sigimd.database.BancoDAO;
import org.example.sigimd.models.Person;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void write(ArrayList<Person> people){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("People.txt"))){
            oos.writeObject(people);
            System.out.println("File saved");

        }catch(IOException e){
            System.err.println("Deu problema na hora de colocar o objeto no arquivo");
        }
    }
    public static void read(ArrayList<Person> people){


        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("People.txt"))){
            ArrayList<Person> persons = (ArrayList<Person>) ois.readObject();

            people.addAll(persons);

        }catch(IOException | ClassNotFoundException e){
            System.err.println("Erro ao recuperar os objetos");
            e.printStackTrace();
        }

        System.out.println("Produtos recuperados com sucesso!");
    }
}
