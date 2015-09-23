import java.awt.Color;

public class Lukas_Husky extends Critter {
	private int moves;
	private boolean moveToWall;
	private boolean findWall;
	private boolean onWall;
	private boolean finalPosition;
	private boolean isBottom;

	private Critter.Direction[] bottomDirections;
	private Critter.Direction[] topDirections;

	public Lukas_Husky() {
		this.moves = 0;
		this.moveToWall = true;
		this.findWall = false;
		this.onWall = false;
		this.finalPosition = false;
		this.isBottom = true;

		this.bottomDirections = new Critter.Direction[]{Direction.EAST, Direction.NORTH, Direction.WEST};
		this.topDirections = new Critter.Direction[]{Direction.EAST, Direction.SOUTH, Direction.WEST};
	}

	public Action getMove(CritterInfo info) {
		this.moves++;
		Critter.Neighbor front = info.getFront();

		if (front == Neighbor.OTHER){
			return Action.INFECT;
		}

		//System.out.println(this.finalPosition);

		if (this.finalPosition) {
			if (this.moves > 50) {
				if (this.moves / 50 % 2 == 0) {
					this.isBottom = true;
				} else {
					this.isBottom = false;
				}

				this.findWall = true;
				this.finalPosition = false;

				/*
				if (info.getDirection() != Direction.NORTH) {
					return Action.LEFT;
				}

				return Action.HOP;
				*/
			}

			Critter.Direction[] directions;
			if (this.isBottom) {
				directions = bottomDirections;
			} else {
				directions = topDirections;
			}

			return this.lookAround(directions, info);
		}

		if (this.findWall) {
			if (front == Neighbor.WALL) {
				this.moveToWall = false;
				this.findWall = false;
				this.onWall = true;
				return Action.LEFT; // or return null?
			}

			if (info.getRight() == Neighbor.EMPTY && info.getDirection() == Direction.EAST) {
				return Action.RIGHT;
			}

			if (front == Neighbor.EMPTY) {
				return Action.HOP;
			}

			Critter.Direction direction;
			if (this.isBottom) {
				direction = Direction.SOUTH;
			} else {
				direction = Direction.NORTH;
			}

			if (info.getDirection() == direction) {
				return Action.LEFT;
			}
		}

		if (this.onWall) {
			if (info.getDirection() != Direction.NORTH) {
				return Action.LEFT;
			} else {
				this.finalPosition = true;
				return null;
			}
		} else if (this.moveToWall) {
			if (info.getDirection() != Direction.SOUTH) {
				return Action.LEFT;
			} else if (front == Neighbor.WALL) {
				this.onWall = true;
				return Action.LEFT;
			} else if (front == Neighbor.SAME) {
				this.findWall = true;
			}

			if (this.onWall) {
				if (info.getDirection() != Direction.NORTH) {
					return Action.LEFT;
				}

				this.finalPosition = true;
				return null;
			}

			return Action.HOP;
		}

		/*
		if (this.moves > 3) {
			this.moveToWall = true;
		}
		*/

		if (front == Neighbor.OTHER) {
			return Action.INFECT;
		} else if (front == Neighbor.EMPTY) {
			return Action.HOP;
		} else {
			// else SAME or WALL
			return Action.LEFT;
		}
	}

	public Action lookAround(Direction[] directions, CritterInfo info) {
		if (info.getDirection() == directions[1]) {
			double nextTurn = Math.random();
			return (nextTurn < 0.5) ? Action.LEFT : Action.RIGHT;
		} else if (info.getDirection() == directions[0]) {
			return Action.LEFT;
		} else if (info.getDirection() == directions[2]) {
			return Action.RIGHT;
		}

		return null;
	}

	public Color getColor() {
		return Color.BLACK;
	}

	public String toString() {
		return "I";
	}

}