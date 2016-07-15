package po;

public class Person {
	private Integer id;
	private String cname;
	private long birth;
	private long tel;
	private int departId;
	private long salary;
	public Person(String cname, long birth, long tel, int departId, long salary) {
		super();
		this.cname = cname;
		this.birth = birth;
		this.tel = tel;
		this.departId = departId;
		this.salary = salary;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public long getBirth() {
		return birth;
	}
	public void setBirth(long birth) {
		this.birth = birth;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
}
