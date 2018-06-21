package views;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PnListClient extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JList<String> clientList;
	private DefaultListModel<String> clientModel;
	
	public PnListClient() {
		setBackground(Color.decode("#3082e5"));
		clientModel = new DefaultListModel<>();
		clientList = new JList<>(clientModel);
		clientList.setFont(new Font("Comic Sans Ms", Font.BOLD, 14));
		clientList.setBackground(Color.WHITE);
		JScrollPane jspList = new JScrollPane(clientList);
		add(new JScrollPane(jspList));
	}
	
	public String getSelectedValue() {
		return clientList.getSelectedValue();
	}
	
	public void refreshClientList(ArrayList<String> clients){
		clientModel.removeAllElements();
		clientModel.addElement("PLAYERS CONECTED");
		for (String client : clients) {
			clientModel.addElement(client);
		}
	}
}