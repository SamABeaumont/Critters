import java.awt.Color;

/**
 * Jacob Kohn
 * March 13, 2015
 * APCS, Period 1
 * My version of the husky class: Tiger
 */


public class Jacob_Tiger extends Critter {
	int colorCount = 0;
	int moveCount = 0;
	int type = 0;


	/**
	 * Sets the type of the class
	 * Type 0 circles walls, Type 0 turns right instead of continuing going around walls
	 */
	public Jacob_Tiger() {
		double d = Math.random();
		if(d<.5) {
			type = 1;
		} else {
			type = 0;
		}
	}

	@Override
	public Action getMove(CritterInfo info) {
		if (info.getFront() == Neighbor.OTHER) {
			colorCount++;
			return Action.INFECT;
		} else if(info.getRight() == Neighbor.OTHER) {
			colorCount++;
			return Action.RIGHT;
		} else if(info.getLeft() == Neighbor.OTHER) {
			colorCount++;
			return Action.LEFT;
        } else if(info.getFront() == Neighbor.WALL) {
        	if(type == 1) {
        		colorCount++;
        		return Action.RIGHT;
        	}
        	colorCount++;
            return Action.LEFT;
        } else if(info.getFront() == Neighbor.SAME) {
        	colorCount++;
        	return Action.RIGHT;
        } else {
        	colorCount++;
        	return Action.HOP;
        }
	}

	@Override
	/**
	 * Changes color from orange to black
	 */
	public Color getColor() {
		if(colorCount==12) { colorCount = 0; }
		if(colorCount < 6) {
			return Color.orange;
		} else {
			return Color.black;
		}
	}

	@Override
	public String toString() {
		return "T";
	}

}


