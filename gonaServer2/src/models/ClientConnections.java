package models;

import java.io.IOException;
import java.net.Socket;

import managers.BombManager;
import managers.WallManager;
import models.entities.User;

public class ClientConnections extends Connection{
	
	private User user;
	private String userFriend;
	private WallManager wall;
	private BombManager bomb;

	public ClientConnections(Socket newConnection) throws IOException {
		super(newConnection);
	}

	@Override
	void executeTask() {
		try {
			String[] path = readResquest().split("#");
			switch (path[0]) {
			case "/registro":
				this.user = new User(path[1], Integer.parseInt(path[2]), Integer.parseInt(path[3]), Integer.parseInt(path[4]));
				break;
			case "/send":
				getUser(path[2]).send("/deseaJugar#" + path[1]);
				userFriend = path[2];
				getUser(path[2]).setUserFriend(user.getName());
				break;
			case "/connect":
				ClientConnections a2 = getUser(path[1]);
				if (a2 != null) {
					send("/connect#1");
				}else{
					send("/connect#2");
				}
				break;
			case "/dameLista":
				Server.sendMassiveList();
				break;
			case "/acceptacion":
				if (path[1].equals("0")){
					generateManagers();
					getUser(userFriend).generateManagers();
					wall.generateRandomWall(Integer.parseInt(path[3]), Integer.parseInt(path[4]));
					bomb.generateBomb(Integer.parseInt(path[3]), Integer.parseInt(path[4]));
					getUser(userFriend).setWallAndBombList(wall.getAllWall(), bomb.getAllBombs());
					String walls = wall.getAllWall();
					send("/generateWall#" + walls);
					getUser(userFriend).send("/generateWall#" + walls);
					send("/generateBomb#" + bomb.getAllBombs());
					getUser(userFriend).send("/generateBomb#" + bomb.getAllBombs());
					send("/connect#1");
					getUser(userFriend).send("/connect#1");
				}else{
					send("/paila");
				}
				break;
			case "/deleteWall":
				wall.setWallList(path[1]);
				send("/generateWall#" + wall.getAllWall());
				getUser(userFriend).send("/generateWall#" + wall.getAllWall());
				send("/updateFiury#"+ path[2] + "#" + path[3]);
				getUser(userFriend).send("/updateFiury#"+ path[2] + "#" + path[3]);
				break;
			case "/Position":
				getUser(userFriend).send("/setPointFriend#" + path[1]);
				break;
			}
		
		} catch (IOException e) {
		}
	}
	
	public void setWallAndBombList(String wallList, String bombList){
		wall.setWallList(wallList);
		bomb.setBombList(bombList);
	}
	
	public void generateManagers() {
		wall = new WallManager();
		bomb = new BombManager();
	}
	
	public ClientConnections getUser(String name){
		ClientConnections aux = null;
		for (ClientConnections user : Server.clientConnections) {
			if (user.getUser().getName().equals(name)) {
				aux = user;
			}
		}
		return aux;
	}
	
	public User getUser() {
		return user;
	}

	public String getUserFriend() {
		return userFriend;
	}

	public void setUserFriend(String userFriend) {
		this.userFriend = userFriend;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
