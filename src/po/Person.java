package po;

public class Person {
	private Integer id;
	private String cname;
	private String birth;
	private String tel;
	private int departId;
	private int salary;
	public Person(String cname, String birth, String tel, int departId, int salary) {
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
}
