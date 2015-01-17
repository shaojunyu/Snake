package game;

public class Updater extends Thread {
	private Board mBoard;
	private Snake mSnake;
	public Updater(Board b,Snake s) {
		 mBoard = b;
		 mSnake = s;
	}
	public void run(){
		while(true){
			mSnake.move(mBoard);
			mBoard.repaint();
			try
			{
				sleep(300);
			}catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}

}
