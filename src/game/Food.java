package game;

import java.util.Random;

public class Food extends Body{

	public Food() {
		super(0,0);
		Random rdm = new Random();
		this.row = rdm.nextInt(Board.Row);
		this.col = rdm.nextInt(Board.Column);
	}

}
