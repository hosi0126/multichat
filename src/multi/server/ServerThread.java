package multi.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JTextArea;

public class ServerThread extends Thread{
	Socket socket;
	ServerMain main;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag=true;
	
	public ServerThread(Socket socket,ServerMain main) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		this.main=main;
		
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void listen(){
		String msg=null;
		
		try {
			msg=socket.getInetAddress().getHostAddress()+" : "+buffr.readLine();
			main.area.append(msg+"\n");
			send(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			flag=false;
			main.v.remove(this);
			main.area.append("Á¢¼ÓÀÚ : "+Integer.toString(main.v.size())+"\n");
		}
		
	}
	public void send(String msg){
		try {
			for(int i=0;i<main.v.size();i++){
				main.v.get(i).buffw.write(msg+"\n");
				main.v.get(i).buffw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		// TODO Auto-generated method stub
		while(flag){
			listen();
		}
	}
}
