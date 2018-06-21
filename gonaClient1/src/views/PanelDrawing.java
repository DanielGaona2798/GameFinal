package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import controller.Controller;
import models.Enemy;

public class PanelDrawing extends JPanel{

	private static final int SPACE = 8;
	private static final long serialVersionUID = 1L;
	
	private Point my, positionWolverine ,pointEnemy, pointNick, pointSword, pointMagnet;
	private Graphics2D gr;
	@SuppressWarnings("unused")
	private Graphics graphics;
	private volatile ArrayList<Rectangle> wallList, bombList;
	@SuppressWarnings("unused")
	private Rectangle dead, friend, nick, latern, sword, enemy, magnet;
	private int key, var, numLifes = 3;
	private Enemy enemy1, enemy2;
	 
	public PanelDrawing(Controller controller) {
		this.addKeyListener(controller);
		my = new Point(20, 20);
		positionWolverine = new Point(1300,40);
		pointMagnet = new Point(1300,40);
		pointNick = new Point(400, 350);
		pointSword = new Point(randomX(), randomY());
		setBackground(Color.BLACK);
		pointEnemy = new Point(30, 30);
		
		wallList = new ArrayList<>();
		bombList = new ArrayList<>();
		enemy1 = new Enemy(my);
		enemy2 = new Enemy(pointEnemy);
		SwingWorker<Integer, Integer> swing = new SwingWorker<Integer, Integer>(){
			@Override
			protected Integer doInBackground() throws Exception {
				while (true) {
					enemy1.updatePointPlayer(my);
					positionWolverine.setLocation(enemy1.getPointEnemy());
					enemy2.updatePointPlayer(pointEnemy);
					pointMagnet.setLocation(enemy2.getPointEnemy());
					Thread.sleep(1000);
				}
			}

		};
		swing.execute();
	}
	
	public void setWallList(String todo){
		wallList.clear();
		String[] rectagulo = todo.split("!");
		for (String string : rectagulo) {
			String[] coordenate = string.split("-");
			wallList.add(new Rectangle((int)Double.parseDouble(coordenate[0]), (int)Double.parseDouble(coordenate[1]), (int)Double.parseDouble(coordenate[2]), (int)Double.parseDouble(coordenate[3])));
		}
	}
	
	public void setBombList(String string) {
		bombList.clear();
		String[] rectagulo = string.split("!");
		for (String ms : rectagulo) {
			String[] coordenate = ms.split("-");
			bombList.add(new Rectangle((int)Double.parseDouble(coordenate[0]), (int)Double.parseDouble(coordenate[1]), (int)Double.parseDouble(coordenate[2]), (int)Double.parseDouble(coordenate[3])));
		}
		repaint();	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.YELLOW);
		paintWall(gr, g);
		paintBomb(gr, g);
		graphics = g;
	}
	public void manageMovement(int key){
		this.key = key;
		if(key == 37 && var != 1 && !colition()){
			moveLeft();
		}else if(key == 38 && var != 2 && !colition()){
			moveUp();
		}else if(key == 39 && var != 3 && !colition()){
			moveRight();
		}else if(key == 40 && var != 4 && !colition()){
			moveDown();
		}
	}

	private void moveRight(){
		var = 5;
		my.setLocation(my.getX()+SPACE, my.getY());
		repaint();
	}
	
	private void moveLeft(){
		var = 5;
		my.setLocation(my.getX()-SPACE, my.getY());
		repaint();
	}
	private void moveUp(){
		var = 5;
		my.setLocation(my.getX(), my.getY()-SPACE);
		repaint();
	}

	private void moveDown(){
		var = 5;
		my.setLocation(my.getX(), my.getY()+SPACE);
		repaint();
	}
	
	public void blockMove() {
		switch (key) {
		case 37:
			moveRightD();
			var = 1;
			break;
		case 38:
			moveDownD();
			var = 2;
			break;
		case 39:
			moveLeftD();
			var = 3;
			break;
		case 40:
			moveUpD();
			var = 4;
			break;
		}
	}
	
	private int randomX() {
		return (int)(Math.random()*1280+1);
	}
	
	private int randomY() {
		return (int)(Math.random()*900+1);
	}
	
	private void moveRightD(){
		my.setLocation(my.getX()+SPACE*2, my.getY());
		repaint();
	}

	private void moveLeftD(){
		my.setLocation(my.getX()-SPACE*2, my.getY());
		repaint();
	}
	private void moveUpD(){
		my.setLocation(my.getX(), my.getY()-SPACE*2);
		repaint();
	}

	private void moveDownD(){
		my.setLocation(my.getX(), my.getY()+SPACE*2);
		repaint();
	}

	public boolean colition(){
		for (Rectangle wall : wallList) {
			if (wall.intersects(dead)) {
				return true;
			}
		}
		return false;
	}

