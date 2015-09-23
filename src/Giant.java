import java.awt.*;

public class Giant extends Critter {
	private int move = -1;
	
	public Action getMove (CritterInfo info) {
		move++;
		if (info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		} else if (info.getFront() == Neighbor.EMPTY) {
			return Action.HOP;
		} else {
			return Action.RIGHT;
		}
	}
	
	public Color getColor () {
		return Color.GRAY;
	}
	
	public String toString () {
		int which = move % 24;
		if (which < 6) {
			return "fee";
		} else if (which < 12) {
			return "fie";
		} else if (which < 18) {
			return "foe";
		} else {
			return "fum";
		}
	}
}
