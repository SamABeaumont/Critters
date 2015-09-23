import java.awt.Color;
import java.util.Random;



public class EvanS_Chameleon extends Critter{
	public int count;
	private boolean moved = false;
	private double random;

	public Action getMove(CritterInfo info) {
		count ++;
		if(count < 1000){
		if(info.getFront() == Neighbor.OTHER){
			return Action.INFECT;
		}else if(info.getBack() == Neighbor.OTHER && info.getFront() == Neighbor.EMPTY){
			return Action.HOP;
		}else if(info.getRight() == Neighbor.OTHER){
			return Action.RIGHT;
		}else if(info.getLeft() == Neighbor.OTHER){
			return Action.LEFT;
		}else{
			random = Math.random();
			if(random <= .33){
				return Action.LEFT;
			}else if(random <= .66){
				return Action.RIGHT;
			}else{
				return Action.HOP;
			}
		}
		}
		if(info.getFront() == Neighbor.OTHER){
			return Action.INFECT;
		}else if(info.getBack() == Neighbor.OTHER && info.getFront() == Neighbor.EMPTY){
			return Action.HOP;
		}else if(info.getRight() == Neighbor.OTHER && info.getFront() == Neighbor.EMPTY){
			return Action.HOP;
		}else if(info.getLeft() == Neighbor.OTHER && info.getFront() == Neighbor.EMPTY){
			return Action.HOP;
		}else if(info.getRight() == Neighbor.OTHER){
			return Action.RIGHT;
		}else if(info.getLeft() == Neighbor.OTHER){
			return Action.LEFT;
		}else{
			random = Math.random();
			if(random <= .33){
				return Action.LEFT;
			}else if(random <= .66){
				return Action.RIGHT;
			}else{
				return Action.HOP;
			}
		}


	}

	public Color getColor() {
	Random rand = new Random();
	int random = rand.nextInt((256));
	int rand2 = rand.nextInt((256));
	int rand3 = rand.nextInt((256));
	Color c = new Color(random, rand2, rand3);
		return c;
	}

	public String toString() {
		return "C";
	}

}
