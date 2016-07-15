package po;

public class Customer {
	private Integer id;
	private String cname;
	private int  age;
	public Customer(String cname, int age) {
		super();
		this.cname = cname;
		this.age = age;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
