package oocl.server;

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
import oocl.util.CheckInput;

public class Server {

	public static final int PORT = 8888;// 监听的端口号
	private ServerSocket serverSocket = null;
	private Socket client = null;
	private boolean flag = false;

	private ArrayList<Socket> sockets = new ArrayList<Socket>();


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

		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}

		public void run() {
			try {
				DataInputStream input = new DataInputStream(socket.getInputStream());
				String clientInputStr = input.readUTF();
				System.out.println("Client Input Command :" + clientInputStr);

				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				

				clientInputStr = clientInputStr.trim();
				
				if(CheckInput.checkCommand(clientInputStr) != null){
					out.writeUTF(CheckInput.checkCommand(clientInputStr));
				}else{
					out.writeUTF("null");
				}
				
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
