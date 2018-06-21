package models;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private ServerSocket serverSocket;
	public static ArrayList<ClientConnections> clientConnections;
	
	public Server() throws IOException {
		clientConnections = new ArrayList<>();
		serverSocket = new ServerSocket(3005);
		new Thread(){
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println("Server online...");
						Socket newConnection = serverSocket.accept();
						System.out.println("aceptado");
						clientConnections.add(new ClientConnections(newConnection));
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	public static String getArrayAloneUsers() {
		String out = "";
		for (ClientConnections clientConnections2 : clientConnections) {
			out += clientConnections2.getUser().getName() + "#";
		}
		return out;
	}

	public static ArrayList<ClientConnections> getClientConnections() {
		return clientConnections;
	}
	
	public static void sendMassiveList() throws IOException {
		for (ClientConnections connections : clientConnections) {
			connections.send("/tengaLista#" + Server.getArrayAloneUsers());
		}
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}