import java.awt.*;
import java.util.*;

public class Lion extends Critter {
	private int count = 0;
	private Color color;
	
	public Action getMove(CritterInfo info) {
		count++;
		if (info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		} else if (info.getFront() == Neighbor.WALL || info.getRight() == Neighbor.WALL) {
			return Action.LEFT;
		} else if (info.getFront() == Neighbor.SAME) {
			return Action.RIGHT;
		} else {
			return Action.HOP;
		}
	}

	public Color getColor() {
		if (count % 3 == 0) {
			int color = new Random().nextInt(3);
			if (color == 0) {
				this.color = Color.RED;
			} else if (color == 1) {
				this.color = Color.GREEN;
			} else {
				this.color = Color.BLUE;
			}
		}
		
		return this.color;
	}

	public String toString() {
		return "L";
	}
}
