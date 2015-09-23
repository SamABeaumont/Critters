import java.awt.Color;


public class Kai_Killer extends Critter {


	public Action getMove(CritterInfo info) {
		if(info.getFront() == Neighbor.EMPTY) {
			return Action.HOP;
		} else if(info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		} else if(info.getFront() == Neighbor.SAME || info.getFront() == Neighbor.WALL ) {
			return Action.RIGHT;
		}
		return Action.RIGHT;
	}

	public Color getColor() {
		return Color.BLUE;
	}

	public String toString() {
		return "K";
	}
}
