package ru.plyusnin.springcource.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.plyusnin.springcource.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(PEOPLE_COUNT++, 12, "someUser1@gmail.com", "SomeName1"));
        people.add(new Person(PEOPLE_COUNT++, 13, "someUser2@gmail.com", "SomeName2"));
        people.add(new Person(PEOPLE_COUNT++, 14, "someUser3@gmail.com", "SomeName3"));
        people.add(new Person(PEOPLE_COUNT++, 15, "someUser4@gmail.com", "SomeName4"));
        people.add(new Person(PEOPLE_COUNT++, 16, "someUser5@gmail.com", "SomeName5"));
    }
    public List<Person> index(){
        return people;
    }
    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public void addPerson(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    public void editPerson(int id, Person person){
        Person person1 = show(id);
        person1.setName(person.getName());
        person1.setAge(person.getAge());
        person1.setEmail(person.getEmail());
    }
    public void deletePerson(int id){
//        people.remove(people.indexOf(show(id)));
        people.removeIf(p->p.getId()==id);
    }
}
