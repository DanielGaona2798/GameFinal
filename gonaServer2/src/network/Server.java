package network;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
	private static Rectangle finalPoint;
	private static ArrayList<Rectangle> rectangleWall;
	
	public Server() throws IOException {
		clientConnections = new ArrayList<>();
		serverSocket = new ServerSocket(Integer.parseInt(JOptionPane.showInputDialog("Port")));
		playerList = new ArrayList<>();
		finalPoint = new Rectangle(400, 500, 20, 20);
		rectangleWall = new ArrayList<>();
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
	public static void createMap(){
		for (int i = 0; i < 630; i+= 30) {
			rectangleWall.add(new Rectangle(30, i, 30, 30));
		}
		for (int i = 30; i < 1300; i+= 30) {
			rectangleWall.add(new Rectangle(i, 630, 30, 30));
		}
		for (int i = 630; i > 30; i-=30) {
			rectangleWall.add(new Rectangle(1300, i, 30, 30));
		}
		for (int i = 1300; i > 100; i-=30) {
			rectangleWall.add(new Rectangle(i, 30, 30, 30));
		}
		sendWalls();
		
	}
	
	private static void sendWalls() {
		for (ClientConnections clientConnections2 : clientConnections) {
			try {
				clientConnections2.send(ConstatntsUI.SEND_WALL + sendWallsArr());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	private static String sendWallsArr() {
		String a = "";
		for (Rectangle rec : rectangleWall) {
			a+= (int)rec.getX()+ "!" + (int)rec.getY()+ ",";
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
				clientConnections2.send(ConstatntsUI.SEND_POINT_FINAL + (int)finalPoint.getX()+ "!" + (int)finalPoint.getY());
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
				switch (code) {
				case KeyEvent.VK_RIGHT:
					player.rigth();
					coalitionWallR(player);
					coalitionPoint(player);
					break;
				case KeyEvent.VK_LEFT:
					player.left();
					coalitionWallLE(player);
					coalitionPoint(player);
					break;
				case KeyEvent.VK_UP:
					player.up();
					coalitionWallU(player);
					coalitionPoint(player);
					break;
				case KeyEvent.VK_DOWN:
					player.down();
					coalitionWallD(player);
					coalitionPoint(player);
					break;
				}
			}
		}
	}

	private static void coalitionPoint(Player player) {
		if (player.getRectangle().intersects(finalPoint)) {
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

	private static void coalitionWallD(Player player) {
		for (Rectangle rectanguloysuptm : rectangleWall) {
			if (player.getRectangle().intersects(rectanguloysuptm)) {
				player.up();
			}
		}
	}

	private static void coalitionWallU(Player player) {
		for (Rectangle rectanguloysuptm : rectangleWall) {
			if (player.getRectangle().intersects(rectanguloysuptm)) {
				player.down();
			}
		}
	}

	private static void coalitionWallLE(Player player) {
		for (Rectangle rectanguloysuptm : rectangleWall) {
			if (player.getRectangle().intersects(rectanguloysuptm)) {
				player.rigth();
			}
		}
	}

	private static void coalitionWallR(Player player) {
		for (Rectangle rectanguloysuptm : rectangleWall) {
			if (player.getRectangle().intersects(rectanguloysuptm)) {
				player.left();
			}
		}
	}
}