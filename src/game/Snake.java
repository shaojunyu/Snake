package game;

import java.util.ArrayList;
import java.util.List;

enum DIRECTION{
	UP,DOWN,LEFT,RIGHT;
}
public class Snake {
	private List<Body> mBody;
	private DIRECTION mDirection;
	
	public Snake() {
		mBody = new ArrayList<Body>();
		reset();
	}
	
	public void reset(){
		mBody.clear();
		mBody.add(new Body(7,4));
		mBody.add(new Body(7,3));
		mBody.add(new Body(7,2));
		mBody.add(new Body(7,1));
		mDirection = DIRECTION.RIGHT;
	}
	
	//change the moving direction of snake
	public void changeDirection(DIRECTION d){
		switch(d)
		{
		case UP:
			if(mDirection == DIRECTION.DOWN){
				return;
			}
			break;
		case DOWN:
			if(mDirection == DIRECTION.UP){
				return;
			}
			break;
		case LEFT:
			if(mDirection == DIRECTION.RIGHT){
				return;
			}
			break;
		case RIGHT:
			if(mDirection == DIRECTION.LEFT){
				return;
			}
			break;
		}
		mDirection = d;
	}
	
	//how to move
	public void move(Board b){
		Body head;
		Body headold = mBody.get(0);
		switch(mDirection)
		{
		case UP:
			head = new Body(headold.row-1,headold.col);
			break;
		case DOWN:
			head = new Body(headold.row+1,headold.col);
			break;
		case LEFT:
			head = new Body(headold.row,headold.col-1);
			break;
		case RIGHT:
			head = new Body(headold.row,headold.col+1);
			break;
		default:
			head  = new Body(0,0);
			break;
		}
		
		//eat food
		Food f = b.getFood();
		if(f.row == head.row && f.col == head.col){
			mBody.add(0,f);
			b.produceFood();
		}
		else{
			mBody.remove(mBody.size()-1);
			mBody.add(0,head);
		}
		
		//check collision
		if(checkBodyCollision() || checkBoundCollision(b)){
			b.setState(STATE.DEAD);
		}
	}
	
	//get a part of snake
	public Body getBody(int index){
		return mBody.get(index);
	}
	
	//get  the length of snake 
	public int getLength(){
		return mBody.size();
	}
	
	//check if the snake have bitten itself
	public boolean checkBodyCollision(){
		Body head = getBody(0);
		for(int i=1;i<getLength();i++){
			if(head.col == getBody(i).col && head.row == getBody(i).row){
				return true;
			}
		}
		return false;
	}
	
	//check if the snake have bump into the bound
	public boolean checkBoundCollision(Board b){
		Body head = getBody(0);
		if(head.col >= b.Column || head.col < 0 || head.row >= b.Row || head.row < 0){
			return true;
		}
		return false;
	}

}

//this class  is designed to define a part of snake 
//each part of snake can be seen as a RECT
//and we should know the position of each RECT
class Body{
	public int row;
	public int col;	//col = column
	public Body(int row,int col){
		this.row = row;
		this.col = col;
	}
}