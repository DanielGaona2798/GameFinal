package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;

public class ChatWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	private PnCarlosArregleEstaJoda arregleEstaJoda;
	private PnListClient listClient;
	
	public ChatWindow(Controller controller) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(300,300);
		this.getContentPane().setBackground(Color.GRAY);
	
		arregleEstaJoda = new PnCarlosArregleEstaJoda(controller);
		add(arregleEstaJoda, BorderLayout.SOUTH);
		
		listClient = new PnListClient();
		add(listClient, BorderLayout.CENTER);
	}
	
	public static int showConfirmDialog(String string) {
		return JOptionPane.showConfirmDialog(null,string);
	}
	
	
	public String getSelectedValue() {
		return listClient.getSelectedValue();
	}
	
	public void setTitle2(String name) {
		setTitle("Connected as: " + name);
	}
	
	public void refreshListFriends(ArrayList<String> clients) {
		listClient.refreshClientList(clients);
	}
	
	@Override
	public int getWidth() {
		return super.getWidth();
	}
	
	@Override
	public int getHeight() {
		return super.getHeight();
	}
	
	public static void impMesagge() {
		JOptionPane.showMessageDialog(null, "No existe el Usuario izi");
	}

	public static void showMsg(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
}
