import java.awt.Color;
import java.util.Random;




public class Connor_Husky extends Critter{
	private Random r = new Random();
	private int num;
	private int critter = 1;
	public Connor_Husky() {
		num = r.nextInt(3);
	}

	public String toString(){
		return "+";
	}
	public Color getColor() {
		if(critter==1) {
		return new Color(189,32,189);
		} else if(critter==2) {
			return Color.ORANGE;
		} else {
			return Color.BLACK;
		}
	}
	/*
	 * Basically, my critter has a (2/3) chance of being a giant (purple), and a (1/3) chance of being a lion (orange).
	 * If it has infected more than 15 critters, it becomes a fly trap (black).
	 * Each type has its own color.
	 */
	public Action getMove(CritterInfo info) {
		if(info.getInfectCount()>15) {
			critter = 3;
			 if (info.getFront() == Neighbor.OTHER) {
		            return Action.INFECT;
		        } else {
		            return Action.LEFT;
		        }
		}
		if(num<=1 ) {
			critter = 1;
			if(info.getFront()==Neighbor.OTHER) {
				return Action.INFECT;
			}
			if(info.getFront()==Neighbor.EMPTY){
				return Action.HOP;
			} else {
				return Action.RIGHT;
			}
		} else {
			critter = 2;
			if(info.getFront()==Neighbor.OTHER) {
				return Action.INFECT;
			} else if(info.getFront()==Neighbor.WALL || info.getRight()==Neighbor.WALL) {
				return Action.LEFT;
			} else if(info.getFront()==Neighbor.SAME) {
				return Action.RIGHT;
			} else {
				return Action.HOP;
			}
		}


	}
}
