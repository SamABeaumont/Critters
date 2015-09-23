import java.awt.Color;


public class Noah_Noah extends Critter {
	boolean kill = false;
	int hops = 0;
	static boolean stuff = false;

	public Action getMove(CritterInfo info) {

		if (info.getFront() == Neighbor.OTHER) {
			kill = true;
			return Action.INFECT;
		}
		else if(info.getFront() == Neighbor.EMPTY) {
			return Action.HOP;
		}
		else if(info.getRight() == Neighbor.EMPTY) {
			return Action.RIGHT;
		}
		else {
			return Action.LEFT;
		}

	}



	public Color getColor() {
		if(stuff == true) return Color.RED;
		else return Color.BLUE;
	}
	public String toString() {
		if(stuff == false) {
			stuff  = !stuff;
			return "N";

		}
		else {
			stuff = !stuff;
			return "H";
		}
	}
}
