import java.awt.Color;

public class Sita_Husky extends Critter{
public int infected = 0;
public int move = 0;
	public Action getMove(CritterInfo info) {
		if (info.getFront() == Neighbor.OTHER) {
			move++;
			infected++;
            return Action.INFECT;
        }
		else if(info.getLeft() == Neighbor.OTHER && infected < (move - 10) ){
			return Action.LEFT;
		}
		else if(info.getRight() == Neighbor.OTHER && infected < (move - 10) ){
			return Action.RIGHT;
		}
		else if(info.getFront() == Neighbor.SAME){
			return Action.HOP;
		}
		else if(info.getFront() == Neighbor.WALL){
			if(info.getRight() == Neighbor.OTHER){
				return Action.RIGHT;
			}
			else{
				return Action.LEFT;
			}
		}
		else{
			return Action.HOP;
		}
	}

	public Color getColor() {
		return Color.MAGENTA;
	}

	public String toString() {
		return "*";
	}

}
