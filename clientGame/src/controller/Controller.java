package controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import models.Client;
import views.DialogRegsitry;
import views.MainWindow;


public class Controller implements ActionListener{

	private static MainWindow mainwindow;
	private DialogRegsitry dialogRegsitry;
	private Client client;
	
	public Controller() {
		mainwindow = new MainWindow(this);
		dialogRegsitry = new DialogRegsitry(this);
		dialogRegsitry.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ACCEPT:
			try {
				client = new Client(dialogRegsitry.getIP(), 2001, dialogRegsitry.getName());
				dialogRegsitry.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case SEND:
			try {
				client.send("/message#" + client.getName() + "#" + mainwindow.getNumberwords());
				mainwindow.clean();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case CHANGE_IP:
			changeIP();
			break;
		}
	}

	private void changeIP() {
		String clie = client.getName(); 
		client = null;
		try {
			client = new Client(JOptionPane.showInputDialog("IP:"), 2001,clie);
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void showMessage(String[] string) {
		mainwindow.setTextArea(string[1]+ ":"+ string [2]);
	}
	public static void main(String[] args) {
		new Controller();
	}
}