//	public void equalsTime(Time time){
//		System.out.println("iguala el tiempo"+time.getSeconds());
//		this.time = time;
//	}

	public synchronized String colitionNickToWall() {
		for (Rectangle wall : wallList) {
			if(wall.intersects(nick)){
				wallList.remove(wall);
				notify();
				return getAllWall();
			}
		}
		return getAllWall();
	}
	
	public synchronized String getwall() {
		for (Rectangle wall : wallList) {
			if(pointNick.getX()-wall.getX() <= 10 ||pointNick.getY()- wall.getY() >= -10) {
				wallList.remove(wall);
				notify();
				return getAllWall();
			}
		}
		JOptionPane.showMessageDialog(null, "no encuentra el muro");
		return getAllWall();
	}

	public String getAllWall(){
		String a = "";
		for (Rectangle wall : wallList) {
			a += wall.getX() + "-" + wall.getY() + "-" + wall.getWidth() + "-" + wall.getHeight() + "!";
		}
		return a;
	}
	public String getPositionNick(){
		return pointNick.getX() +"!" + pointNick.getY();
	}
	public boolean colitionEnemy() {
		return friend.intersects(dead);
	}

	public boolean colitonSword() {
		return sword.intersects(dead);
	}
	
	public boolean colitionNick() {
		return nick.intersects(dead);
	}

	public boolean colitionLatern() {
		return latern.intersects(dead);
	}

	public void equalsNumOfLifes(int numOfLifes){
		numLifes = numOfLifes;
	}

	public void equalsWall(ArrayList<Rectangle> listOfWall){
		wallList = listOfWall;
	}

	public synchronized void paintWall(Graphics2D g, Graphics gra){
		g = (Graphics2D)gra;
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/wall.jpg")).getImage());
		for (Rectangle wall : wallList) {
			g.fill(wall);
			gra.drawImage(imagen.getImage(), (int)wall.getX(), (int)wall.getY(),(int)wall.getWidth(), 50, this);
		}
		paintDead(g, gra);
		paintPuto(g, gra);
		paintWolverine(g, gra);
		paintNick(g, gra);
		paintLifes(gra);
		paintLatern(g, gra);
		paintTime(gra);
		paintSword(g, gra);
		paintMagnet(g, gra);
	}

	private void paintBomb(Graphics2D g, Graphics gra) {
		g = (Graphics2D)gra;
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/bomb.png")).getImage());
		for (Rectangle bomb : bombList) {
			g.setColor(Color.BLACK);
			g.draw(bomb);
			gra.drawImage(imagen.getImage(), (int)bomb.getX(), (int)bomb.getY(),(int)bomb.getWidth(), 20, this);
		}
	}

	public void paintDead(Graphics2D g, Graphics gra){
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/dead.png")).getImage());
		Rectangle dead = new Rectangle((int)my.getLocation().getX(), (int)my.getLocation().getY(), imagen.getIconWidth()-15, imagen.getIconHeight()-10);
		g.setColor(Color.BLACK);
		g.draw(dead);
		gra.drawImage(imagen.getImage(), (int)dead.getX()-7, (int)dead.getY()-8, imagen.getIconWidth(), imagen.getIconHeight(), this);
		this.dead = dead;
	}

	public void paintLatern(Graphics2D g, Graphics gra){
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/latern.png")).getImage());
		Rectangle latern = new Rectangle(500, 600, imagen.getIconWidth()-15, imagen.getIconHeight()-10);
		g.setColor(Color.BLACK);
		g.draw(latern);
		gra.drawImage(imagen.getImage(), (int)latern.getX()-7, (int)latern.getY()-8, imagen.getIconWidth(), imagen.getIconHeight(), this);
		this.latern = latern;
	}

	public void paintWolverine(Graphics2D g, Graphics gra){
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/wolverine.png")).getImage());
		Rectangle wolv = new Rectangle((int)positionWolverine.getLocation().getX(), (int)positionWolverine.getLocation().getY(), imagen.getIconWidth()-15, imagen.getIconHeight()-10);
		g.setColor(Color.BLACK);
		g.draw(wolv);
		gra.drawImage(imagen.getImage(), (int)wolv.getX()-7, (int)wolv.getY()-8, imagen.getIconWidth(), imagen.getIconHeight(), this);
		this.friend = wolv;
	}
	
	public void paintMagnet(Graphics2D g, Graphics gra){
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/wolverine.png")).getImage());
		Rectangle wolv = new Rectangle((int)pointMagnet.getLocation().getX(), (int)pointMagnet.getLocation().getY(), imagen.getIconWidth()-15, imagen.getIconHeight()-10);
		g.setColor(Color.BLACK);
		g.draw(wolv);
		gra.drawImage(imagen.getImage(), (int)wolv.getX()-7, (int)wolv.getY()-8, imagen.getIconWidth(), imagen.getIconHeight(), this);
		this.magnet = wolv;
	}

	public boolean colitionBomb() {
		for (Rectangle bomb : bombList) {
			if (bomb.intersects(dead)) {
				return true;
			}
		}
		return false;
	}
	
	public void paintNick(Graphics2D g, Graphics gra){
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/nick.png")).getImage());
		Rectangle nick = new Rectangle((int)pointNick.getLocation().getX(), (int)pointNick.getLocation().getY(), imagen.getIconWidth()-15, imagen.getIconHeight()-10);
		g.setColor(Color.BLACK);
		g.draw(nick);
		gra.drawImage(imagen.getImage(), (int)nick.getX()-7, (int)nick.getY()-8, imagen.getIconWidth(), imagen.getIconHeight(), this);
		this.nick = nick;
	}

	public void paintSword(Graphics2D g, Graphics gra){
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/sword.png")).getImage());
		Rectangle sword = new Rectangle((int)pointSword.getLocation().getX(), (int)pointSword.getLocation().getY(), imagen.getIconWidth()-15, imagen.getIconHeight()-10);
		g.setColor(Color.BLACK);
		g.draw(sword);
		gra.drawImage(imagen.getImage(), (int)sword.getX()-7, (int)sword.getY()-8, imagen.getIconWidth(), imagen.getIconHeight(), this);
		this.sword = sword;
	}
	
	public void paintLifes(Graphics g){
		int incrementX = 130;
		for (int i = 0; i < numLifes; i++) {
			ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/heart.png")).getImage());
			g.setColor(Color.WHITE);
			g.drawString("Lifes:  ", 100, 20);
			g.drawImage(imagen.getImage(), incrementX, 2, 30, 30, this);
			incrementX += 20;
		}
		repaint();
	}
	public void paintPuto(Graphics2D g, Graphics gra){
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/img/guason.png")).getImage());
		Rectangle dead = new Rectangle((int)pointEnemy.getLocation().getX(), (int)pointEnemy.getLocation().getY(), imagen.getIconWidth()-15, imagen.getIconHeight()-10);
		g.setColor(Color.BLACK);
		g.draw(dead);
		gra.drawImage(imagen.getImage(), (int)dead.getX()-7, (int)dead.getY()-8, imagen.getIconWidth(), imagen.getIconHeight(), this);
		this.enemy = dead;
	}

	public void paintTime(Graphics g){
	}

	public int x(){
		return (int) ((Math.random()*this.getHeight())+20);
	}

	public int y(){
		return (int) ((Math.random()*this.getWidth())+20);
	}

	public void border(){
		if(dead.getX() >= getWidth()){
			moveLeftD();
		}else if(dead.getY() >= getHeight()){
			moveUpD();
		}else if(dead.getX() <= 0){
			moveRightD();
		}else if(dead.getY() <= 0){
			moveDownD();
		}
	}

	public void restartPoint(){
		my.setLocation(200, 240);
	}
	public void setPoint(double x2, double y2) {
		my.setLocation(x2, y2);
	}
	
	

	public void changeLocationNick() {
		int ranX = (int)(Math.random()*1000+10);
		int ranY = (int)(Math.random()*700+10);
		pointNick.setLocation(ranX, ranY);
		repaint();
	}
	
	public void changeLocationlantern() {
		int ranX = (int)(Math.random()*1000+10);
		int ranY = (int)(Math.random()*700+10);
		latern.setLocation(ranX, ranY);
		repaint();
	}
	
	public void quitLifes() {
		if(numLifes > 1) {
			numLifes--;
		}else {
			JOptionPane.showMessageDialog(null, "has mnuerto....");
			System.exit(0);
		}
	}
	public void resetAllPoints() {
		setPoint(20, 20);
//		enemy.resetData(my);
	}
	public void quitLifesZero() {
		numLifes = 1;
		quitLifes();
	}
	public void equalsBomb(ArrayList<Rectangle> bombList) {
		this.bombList = bombList;
	}
	public String getBombWall() {
		for (Rectangle wall : wallList) {
			for (Rectangle bomb : bombList) {
				if(bomb.intersects(wall)) {
					wallList.remove(wall);
					return getAllWall();
				}
			}
		}
		return null;
	}

	public String getMy() {
		return my.getX() + "!" + my.getY();
	}
	
	public String getFriend() {
		return pointEnemy.getX() + "!" + pointEnemy.getY();
	}

	public void setPositionFriend(Point positionFriend) {
		this.pointEnemy = positionFriend;
	}

	public void setPositionFiury(Point point) {
		this.pointNick = point;
	}

	public void setPositionLantern(Rectangle re){
		this.latern = re;
	}
	
	public String getPositionLantern() {
		return latern.getX() + "!" + latern.getY() + "!" + latern.getWidth() + "!" + latern.getHeight();
	}
	
}