package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import util.CheckInput;

public class Client {

	public static final String IP_ADDR = "localhost";// 服务器地址
	public static final int PORT = 8888;// 服务器端口号

	public static void main(String[] args) {    
      System.out.println("Client Start...");    
      System.out.println("当接收到服务器端字符为 \"Q\" 的时候, 客户端将终止\n");   
      while (true) {    
          Socket socket = null;  
          try {  
              socket = new Socket(IP_ADDR, PORT);    

              DataInputStream input = new DataInputStream(socket.getInputStream());    
              //向服务器端发送数据    
              DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
              System.out.print("Please Input: \t");   
              
//              String str = new BufferedReader(new InputStreamReader(System.in)).readLine();  
              BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
              String str = reader.readLine();
//              if(CheckInput.check(str)){
//              	out.writeUTF(str); 
//      				}else{
//      					System.out.println("Result from Server is: \n" ); 
//      				}
              
              while(!CheckInput.check(str)){
              	System.out.print("\nInput Error ! \n\nPlease Input : \t" );
              	str = reader.readLine();
              }
              out.writeUTF(str); 
                 
                  
              String ret = input.readUTF();     
              System.out.println("Result from Server is: " ); 
//              System.out.println(ret+"\n");
              if(ret!=null||!"".equals(ret)){
              	if(ret.contains("#")){
              		String[] result = ret.split("#");
              		for(int i = 0; i<result.length; i++){
              			System.out.println(result[i]+"\n");
              		}
              	}else {
              		System.out.println(ret);
              	}
              }else{
              	System.out.println("Client Close!");    
                Thread.sleep(500);    
                break;    
              }
              
              // 如接收到 "OK" 则断开连接    
//              if ("OK".equals(ret)) {    
//                  System.out.println("Client Close!");    
//                  Thread.sleep(500);    
//                  break;    
//              }    
                
              out.close();  
              input.close();  
          } catch (Exception e) {  
              System.out.println("客户端异常:" + e.getMessage());   
          } finally {  
              if (socket != null) {  
                  try {  
                      socket.close();  
                  } catch (IOException e) {  
                      socket = null;   
                      System.out.println("客户端 finally 异常:" + e.getMessage());   
                  }  
              }  
          }  
      }    
  }
	
}
