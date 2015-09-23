import java.awt.Color;

public class Flynn_Husky extends Critter{
	public int moveCount;
	public int form;
	public boolean moveToggle;
	public Flynn_Husky() {
		form = (int)(Math.random()*4);
		moveToggle = true;
		moveCount = 1;
	}
	public  Action getMove(CritterInfo info) {
		moveCount++;
		if (moveCount==25) {
			moveCount=1;
			if (form==3) {
				form = 0;
			} else {
				form++;
			}
		}
		if (form==0) { //LION
			if (info.getFront()==Neighbor.OTHER) {
				return Action.INFECT;
			} else if (info.getFront()==Neighbor.WALL||info.getRight()==Neighbor.WALL) {
				return Action.LEFT;
			} else if (info.getFront()==Neighbor.SAME) {
				return Action.RIGHT;
			} else {
				return Action.HOP;
			}
		} else if (form==1) { //BEAR
			moveToggle=!moveToggle;
			if (info.getFront()==Neighbor.OTHER) {
				return Action.INFECT;
			} else if (info.getFront()==Neighbor.EMPTY) {
				return Action.HOP;
			} else {
				return Action.LEFT;
			}
		} else if (form==2) { //Giant
			if (info.getFront()==Neighbor.OTHER) {
				return Action.INFECT;
			} else if (info.getFront()==Neighbor.EMPTY) {
				return Action.HOP;
			} else {
				return Action.RIGHT;
			}
		} else if (form==3) { //FlyTrap
			if (info.getFront() == Neighbor.OTHER) {
	            return Action.INFECT;
	        } else {
	            return Action.LEFT;
	        }
		} else {
			form=0;
			return Action.INFECT;
		}
	}
    public Color getColor() {
    	return Color.MAGENTA;
    }
    public String toString() {
    	if (form==0) {
    		return "L";
    	} else if (form==1) {
    		if (moveToggle) return "/";
        	return "\\";
    	} else if (form==2) {
    		if (moveCount==25) moveCount=1;
        	if (moveCount>0&&moveCount<=6) return "fee";
        	if (moveCount>6&&moveCount<=12) return "fie";
        	if (moveCount>12&&moveCount<=18) return "foe";
        	if (moveCount>18&&moveCount<=24) return "fum";
        	return "ERROR";
    	} else if (form==3) {
    		return "T";
    	} else {
    		form=0;
    		return "ERROR";
    	}
    }
}
