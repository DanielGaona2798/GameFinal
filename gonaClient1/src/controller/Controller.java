package controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import constants.ConstatntsUI;
import models.Client;
import models.MusicManager;
import views.ChatWindow;
import views.DialogRegsitry;
import views.MainWindow;
import views.UserWindow;

public class Controller implements ActionListener, KeyListener{

	private UserWindow userWindow;
	private DialogRegsitry dialogRegsitry;
	private ChatWindow chatWindow;
	private Client cliente;
	private MainWindow mainWindow;
	private MusicManager musicManager;

	public Controller() throws IOException {
		userWindow = new UserWindow(this);
		dialogRegsitry = new DialogRegsitry(this);
		chatWindow = new ChatWindow(this);
		mainWindow = new MainWindow(this);
		musicManager = new MusicManager();
	}


	public static void main(String[] args) {
		try {
			new Controller();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (Actions.valueOf(arg0.getActionCommand())) {
		case ACCEPT:
			try {
				accept();
			} catch (IOException e1) {
			}
			break;
		case CANCEL:
			break;
		case REGISTRY:
			registry();
			break;
		case NEW_USER:
			break;
		case REGISTRY_ACEEPT:
			try {
				registryAccept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case EXIT:
			System.exit(0);
			break;
		case SEND:
			try {
				cliente.send("/send#" + cliente.getName()+ "#" + chatWindow.getSelectedValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	private void registryAccept() throws IOException {
		cliente = new Client(dialogRegsitry.getName(),Integer.parseInt( dialogRegsitry.getPasword()),"127.0.0.1", 3005,this);
		cliente.send("/registro#" + cliente.getName() + "#" + cliente.getPasword() + "#" + cliente.getPoints() + "#" + cliente.getLives());
		dialogRegsitry.setVisible(false);

	}

	public int getWithWindow(){
		return mainWindow.getWidth();
	}

	public int getheithWindow(){
		return mainWindow.getHeight();
	}

	public void setWallList(String a){
		mainWindow.setWallList(a);
		mainWindow.repaint();
	}

	public void setBombList(String string) {
		mainWindow.setBombList(string);
		mainWindow.repaint();		
	}

	private void accept() throws IOException {
		cliente.send("/connect#" + userWindow.getClient());
	}


	private void registry() {
		dialogRegsitry.setVisible(true);
	}
	public void showChatWindow() throws IOException {
		chatWindow.setVisible(true);
		chatWindow.setTitle2(cliente.getName());
		userWindow.setVisible(false);
		cliente.send("/dameLista");
	}
	public void putListClient(ArrayList<String> users) {
		chatWindow.refreshListFriends(users);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) {
		mainWindow.manage(e.getKeyCode());
		try {
			cliente.send("/Position#" + mainWindow.getMy());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		if(mainWindow.colition()) {
			System.out.println("golpeo");
			mainWindow.blockMove();
			try {
				musicManager.playSong(ConstatntsUI.HITSONG);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if(mainWindow.colitionEneny()) {
			try {
				musicManager.playSong(ConstatntsUI.AHSONG);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			mainWindow.quitLifesZero();
			mainWindow.revalidate();
			JOptionPane.showMessageDialog(null, "has muerto!!");
		}
		if(mainWindow.colitionNick()) {
			JOptionPane.showMessageDialog(new JDialog(), "Hola dead, tienes suerte,"
					+ "te dare mi hojo para que veas una parte del camino...");
			mainWindow.changeLocationNick();
			try {
				cliente.send("/deleteWall#" + mainWindow.getWallNick()+ "#" + mainWindow.getPositionNick() + "#" + mainWindow.getPositionLantern());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			mainWindow.repaint();
		}

		if(mainWindow.colitionLatern()) {
			JOptionPane.showMessageDialog(new JDialog(), "Vamos a despejar esto por unos segundos");
			mainWindow.changeLocationNick();
			mainWindow.changeLocationLantern();
			try {
				cliente.send("/deleteWall#" + mainWindow.getWall()+ "#" + mainWindow.getPositionNick() + "#" + mainWindow.getPositionLantern());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			mainWindow.repaint();

		}
		if(mainWindow.colitionBomb()) {
			JOptionPane.showMessageDialog(null, "si hay bombas entre los muros, estos se eliminaran");
			try {
				musicManager.playSong(ConstatntsUI.BOMBASONG);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				cliente.send("/deleteWall#" + mainWindow.getBombWall());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			mainWindow.repaint();
		}
		if(mainWindow.colitionSword()) {
			JOptionPane.showMessageDialog(null, "Felicitaciones has ganado!!!");
			try {
				musicManager.playSong(ConstatntsUI.CONGRATULATIONS);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
		mainWindow.border();
	}


	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void showPanel() {
		mainWindow.setVisible(true);
	}


	public void updatePonint(String string) {
		String[] point = string.split("!");
		mainWindow.setPointFriend(new Point((int)Double.parseDouble(point[0]), (int)Double.parseDouble(point[1])));
	}

	public void updateFiury(String string) {
		String[] point = string.split("!");
		mainWindow.setPointFiury(new Point((int)Double.parseDouble(point[0]), (int)Double.parseDouble(point[1])));
	}
	
	public void updateLantern(String str){
		String[] rectange = str.split("!");
		mainWindow.setpointLantern(new Rectangle((int)Double.parseDouble(rectange[0]), (int)Double.parseDouble(rectange[1]), (int)Double.parseDouble(rectange[2]), (int)Double.parseDouble(rectange[3])));
	}
}