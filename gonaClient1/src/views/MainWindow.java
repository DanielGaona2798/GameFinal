package views;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.Controller;

public class MainWindow  extends JFrame{

	private static final long serialVersionUID = 1L;
	private PanelDrawing panelDrawing;
	public MainWindow(Controller controller){
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panelDrawing = new PanelDrawing(controller);
		add(panelDrawing, BorderLayout.CENTER);
		this.addKeyListener(controller);
		setVisible(true);
		setVisible(false);
	}
	
	public void manage(int botton){
		panelDrawing.manageMovement(botton);
	}

	public void equalsLifes(int numLifes){
		panelDrawing.equalsNumOfLifes(numLifes);
	}

	public boolean colition(){
		return panelDrawing.colition();
	}

	public boolean colitionSword(){
		return panelDrawing.colitonSword();
	}
	
	public void quitLifes() {
		panelDrawing.resetAllPoints();
		panelDrawing.quitLifes();
	}
	
	public void quitLifesZero() {
		panelDrawing.resetAllPoints();
		panelDrawing.quitLifesZero();
	}
	
	public void equalsWall(ArrayList<Rectangle> listOfWall){
		panelDrawing.equalsWall(listOfWall);
	}

	public int x() {
		return panelDrawing.x();
	}

	public int y() {
		return panelDrawing.y();
	}

	public void re(){
		panelDrawing.repaint();
	}


	public void restarPoint(){
		panelDrawing.restartPoint();
	}

	public void setPoint(double x, double y) {
		panelDrawing.setPoint(x, y);
	}

	public void blockMove() {
		panelDrawing.blockMove();
	}

	public boolean colitionEneny() {
		return panelDrawing.colitionEnemy();
	}

	public boolean colitionNick() {
		return panelDrawing.colitionNick();
	}
	
	public boolean colitionLatern() {
		return panelDrawing.colitionLatern();
	}
	
	public String getWall(){
		return panelDrawing.getwall();
	}

	public String getWallNick(){
		return panelDrawing.colitionNickToWall();
	}
	public String getPositionNick(){
		return panelDrawing.getPositionNick();
	}
//	public void equalsTime(Time time){
//		panelDrawing.equalsTime(time);
//	}
	
	public void changeLocationNick() {
		panelDrawing.changeLocationNick();
	}

	public void changeLocationLantern() {
		panelDrawing.changeLocationlantern();
	}
	
	public void border() {
		panelDrawing.border();
	}

	public boolean colitionBomb() {
		return panelDrawing.colitionBomb();
	}
	
	public void equalsBomb(ArrayList<Rectangle> bombList) {
	panelDrawing.equalsBomb(bombList);
	}

	public String getBombWall() {
		return panelDrawing.getBombWall();
	}

	public void setWallList(String a) {
		panelDrawing.setWallList(a);
	}
	
	public String getMy() {
		return panelDrawing.getMy();
	}
	
	public void setPointFriend(Point pointFriend) {
		this.panelDrawing.setPositionFriend(pointFriend);
	}

	public void setBombList(String string) {
		this.panelDrawing.setBombList(string);
	}

	public void setPointFiury(Point point) {
		this.panelDrawing.setPositionFiury(point);
		
	}

	public String getPositionLantern() {
		return panelDrawing.getPositionLantern();
	}

	public void setpointLantern(Rectangle rectangle) {
		this.panelDrawing.setPositionLantern(rectangle);
	}
}