import java.awt.Color;

public class EvanH_Husky extends Critter {
	private Direction heading = heading();// SET direction of movement at start to either north/south or east/west
	private Direction heading() {
		if (Math.random() < .5) {
			if (Math.random() < .5) {
				return Direction.SOUTH;
			} else {
				return Direction.NORTH;
			}
		} else {
			if(Math.random() < .5) {
				return Direction.EAST;
			} else {
				return Direction.WEST;
			}
		}
	}
	@Override
	public Action getMove(CritterInfo info) {
		if (info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		}
		if (info.getFront() == Neighbor.WALL || info.getFront() == Neighbor.SAME) {
			if (Math.random() < .5) {
			return Action.RIGHT;
			} else {
				return Action.LEFT;
			}
		}
		if (info.getDirection() != heading) {
			return Action.RIGHT;
		}

		return Action.HOP;
	}

	@Override
	public Color getColor() {
		return Color.PINK;
	}

	@Override
	public String toString() {
		int thingy = (int) (100*Math.random());
		return (char)thingy + "";
	}

}
