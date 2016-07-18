package oocl.server.dao;

import java.util.List;

import oocl.po.Depart;
import oocl.po.Person;

public interface ShowListDao {


	public List<Depart> showAllDepart();
	public List<Person> showAllPerson();
	public List<Person> findPersonByDid(int id);
}
