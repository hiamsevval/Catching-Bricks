import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class FinalProject extends GraphicsProgram {
	public static final int APPLICATION_WIDTH = 600;
	public static final int APPLICATION_HEIGHT = 600;
	public static final int RECT_WIDTH=60;
	public static final int RECT_HEIGHT=20;
	public static final int LIVES=3;
	ArrayList <GRect> Rect=null;
	int counter=0;
	int start=0;

	GRect rect;

	GLabel label;
	GLabel score;

	GImage background;
	GImage heart;
	GImage heart2;
	GImage heart3;




	private RandomGenerator rgen = RandomGenerator.getInstance();
	public void run() {
		int plays=LIVES;

		addMouseListeners();

		while(plays>0) { 
			addAA();
			addScore();
			if(plays==3) {
				addHeart1();
				addHeart2();
				addHeart3();
			}
			if(plays==2) {
				addHeart1();
				addHeart2();
			}
			if(plays==1) {
				addHeart1();
			}

			displayMessage("Click to Start");
			Rect= new ArrayList<GRect>();
			double speed=-0.7;
			waitForClick();
			remove(label);
			while(true) {
				remove(score);
				addScore();
				boolean gameOver=false;
				for(int i=0; i<Rect.size(); i++) {
					GRect n=Rect.get(i);
					n.move(0.0,speed);

					if(rgen.nextBoolean(0.01)) {
						speed=speed-0.01;
					}

					if(n.getY()<=0) {
						remove(rect);
						plays=plays-1;
						gameOver=true;
						break;
					}

				}
				if(gameOver) {
					break;
				}
				pause(10);
				if(rgen.nextBoolean(0.01)) {

					Rect.add(createRect());
				}

			}
		}
		addAA();
		displayMessage("GAME OVER!");

	}

	private GRect createRect() {
		int x= rgen.nextInt(0,getWidth()-RECT_WIDTH);
		rect=new GRect(RECT_WIDTH,RECT_HEIGHT);
		rect.setFilled(true);
		rect.setColor(rgen.nextColor());
		add(rect,x,getHeight());
		return rect;
	}

	private void displayMessage(String message) {
		label= new GLabel(message);
		label.setFont("Elephant-50");
		label.setLocation(getWidth()/2-label.getWidth()/2, getHeight()/2-label.getHeight()/2);
		label.setColor(Color.red);
		add(label);
	}

	public void mouseClicked(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		GObject object=getElementAt(x,y);
		if(object!=null && object!=background) {
			remove(object);
			Rect.remove(object);
			counter=counter+1;
		}


	}

	private void addAA() {

		background = new GImage("1.png");

		background.scale(1);

		add(background,0,0);

	}

	private void addHeart1() {

		heart = new GImage("2.png");

		heart.scale(0.5);

		add(heart, getWidth()-30,0);

	}

	private void addHeart2() {

		heart2 = new GImage("2.png");

		heart2.scale(0.5);

		add(heart2, getWidth()-60,0);

	}

	private void addHeart3() {

		heart3 = new GImage("2.png");

		heart3.scale(0.5);

		add(heart3, getWidth()-90,0);

	}

	private void addScore() {
		println("score");
		score= new GLabel("Score:"+counter);
		score.setColor(Color.RED);
		score.setFont("Elephant-20");
		add(score,1,15);
		score.sendToFront();
	}


}


