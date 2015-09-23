import java.awt.Color;

public class SamuelM_Husky extends Critter{

	public SamuelM_Husky(){

	}

	public Action getMove(CritterInfo info) {
		if (info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		}else if(info.getFront() == Neighbor.SAME){
			return Action.RIGHT;
		}else if(info.getLeft() == Neighbor.SAME){
			return Action.RIGHT;
		}else if(info.getBack() == Neighbor.SAME){
			return Action.RIGHT;
		}else if(info.getRight() == Neighbor.SAME){
			return Action.RIGHT;
		}else if(info.getFront() == Neighbor.WALL){
			return Action.LEFT;
		}else{
			return Action.HOP;
		}
	}


	public Color getColor() {
		return Color.PINK;
	}


	public String toString() {

		return ":3";
	}

}
