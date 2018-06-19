package models;

import java.io.File;
import java.io.IOException;
import java.net.Socket;


public class ClientConnections extends Connection{


	public ClientConnections(Socket newConnection) throws IOException {
		super(newConnection);
	}

	@Override
	void executeTask() {
		try {
			String [] string = readResquest().split("#");

		} catch (IOException e) {
		}
	}

	public void sendFile(String file){
		sendFile(new File(file));
	}

	public void saveFile(){
		saveFileConnection();
	}
}