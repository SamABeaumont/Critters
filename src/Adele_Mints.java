import java.awt.Color;



public class Adele_Mints extends Critter {
	int count = 0;

	public Action getMove(CritterInfo info) {
		if (info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		} else if (info.getFront() == Neighbor.SAME){
			return Action.RIGHT;
		}	else if (info.getLeft() == Neighbor.SAME) {
			return Action.RIGHT;
		} else if (info.getFront() == Neighbor.WALL){
			return Action.LEFT;
		} else if (info.getLeft() == Neighbor.WALL) {
			return Action.RIGHT;
		} else {
			return Action.HOP;
		}
	}

	public Color getColor() {
		return Color.CYAN;
	}

	public String toString() {
		return "M";
	}
}
