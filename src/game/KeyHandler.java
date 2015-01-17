package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{
	private Snake mSnake;
	private Board mBoard;
	public KeyHandler(Snake s,Board b) {
		mSnake = s;
		mBoard = b;
	}
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			mSnake.changeDirection(DIRECTION.UP);
			break;
		case KeyEvent.VK_DOWN:
			mSnake.changeDirection(DIRECTION.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			mSnake.changeDirection(DIRECTION.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			mSnake.changeDirection(DIRECTION.RIGHT);
			break;
		case KeyEvent.VK_F5:
			mSnake.reset();
			mBoard.setState(STATE.RUN);
			break;
		}
	}
}
