import java.awt.*;

public class Ariel_Cat extends Critter{
	private int count ;

	public Action getMove(CritterInfo info) {

		if (info.getFront() == Neighbor.OTHER ) {
			return Action.INFECT;
		}
		if (info.getFront() == Neighbor.WALL) {
			return Action.LEFT;
		}
		if (info.getFront() == Neighbor.SAME) {
			return Action.RIGHT;
		}else{
			return Action.HOP;
		}

	}

	public Color getColor() {
		return Color.WHITE;
	}

	public String toString() {
		return "C";
	}

}
