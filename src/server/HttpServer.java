package server;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(8889);
			
			while(true){
				Socket socket = server.accept();
				OutputStream out = socket.getOutputStream();
				out.write("HTTP/1.0 200 OK\r\n".getBytes());
				out.write("Content-Type:text/html;charset=utf8\r\n".getBytes());
				out.write("\r\n".getBytes());
				InputStream in = new FileInputStream("server.html");
				byte[] bs = new byte[in.available()];
				in.read(bs);
				out.write(bs);
				out.close();
				socket.close();
//				server.close();
				
//				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//				out.writeUTF("HTTP/1.0 200 OK\r\n");
//				out.writeUTF("Content-Type:text/html;charset=utf8\r\n");
//				out.writeUTF("\r\n");
//				out.writeUTF("HELLO中国");
//				out.close();
//				socket.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
