package models;

import java.awt.Rectangle;


public class Wall extends GameThread{

	private Rectangle  recatngle;
	private Boolean state = true;
	
	public Wall(Rectangle recatngle) {
		this.recatngle = recatngle;
		int sleep = (int)(Math.random() *200)+1;
		sleep(sleep);
	}
	

	public Rectangle getRecatngle() {
		return recatngle;
	}
	
	@Override
	public
	void executeTask() {
		if (recatngle.getY() >= 800) {
			state = false;
		}else if(recatngle.y <= 0){
			state = true;
		}
		if (state == true) {
			recatngle.setLocation(recatngle.x, recatngle.y+30);
		}else if(state == false){
			recatngle.setLocation(recatngle.x, recatngle.y-30);
		}
	}
}
