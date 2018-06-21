package models;

import java.awt.Point;

public class Enemy extends MyThread{

	private int ubic;
	private Point pointEnemy, pointPlayer;
	
	public Enemy(Point point) {
		this.ubic = 200;
		this.pointPlayer = point;
		this.pointEnemy=new Point(-100, -100);
		this.pointEnemy.setLocation(point.getX()-ubic, point.getY()-ubic);
		start();
	}
	
	@Override
	void executeTask() {
		ubic -= 1;
		pointEnemy.setLocation(pointPlayer.getX()-ubic, pointPlayer.getY()-ubic);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Point getPointEnemy() {
		return pointEnemy;
	}
	
	public void updatePointPlayer(Point player) {
		this.pointPlayer = player;
	}

	public void resetData(Point point) {
		pause();
		this.ubic = 200;
		this.pointPlayer = point;
		this.pointEnemy=new Point(-100, -100);
		this.pointEnemy.setLocation(point.getX()-ubic, point.getY()-ubic);
		resume();
	}
}