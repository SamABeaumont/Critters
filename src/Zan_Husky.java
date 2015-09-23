import java.awt.*;

public class Zan_Husky extends Critter {

	//Visual representation of the critter
	private char symbol;
	//Number of spaces to move in its "run" state (priority move forward)
	private int run;
	//Used for calculating random moves
	private int random;
	/* Color to be displayed
	 * Blue: normal
	 * Cyan: running
	 * Red: infecting */
	private Color color;

	public Zan_Husky() {
		run = 6;
		color = Color.cyan;
	}

	/* Goes through possible moves in order of priority
	 * Each test that returns an output has the precondition of the previous tests all failing */
	public Action getMove(CritterInfo info) {

		//Sets color according to run state
		if (run > 0)
			color = Color.cyan;
		else
			color = Color.blue;

		//First priority: infect
		if (info.getFront() == Neighbor.OTHER) {
			color = Color.red;
			return Action.INFECT;
		}

		//Second priority: avoid obstacles
		if (info.getFront() != Neighbor.EMPTY) {
			if (info.getFront() == Neighbor.WALL && run == 0)
				run = 3;
			if (info.getRight() == Neighbor.OTHER)
				return Action.RIGHT;
			if (info.getLeft() == Neighbor.OTHER)
				return Action.LEFT;
			if (info.getRight() == Neighbor.EMPTY)
				return Action.RIGHT;
			if (info.getLeft() == Neighbor.EMPTY)
				return Action.LEFT;
			return Action.RIGHT;
		}

		//Third priority: set run state if enemies nearby
		setRun(info);

		//Fourth priority: move forward if in run state and the way is clear
		if (run > 0) {
			run--;
			return Action.HOP;
		}

		//Fifth priority: move away from friends (to spread out)
		if (info.getRight() == Neighbor.SAME && info.getLeft() == Neighbor.EMPTY)
			return Action.LEFT;

		//Final priority: random move (forward, or turn towards and open direction)
		return randomMove(info);
	}

	/* Activates run mode if enemies are in currently unreachable adjacent spaces
	 * Can run up to 8 spaces, determined by how many critters they have infected
	 * Precondition: forward is open
	 */
	private void setRun(CritterInfo info) {
		if (info.getRight() == Neighbor.OTHER || info.getLeft() == Neighbor.OTHER || info.getBack() == Neighbor.OTHER) {
			run = 3 + info.getInfectCount();
			color = Color.cyan;
		}
		if (run > 8)
			run = 8;
	}

	/* Randomly moves up, turns right, or turns left depending on what directions are open
	 * All random decisions give EQUAL PROBABILITY to all outcomes
	 * Precondition: forward is open
	 */
	private Action randomMove(CritterInfo info) {

		if (info.getRight() == Neighbor.EMPTY) {

			if (info.getLeft() == Neighbor.EMPTY) {
				//Right and left are open
				random = (int)(Math.random()*3);
				if (random == 2)
					return Action.RIGHT;
				if (random == 1)
					return Action.LEFT;
			}

			//Right is open
			random = (int)(Math.random()*2);
			if (random == 1)
				return Action.RIGHT;
		}

		if (info.getLeft() == Neighbor.EMPTY) {
			//Left is open
			random = (int)(Math.random()*2);
			if (random == 1)
				return Action.LEFT;
		}

		//Neither right nor left are open
		return Action.HOP;

	}

	//Returns the current color
	public Color getColor() {
		return color;
	}

	//Returns a random capital letter
	public String toString() {
		symbol = (char)(Math.random()*26 + 65);
		return symbol+"";
	}

}
