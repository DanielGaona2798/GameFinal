package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Connection extends MyThread{

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	
	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		start();
	}

	public void send(String data) throws IOException{
		output.writeUTF(data);
	}
	
	public Socket getSocket() {
		return socket;
	}

	public String readResquest() throws IOException{
		return input.readUTF();
	}
	
	public DataInputStream getInput() {
		return input;
	}

	public DataOutputStream getOutput() {
		return output;
	}
	
	public void close() throws IOException{
		output.close();
		input.close();
		socket.close();
	}

}
