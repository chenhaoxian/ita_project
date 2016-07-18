package oocl.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oocl.po.Depart;
import oocl.po.Person;
import oocl.server.dao.AddDao;
import oocl.server.dao.DeleteDao;
import oocl.server.dao.ShowListDao;
import oocl.server.dao.UpdateDao;
import oocl.server.dao.impl.AddDaoImpl;
import oocl.server.dao.impl.DeleteDaoImpl;
import oocl.server.dao.impl.ShowListDaoImpl;
import oocl.server.dao.impl.UpdateDaoImpl;

public class CheckInput {

	private static AddDao addDao = new AddDaoImpl();
	private static DeleteDao deleteDao = new DeleteDaoImpl();
	private static ShowListDao showListDao = new ShowListDaoImpl();
	private static UpdateDao updateDao = new UpdateDaoImpl();

	public static boolean check(String line) {

		if ("Q".equals(line.toUpperCase())) {
			return true;
		} else {
			String rex = "[ADUL]{1}-[A-Z]{1,2}\\s{0,}.*";
			Pattern p = Pattern.compile(rex);
			Matcher m = p.matcher(line);

			if (m.find()) {
				return true;
			} else {
				return false;
			}
		}
	}

	public static String checkCommand(String clientInputStr) {
		String command = null;

		String rs = null;

		if (clientInputStr.split("\\s+").length == 1 && "Q" != clientInputStr) {
			// out.writeUTF("ok");
			if ("L-PA".equals(clientInputStr)) {
				List<Person> list = showListDao.showAllPerson();
				StringBuilder sb = new StringBuilder();
				sb.append("pid").append("\t\t").append("name").append("\t\t").append("city").append("\t\t").append("Birth")
						.append("\t\t").append("Salary").append("\t\t").append("Tel").append("\t\t").append("DepartId")
						.append("\t\t").append("Dname").append("#");
				for (Person person : list) {
					sb.append(person.getId()).append("\t\t").append(person.getCname()).append("\t\t").append(person.getCity())
							.append("\t\t").append(person.getBirth()).append("\t\t").append(person.getSalary()).append("\t\t")
							.append(person.getTel()).append("\t\t").append(person.getDepartId()).append("\t\t")
							.append(person.getDname()).append("#");
				}
				// out.writeUTF(sb.toString());
				rs = sb.toString();
			} else if ("L-DA".equals(clientInputStr)) {
				List<Depart> list = showListDao.showAllDepart();
				StringBuilder sb = new StringBuilder();
				sb.append("dId").append("\t\t").append("Dname").append("\t\t").append("City").append("#");
				for (Depart depart : list) {
					sb.append(depart.getId()).append("\t\t").append(depart.getDname()).append("\t\t").append(depart.getCity())
							.append("#");
				}
				// out.writeUTF(sb.toString());
				rs = sb.toString();
			} else {
				// out.writeUTF("null");
				rs = "null";
			}
		} else if (clientInputStr.split("\\s+").length == 2)

		{
			command = clientInputStr.split("\\s+")[0].trim();
			String value = clientInputStr.split("\\s+")[1].trim();

			switch (command) {
			case ("A-P"): {
				if (value.toLowerCase().contains("pname")) {
					Person p = new Person();
					String[] data = value.split(",");
					for (int i = 0; i < data.length; i++) {
						if (data[i].toLowerCase().startsWith("pname")) {
							p.setCname(data[i].split(":")[1].trim());
						} else if (data[i].toLowerCase().startsWith("did")) {
							p.setId(Integer.parseInt(data[i].split(":")[1].trim()));
						} else if (data[i].toLowerCase().startsWith("birth")) {
							p.setBirth(data[i].split(":")[1].trim());
						} else if (data[i].toLowerCase().startsWith("tel")) {
							p.setTel(data[i].split(":")[1].trim());
						} else if (data[i].toLowerCase().startsWith("salary")) {
							p.setSalary(Integer.parseInt(data[i].split(":")[1].trim()));
						}
					}
					int result = addDao.addPerson(p);
					if (result != 0) {
						// out.writeUTF("success A-P");
						rs = "success A-P";
						;
					} else {
						// out.writeUTF("fail A-P");
						rs = "fail A-P";
					}

				} else {
					// out.writeUTF("Input error");
					rs = "Input error";
				}

				break;
			}
			case ("A-D"): {
				if (value.toLowerCase().contains("dname")) {
					Depart depart = new Depart();
					String[] data = value.split(",");
					for (int i = 0; i < data.length; i++) {
						if (data[i].split(":")[i].startsWith("dname")) {
							depart.setDname(value.split(":")[1].trim());
						} else if (data[i].split(":")[i].startsWith("city")) {
							depart.setCity(value.split(":")[1].trim());
						}
					}
					int result = addDao.addDepart(depart);
					if (result == 1) {
						// out.writeUTF("success A-D");
						rs = "success A-D";
					} else {
						// out.writeUTF("fail A-D");
						rs = "fail A-D";
					}

				} else {
					// out.writeUTF("Input error");
					rs = "Input error";
				}
			}

				break;
			case ("U-P"):
				if (value.toLowerCase().contains("pid")) {
					Person p = new Person();
					String[] data = value.split(",");
					for (int i = 0; i < data.length; i++) {
						if (data[i].toLowerCase().startsWith("pname")) {
							p.setCname(data[i].split(":")[1].trim());
						} else if (data[i].toLowerCase().startsWith("pid")) {
							p.setId(Integer.parseInt(data[i].split(":")[1].trim()));
						} else if (data[i].toLowerCase().startsWith("birth")) {
							p.setBirth(data[i].split(":")[1].trim());
						} else if (data[i].toLowerCase().startsWith("tel")) {
							p.setTel(data[i].split(":")[1].trim());
						} else if (data[i].toLowerCase().startsWith("salary")) {
							p.setSalary(Integer.parseInt(data[i].split(":")[1].trim()));
						}
					}
					int result = updateDao.updatePerson(p);
					if (result != 0) {
						// out.writeUTF("success U-P");
						rs = "success U-P";
					} else {
						// out.writeUTF("No this ID");
						rs = "No this ID";
					}

				} else {
					// out.writeUTF("Input error");
					rs = "Input error";
				}

				break;
			case ("U-D"):
				if (value.toLowerCase().contains("did")) {
					Depart depart = new Depart();
					String[] data = value.split(",");
					for (int i = 0; i < data.length; i++) {
						if (data[i].toLowerCase().startsWith("dname")) {
							depart.setDname(data[i].split(":")[1].trim());
						} else if (data[i].toLowerCase().startsWith("did")) {
							depart.setId(Integer.parseInt(data[i].split(":")[1].trim()));
						} else if (data[i].toLowerCase().startsWith("city")) {
							depart.setCity(data[i].split(":")[1].trim());
						}
						int result = updateDao.updateDepart(depart);
						if (result == 1) {
							// out.writeUTF("success U-D");
							rs = "success U-D";
						} else {
							// out.writeUTF("fail U-D");
							rs = "fail U-D";
						}
					}

				} else {
					// out.writeUTF("Input error");
					rs = "Input error";
				}

				break;
			case ("D-P"):
				if (value.toLowerCase().contains("pid")) {
					String[] data = value.split(":");
					int i = Integer.parseInt(data[1]);
					int result = deleteDao.deletePerson(i);
					if (result == 1) {
						// out.writeUTF("success D-P");
						rs = "success D-P";
					} else {
						// out.writeUTF("fail D-P");
						rs = "fail D-P";
					}
				} else {
					// out.writeUTF("Input error");
					rs = "Input error";
				}

				break;
			case ("D-D"):

				if (value.toLowerCase().contains("did")) {
					String[] data = value.split(":");
					int i = Integer.parseInt(data[1]);
					System.out.println(i);
					int result = deleteDao.deleteDepart(i);
					if (result != 0) {
						// out.writeUTF("success D-D");
						rs = "success D-D";
					} else {
						// out.writeUTF("fail D-D");
						rs = "fail D-D";
					}
				} else {
					// out.writeUTF("Input error");
					rs = "Input error";
				}

				break;
			case ("L-PD"):

				if (value.toLowerCase().contains("did") || value.toLowerCase().contains("DepartId")) {
					String[] data = value.split(":");
					int i = Integer.parseInt(data[1]);
					List<Person> list = showListDao.findPersonByDid(i);
					StringBuilder sb = new StringBuilder();
					sb.append("pid").append("\t\t").append("name").append("\t\t").append("city").append("\t\t").append("Birth")
							.append("\t\t").append("Salary").append("\t\t").append("Tel").append("\t\t").append("DepartId")
							.append("\t\t").append("Dname").append("#");
					for (Person person : list) {
						sb.append(person.getId()).append("\t\t").append(person.getCname()).append("\t\t").append(person.getCity())
								.append("\t\t").append(person.getBirth()).append("\t\t").append(person.getSalary()).append("\t\t")
								.append(person.getTel()).append("\t\t").append(person.getDepartId()).append("\t\t")
								.append(person.getDname()).append("#");
					}
					// out.writeUTF(sb.toString());
					rs = sb.toString();

				}

				break;

			default:
				// out.writeUTF("Please enter the right signals");
				rs = "Please enter the right signals";

				break;
			}
		} else if (clientInputStr == "Q") {
			// out.writeUTF("Q");
			rs = "Q";

		}

		return rs;
	}

}
