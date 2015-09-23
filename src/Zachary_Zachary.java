import java.awt.Color;

public class Zachary_Zachary extends Critter {

	private double decide = Math.random();
	private int kill = 0;
	private int count = 0;

	public Action getMove(CritterInfo info) {
		count++;
		kill = info.getInfectCount();
		if(info.getFront() == Neighbor.OTHER){
			return Action.INFECT;
		} else if (info.getLeft() == Neighbor.OTHER){
			return Action.LEFT;
		} else if (info.getRight() == Neighbor.OTHER){
			return Action.RIGHT;
		} else if (info.getBack() == Neighbor.OTHER){
			return Action.LEFT;
		}


		if(count < 600){
			return globHusky(info);
		} else {
			return attackHusky(info);
		}

	}

	private Action globHusky(CritterInfo info){
		// deal with friendly stand-off
		if(info.getFront() != Neighbor.SAME || info.getLeft() != Neighbor.SAME
				|| info.getRight() != Neighbor.SAME || info.getBack() != Neighbor.SAME){

			if (info.getFront() == Neighbor.WALL){
				if(Math.random() < .5){
					return Action.LEFT;
				} else {
					return Action.RIGHT;
				}
			} else if(info.getRight() == Neighbor.WALL){
				if(Math.random() < 0.5){
					return Action.LEFT;
				} else {
					return Action.HOP;
				}
			} else {
				return Action.HOP;
			}
		} else {
			return null;
		}
	}

	private Action attackHusky(CritterInfo info){
		if (info.getFront() == Neighbor.SAME || info.getFront() == Neighbor.WALL){
			if(decide < .5){
				return Action.LEFT;
			} else {
				return Action.RIGHT;
			}
		} else if (info.getRight() == Neighbor.WALL){
			if(decide < .5){
				return Action.LEFT;
			} else {
				return Action.HOP;
			}
		} else {
			return Action.HOP;
		}
	}


	public Color getColor() {
		if(kill > 40){
			return Color.BLACK;
		}
		return Color.MAGENTA;
	}

	public String toString() {
		if(kill < 2){
			return "1";
		} else if (kill < 10){
			return "2";
		} else if (kill < 20){
			return "3";
		} else if (kill > 40){
			return "*";
		} else {
			return "4";
		}
	}

}
