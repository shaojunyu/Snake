package game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Board extends Frame{
	public static final int Width = 350;
	public static final int Height = 300;
	public static final int TileWidth = 15;
	public static final int TileHeight = 15;
	public static final int Row = 16;
	public static final int Column = 20;
	public static final int XOffset = (Width - Column*TileWidth)/2;
	public static final int YOffset = (Height - Row*TileHeight)/2;
	
	private Snake mSnake;
	private Updater mUpdater;
	private Food mFood;
	private STATE mState;
	
	public Board(String title){
		setTitle(title);
		setBounds(100,100,Width,Height);
		setVisible(true);
		setResizable(false);
		setState(STATE.RUN);
		mSnake = new Snake();
		mFood = produceFood();
		mUpdater = new Updater(this, mSnake);
		mUpdater.start();
		addWindowListener(new windowHandler());
		addKeyListener(new KeyHandler(mSnake,this));
	}
	public void setState(STATE s){
		mState = s;
	}
	public void drawDeadMessage(Graphics g){
		g.drawString("Sorry, you dead.press F5 to retry!", XOffset, Height/2);
	}
	public void drawDecoration(Graphics g){
		g.setColor(Color.RED);
		g.drawRect(XOffset, YOffset, TileWidth*Column, TileHeight*Row);
	}
	public void drawSnake(Graphics g){
		g.setColor(Color.blue);
		for(int i=0;i<mSnake.getLength();i++){
			Body mBody = mSnake.getBody(i);
			g.fillRect(mBody.col*TileWidth + XOffset, mBody.row*TileHeight + YOffset, TileWidth, TileHeight);
		}
	}
	public void drawFood(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(XOffset+mFood.col*TileWidth, YOffset+mFood.row*TileHeight, TileWidth, TileHeight);
	}
	public Food produceFood(){
		boolean finished;
		Food food;
		do
		{
			finished = true;
			food = new Food();
			for(int i=0;i<mSnake.getLength();i++){
				if(mSnake.getBody(i).col == food.col && mSnake.getBody(i).row == food.row){
					finished = false;
				}
			}
			
		}while(!finished);
		mFood = food;
		return food;
	}
	public Food getFood(){
		return mFood;
	}
	public void paint(Graphics g){
		switch(mState)
		{
		case RUN:
			drawSnake(g);
			drawFood(g);
			drawDecoration(g);
			break;
		case DEAD:
			drawDeadMessage(g);
			break;
		}
	}
	public static void main(String args[]){
		Board board = new Board("Snake -- by William and Shawn");
	}

}

class windowHandler extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
}