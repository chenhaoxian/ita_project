package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.AddDao;
import dao.DeleteDao;
import dao.ShowListDao;
import dao.UpdateDao;
import dao.impl.AddDaoImpl;
import dao.impl.DeleteDaoImpl;
import dao.impl.ShowListDaoImpl;
import dao.impl.UpdateDaoImpl;
import po.Depart;
import po.Person;
import util.CheckInput;

public class Server {

	public static final int PORT = 8888;// 监听的端口号
	private ServerSocket serverSocket = null;
	private Socket client = null;
	private boolean flag = false;

	private ArrayList<Socket> sockets = new ArrayList<Socket>();

	// public static void main(String[] args) {
	// System.out.println("Server Start...\n");
	// Server server = new Server();
	// server.init();
	// }

	public void init() {
		while (true) {
			try {
				serverSocket = new ServerSocket(PORT);
				while (true) {
					// 一旦有堵塞, 则表示服务器与客户端获得了连接
					// System.out.println("Server start success!");
					client = serverSocket.accept();
					sockets.add(client);
					if(flag ) {
						break;
					}

					// 处理这次连接
					new HandlerThread(client);
				}
			} catch (Exception e) {
				System.out.println(" " + e.getMessage());
				

			}
		}
	}

	public void closeServerSocket() {
		try {
			this.serverSocket.close();
			flag = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("server 8888 关闭成功");
	}

	public ArrayList<Socket> getalivedSockets() {
		ArrayList<Socket> list = new ArrayList<Socket>();
		for (int i = 0; i < sockets.size(); i++) {
			if (sockets.get(i).isConnected()) {
				list.add(sockets.get(i));
			}
		}
		return list;
	}
	
	public void removeSocket(int i){
		sockets.remove(i);
	}

	private class HandlerThread implements Runnable {
		private Socket socket;

		private AddDao addDao = new AddDaoImpl();
		private DeleteDao deleteDao = new DeleteDaoImpl();
		private ShowListDao showListDao = new ShowListDaoImpl();
		private UpdateDao updateDao = new UpdateDaoImpl();

		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}

		public void run() {
			try {
				// 读取客户端数据
				DataInputStream input = new DataInputStream(socket.getInputStream());
				String clientInputStr = input.readUTF();
				// 处理客户端数据
				System.out.println("Client Input Command :" + clientInputStr);

				// 向客户端回复信息
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				String command = null;

				clientInputStr = clientInputStr.trim();
				if (clientInputStr.split("\\s+").length == 1) {
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
						out.writeUTF(sb.toString());
					} else if ("L-DA".equals(clientInputStr)) {
						List<Depart> list = showListDao.showAllDepart();
						StringBuilder sb = new StringBuilder();
						sb.append("dId").append("\t\t").append("Dname").append("\t\t").append("City").append("#");
						for (Depart depart : list) {
							sb.append(depart.getId()).append("\t\t").append(depart.getDname()).append("\t\t").append(depart.getCity())
									.append("#");
						}
						out.writeUTF(sb.toString());
					} else {
						out.writeUTF("null");
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
							if (result!=0) {
								out.writeUTF("success A-P");
								;
							} else {
								out.writeUTF("fail A-P");
							}

						} else {
							out.writeUTF("Input error");
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
								} else if(data[i].split(":")[i].startsWith("city")){
									depart.setCity(value.split(":")[1].trim());
								}
							}
							int result = addDao.addDepart(depart);
							if (result == 1) {
								out.writeUTF("success A-D");
							} else {
								out.writeUTF("fail A-D");
							}

						} else {
							out.writeUTF("Input error");
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
							if (result !=0) {
								out.writeUTF("success U-P");
								;
							} else {
								out.writeUTF("No this ID");
							}

						} else {
							out.writeUTF("Input error");
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
									out.writeUTF("success U-D");
								} else {
									out.writeUTF("fail U-D");
								}
							}

						} else {
							out.writeUTF("Input error");
						}

						break;
					case ("D-P"):
						if (value.toLowerCase().contains("pid")) {
							String[] data = value.split(":");
							int i = Integer.parseInt(data[1]);
							int result = deleteDao.deletePerson(i);
							if (result == 1) {
								out.writeUTF("success D-P");
							} else {
								out.writeUTF("fail D-P");
							}
						} else {
							out.writeUTF("Input error");
						}

						break;
					case ("D-D"):

						if (value.toLowerCase().contains("did")) {
							String[] data = value.split(":");
							int i = Integer.parseInt(data[1]);
							System.out.println(i);
							int result = deleteDao.deleteDepart(i);
							if (result !=0) {
								out.writeUTF("success D-D");
							} else {
								out.writeUTF("fail D-D");
							}
						} else {
							out.writeUTF("Input error");
						}

						break;
					case ("L-PD"):

						if (value.toLowerCase().contains("did") || value.toLowerCase().contains("DepartId")) {
							String[] data = value.split(":");
							int i = Integer.parseInt(data[1]);
							List<Person> list = showListDao.findPersonByDid(i);
							StringBuilder sb = new StringBuilder();
							sb.append("pid").append("\t\t").append("name").append("\t\t").append("city").append("\t\t")
									.append("Birth").append("\t\t").append("Salary").append("\t\t").append("Tel").append("\t\t")
									.append("DepartId").append("\t\t").append("Dname").append("#");
							for (Person person : list) {
								sb.append(person.getId()).append("\t\t").append(person.getCname()).append("\t\t")
										.append(person.getCity()).append("\t\t").append(person.getBirth()).append("\t\t")
										.append(person.getSalary()).append("\t\t").append(person.getTel()).append("\t\t")
										.append(person.getDepartId()).append("\t\t").append(person.getDname()).append("#");
							}
							out.writeUTF(sb.toString());

						}

						break;
					
					default:
						out.writeUTF("Please enter the right signals");

						break;
					}
				}else if(clientInputStr == "Q"){
					out.writeUTF("Q");

				}

				// out.writeUTF(clientInputStr);
				// if (clientInputStr != null) {
				// if(!clientInputStr.trim().contains(" ")){
				// if("L-PA".equals(clientInputStr.toUpperCase())){
				// List<Hyman1> list = dao.showAllHyman1();
				// StringBuilder sb = new StringBuilder();
				// for (Hyman1 hyman: list){
				// sb.append(hyman.getMyname()).append("\t").append(hyman.getAge()).append("#");
				// }
				// out.writeUTF(sb.toString());
				// }
				// }
				// } else {
				// out.writeUTF("null");
				// }

				// System.out.print("Please Input :\t");
				// 发送键盘输入的一行
				// String s = new BufferedReader(new
				// InputStreamReader(System.in)).readLine();
				// out.writeUTF(s);

				out.close();
				input.close();
			} catch (Exception e) {
				System.out.println("服务器 run 异常: " + e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						socket = null;
						System.out.println("服务端 finally 异常:" + e.getMessage());
					}
				}
			}
		}
	}

}
