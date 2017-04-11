package multi.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerMain extends JFrame implements ActionListener,Runnable{
	JPanel p_north; 
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	int port=7777;
	Thread thread;
	ServerSocket server;
	Socket socket;
	
	Vector<ServerThread> v=new Vector<ServerThread>();
	
	public ServerMain() {
		p_north = new JPanel();
		t_port = new JTextField(Integer.toString(port) ,10);
		bt_start = new JButton("啊悼");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_north.add(t_port);
		p_north.add(bt_start);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		bt_start.addActionListener(this);
		
		setBounds(600,100,300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void startServer(){
		try {
				port=Integer.parseInt(t_port.getText());
				server=new ServerSocket(port);
				area.append("辑滚 积己\n");

				while(true){
					socket=server.accept();
					String ip=socket.getInetAddress().getHostAddress();
					area.append(ip+" 立加 \n");
					ServerThread st=new ServerThread(socket, this);
					st.start();
					
					v.add(st);
					area.append("立加磊 : "+Integer.toString(v.size())+"\n");
				}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		startServer();
	}
	
	public void actionPerformed(ActionEvent e) {
		thread=new Thread(this);
		thread.start();
	}
	
	public static void main(String[] args) {
		new ServerMain();
	}

}