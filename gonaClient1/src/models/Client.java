package models;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import constants.ConstatntsUI;
import controller.Controller;

public class Client extends Connection{

	private Controller controller;
	private String name;
	private ArrayList<Player> players;
	private Rectangle point;
	private ArrayList<Rectangle> rectangleWall;
	
	public Client(String name, String ip, int port,Controller controller) throws IOException {
		super(ip, port);
		this.name = name;
		this.controller = controller;
		players = new ArrayList<>();
		rectangleWall = new ArrayList<>();
	}

	@Override
	void executeTask() {
			String[] path;
			try {
				path = readResponse().split("#");
				switch (path[0]) {
				case ConstatntsUI.SEND_ALL:
					getPLayerList(path[1]);
					break;
				case ConstatntsUI.SEND_POINT_FINAL:
					getPoint(path[1]);
					break;
				case ConstatntsUI.SEND_WALL:
					getWalls(path[1]);
					break;
				case ConstatntsUI.FINISH_GAME:
					controller.finish(path[1]);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	private void getWalls(String string) {
		rectangleWall = new ArrayList<>();
		String[] request = string.split(",");
		for (int i = 0; i < request.length; i++) {
			String string2 = request[i];
			String[] as = string2.split("!");
			rectangleWall.add(new Rectangle(Integer.parseInt(as[0]), Integer.parseInt(as[1]), 30, 30));
		}
	}

	private void getPoint(String string) {
		String[] a = string.split("!");
		point = new Rectangle(Integer.parseInt(a[0]), Integer.parseInt(a[1]), 50, 50);
	}

	public void getPLayerList(String a){
		players = new ArrayList<>();
		String[] players2 = a.split(",");
		for (int i = 0; i < players2.length; i++) {
			String string2 = players2[i];
			String[] as = string2.split("!");
			players.add(new Player(as[0], new Rectangle(Integer.parseInt(as[1]),Integer.parseInt(as[2]), 20, 20)));
		}
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Rectangle getPoint() {
		return point;
	}

	public ArrayList<Rectangle> getRectangleWall() {
		return rectangleWall;
	}
	
	
}