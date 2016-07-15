package po;

public class Depart {
	private Integer id;
	private String dname;
	private String city;
	public Integer getId() {
		return id;
	}
	public Depart(String dname, String city) {
		super();
		this.dname = dname;
		this.city = city;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
