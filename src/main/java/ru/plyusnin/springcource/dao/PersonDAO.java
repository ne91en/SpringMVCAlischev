package ru.plyusnin.springcource.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.plyusnin.springcource.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
//    private static int PEOPLE_COUNT;
//    private List<Person> people;
//
//    {
//        people = new ArrayList<>();
//        people.add(new Person(PEOPLE_COUNT++, 12, "someUser1@gmail.com", "SomeName1"));
//        people.add(new Person(PEOPLE_COUNT++, 13, "someUser2@gmail.com", "SomeName2"));
//        people.add(new Person(PEOPLE_COUNT++, 14, "someUser3@gmail.com", "SomeName3"));
//        people.add(new Person(PEOPLE_COUNT++, 15, "someUser4@gmail.com", "SomeName4"));
//        people.add(new Person(PEOPLE_COUNT++, 16, "someUser5@gmail.com", "SomeName5"));
//    }

    private static final String URL = "jdbc:postgresql://localhost:5432/first db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "pass";

    private static Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /////////////////////////////////////////////
    public List<Person> index(){
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");
            System.out.println(resultSet.toString() + "RESULTSET===================");
            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                System.out.println(person.getId() + "=============================================================================");
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }
    /////////////////////////////////////////////
    public Person show(int id){
        Person person = new Person();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }
    /////////////////////////////////////////////
    public void addPerson(Person person){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person values (1,?,?,?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /////////////////////////////////////////////
    public void editPerson(int id, Person person){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person Set name = ?, age = ?, email = ? WHERE id = ?");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /////////////////////////////////////////////
    public void deletePerson(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /////////////////////////////////////////////
}
