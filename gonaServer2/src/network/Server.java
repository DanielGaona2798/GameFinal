package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import constants.ConstatntsUI;
import models.Player;

public class Server {

	private ServerSocket serverSocket;
	public static ArrayList<ClientConnections> clientConnections;
	private static ArrayList<Player> playerList;
	
	public Server() throws IOException {
		clientConnections = new ArrayList<>();
		serverSocket = new ServerSocket(Integer.parseInt(JOptionPane.showInputDialog("Port")));
		playerList = new ArrayList<>();
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
	
	public static void addPLayer(Player player){
		playerList.add(player);
	}

	public static void sendPlayers(){
		for (ClientConnections clientConnections2 : clientConnections) {
			try {
				clientConnections2.send(ConstatntsUI.SEND_ALL + sendArrayPLayers());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static String sendArrayPLayers() {
		String result = "";
		for (Player player : playerList) {
		result += player.getName() + "!"+ (int)player.getRectangle().getX()+ "!" + (int)player.getRectangle().getY()+ ",";
		}
		return result;
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void move(String name,int code) {
		for (Player player : playerList) {
			if (player.getName().equals(name)) {
				player.move(code);
			}
		}
	}
}