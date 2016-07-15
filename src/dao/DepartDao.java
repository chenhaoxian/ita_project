package dao;

import java.util.List;

import po.Depart;

public interface DepartDao {
public int addDepart(Depart depart);
public int deleteDepart(int id);
public int  updateDepart(Depart depart);
public List<Depart> showAllDepart();
public Depart loadDepart(int id);
}
