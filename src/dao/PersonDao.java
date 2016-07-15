package dao;

import java.util.List;

import po.Person;

public interface PersonDao {
public int addPerson(Person person);
public int deletePerson(int id);
public int  updatePerson(Person person);
public List<Person> showAllPerson();
public Person loadPerson(int id);
}
