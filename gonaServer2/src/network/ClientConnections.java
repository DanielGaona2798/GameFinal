package network;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.Socket;

import constants.ConstatntsUI;
import models.Player;


public class ClientConnections extends Connection{


	
	public ClientConnections(Socket newConnection) throws IOException {
		super(newConnection);
	}

	@Override
	void executeTask() {
		String[] path;
		try {
			path = readResquest().split("#");
			switch (path[0]) {
			case ConstatntsUI.INIT_MESSAGE:
				Server.addPLayer(new Player(path[1], new Rectangle(10, 10, 20, 20)));
				Server.sendPlayers();
				Server.createMap();
				break;
			case ConstatntsUI.MOVE:
				Server.move(path[2],Integer.parseInt(path[1]));
				Server.sendPlayers();
				break;
			case ConstatntsUI.CLOSE:
				Server.removeClientConnection(this);
				close();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
