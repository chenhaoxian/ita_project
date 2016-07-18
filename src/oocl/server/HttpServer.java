package oocl.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HttpServer implements Runnable {
	private final static int PORT = 8889;
	private ServerSocket server = null;
	private static Server myServer = new Server();
	private List<Socket> list;
	private int ii;

	private static long threadId = 0;
	private static Map<Long, Thread> map = null;

	public static void main(String[] args) {
		new HttpServer();
	}

	public HttpServer() {
		try {
			server = new ServerSocket(PORT);
			if (server == null)
				System.exit(1);
			new Thread(this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket client = null;
				client = server.accept();
				if (client != null) {
					try {
						System.out.println("Http服务器开启成功！！...");
						BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
						OutputStream out = client.getOutputStream();
						DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
						// ObjectOutputStream oOut = new
						// ObjectOutputStream(client.getOutputStream());
						out.write("HTTP/1.0 200 OK\r\n".getBytes());
						out.write("Content-Type:text/html;charset=utf8\r\n".getBytes());
						out.write("\r\n".getBytes());

						InputStream in = new FileInputStream("server.html");
						byte[] bs = new byte[in.available()];
						in.read(bs);
						out.write(bs);

						String line = reader.readLine();
						System.out.println("line: " + line);

						if (line.contains("=") && !line.contains("&")) {
							String key = line.split("=")[1].split(" ")[0];
							// System.out.println(threadId);
							if ("start".equals(key)) {
								if (threadId == 0) {
									new Thread() {
										@Override
										public void run() {
											Thread thread = Thread.currentThread();
											System.out.println("Thread Id: " + thread.getId() + thread.getName());
											threadId = thread.getId();
											map = new HashMap<Long, Thread>();
											map.put(threadId, thread);
											// myServer = new Server();
											System.out.println("Server start success!");
											myServer.init();
										}
									}.start();

									out.write("success".getBytes());
								} else {
									out.write("success".getBytes());
								}
							} else if ("close".equals(key)) {
								Thread t = map.get(threadId);
								System.out.println(t.getId() + t.getName());

								t.interrupt();

								new Thread() {
									@Override
									public void run() {
										Thread thread = Thread.currentThread();
										myServer.closeServerSocket();
									}
								}.start();
								System.out.println("Close Server Success ");
								out.write("success".getBytes());

							} else if ("show".equals(key)) {

								list = myServer.getalivedSockets();

								if (list.size() != 0) {
									StringBuilder sb = new StringBuilder();
									for (int i = 0; i < list.size(); i++) {
										if (!sb.toString().contains(list.get(i).getInetAddress().toString())) {
											sb.append(list.get(i).getInetAddress()).append("#");
										}
									}
									out.write(sb.toString().getBytes());
								} else {
									out.write("null".getBytes());
								}
							}
						} else if (line.contains("=") && line.contains("&")) {
							String addr = line.split("key")[1].split("=")[1].split(" ")[0];
							// System.out.println(addr);
							addr = addr.substring(3, addr.length());
							// System.out.println(addr);
							addr = "/" + addr;

							for (int i = 0; i < list.size(); i++) {
								if (addr.equals(list.get(i).getInetAddress().toString())) {
									ii = i;

									new Thread() {
										@Override
										public void run() {
											try {
												list.get(ii).close();
												System.out.println("socket close....." + list.get(ii).isClosed());
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											System.out.println(ii);
											// list.remove(ii);
											myServer.removeSocket(ii);

										}
									}.start();

									break;
								}
							}
							out.write("success".getBytes());
						}
						out.close();
						client.close();
						in.close();

						continue;

					} catch (Exception e) {
						System.out.println("HTTP服务器:" + e.getLocalizedMessage());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void closeSocket(Socket socket) {
		try {
			socket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println(socket + "离开了HTTP服务器");
	}

}
