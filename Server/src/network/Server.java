package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import constants.ConstantsNetwork;
import models.entities.Game;
import persistence.JSONFileManagerServer;
import utilities.MyUtilities;

public class Server {

	private ServerSocket serverSocket;
	private static MyUtilities myUtilities;
	private static JSONFileManagerServer fileManagerServer;
	private Timer timer;

	public static ArrayList<ClientConnections> clientConnections;

	public Server() throws IOException {
		clientConnections = new ArrayList<>();
		serverSocket = new ServerSocket(Integer.parseInt(JOptionPane.showInputDialog("port of server")));
		myUtilities = new MyUtilities();
		fileManagerServer = new JSONFileManagerServer();
		map();
		new Thread(){
			@Override
			public void run() {
				validate();
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

	private void map() {
		timer = new Timer(10000, new ActionListener() {
			private int x = 500;
			private int y = 500;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (Game game : myUtilities.getGameList()) {
					game.setDimensions(x, y);
				}
				x-=50;
				y-=50;
			}
		});
		timer.start();
		
	}

	public static ArrayList<ClientConnections> getClientConnections() {
		return clientConnections;
	}

	private void validate() {
		for (Game game : myUtilities.getGameList()) {
			if(game.getState()) {
				desconect(game.getName());
			}
		}
	}
	
	private void desconect(String name) {
		for (ClientConnections clientConnections2 : clientConnections) {
			if(clientConnections2.getName().equals(name)) {
				try {
					clientConnections2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void sendMessageALL() throws IOException{
		if(fileManagerServer != null && myUtilities != null) {
			fileManagerServer.writeGameList(myUtilities.getGameList());
			for (ClientConnections clientConnections2 : clientConnections) {
				try {
					if (clientConnections2.getSocket().isConnected()) {
						clientConnections2.send(ConstantsNetwork.FILE);
						clientConnections2.sendFile();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void game(ClientConnections clientConnections2) {
		fileManagerServer = new JSONFileManagerServer();
		try {
			clientConnections2.saveFile();
			myUtilities.add(fileManagerServer.readGame());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Game> getList(){
		return myUtilities.getGameList();
	}
}