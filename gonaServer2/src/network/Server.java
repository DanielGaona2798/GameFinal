package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import constants.ConstatntsUI;
import models.Game;
import models.Player;
import models.Wall;

public class Server {

	private ServerSocket serverSocket;
	public static ArrayList<ClientConnections> clientConnections;
	private static Game game;
	private static Timer timer;
	
	public Server() throws IOException {
		clientConnections = new ArrayList<>();
		serverSocket = new ServerSocket(Integer.parseInt(JOptionPane.showInputDialog("Port")));
		game = new Game();
		timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (ClientConnections clientConnections2 : clientConnections) {
					try {
						clientConnections2.send(ConstatntsUI.SEND_WALL + sendWallsArr());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
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
		game.addPLayer(player);
	}
	
	public static void createMap(){
		game.createMap();
		timer.start();
	}
	
	
	private static String sendWallsArr() {
		String a = "";
		for (Wall rec : game.getRectangleWall()) {
			a+= (int)rec.getRecatngle().getX()+ "!" + (int)rec.getRecatngle().getY()+ ",";
		}
		return a;
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
	public static void sendPointFinal(){
		for (ClientConnections clientConnections2 : clientConnections) {
			try {
				clientConnections2.send(ConstatntsUI.SEND_POINT_FINAL + (int)game.getFinalPoint().getX()+ "!" + (int)game.getFinalPoint().getY());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static String sendArrayPLayers() {
		String result = "";
		for (Player player : game.getPlayerList()) {
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
		for (Player player : game.getPlayerList()) {
			if (player.getName().equals(name)) {
				switch (code) {
				case KeyEvent.VK_RIGHT:
					player.rigth();
					coalitionWall(player);
					coalitionPoint(player);
					break;
				case KeyEvent.VK_LEFT:
					player.left();
					coalitionWall(player);
					coalitionPoint(player);
					break;
				case KeyEvent.VK_UP:
					player.up();
					coalitionWall(player);
					coalitionPoint(player);
					break;
				case KeyEvent.VK_DOWN:
					player.down();
					coalitionWall(player);
					coalitionPoint(player);
					break;
				}
			}
		}
	}

	private static void coalitionPoint(Player player) {
		if (player.getRectangle().intersects(game.getFinalPoint())) {
			for (ClientConnections clientConnections2 : clientConnections) {
				try {
					clientConnections2.send(ConstatntsUI.FINISH_GAME+ player.getName());
					clientConnections2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void coalitionWall(Player player) {
		for (Wall rectanguloysuptm : game.getRectangleWall()) {
			if (player.getRectangle().intersects(rectanguloysuptm.getRecatngle())) {
				player.moveInitPosition();
			}
		}
	}
}