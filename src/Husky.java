import java.awt.*;
import java.util.*;

/**
 * My absolutely awesomely powerful critter
 * @author Sam Beaumont
 */
public final class Husky extends Critter implements Comparable<Husky> {
	private static final Random r = new Random();
	
	private int id;
	private Direction dir;
	private boolean isCounter;
	private int startCount;
	private int moveCount;
	private int kills;
	private Stack<Action> nextMoves = new Stack<Action>();
	private Point loc;
	private Point prevLoc;
	private Point distanceFromStart;
	
	/**
	 * For use in binary searches of the {@link huskies} {@link ArrayList}
	 * @param id The ID to be assigned
	 */
	private Husky (int id) {
		this.id = id;
	}
	
	/**
	 * Initializes a new {@code Husky} object
	 */
	public Husky () {
		id = Data.nextId();
		startCount = Data.getSteps();
		moveCount = 0;
		kills = 0;
		distanceFromStart = new Point(false);
		if (id == 0) {
			Data.setCorner();
			isCounter = true;
		}
		Data.add(this);
	}
	
	/**
	 * Computes and returns the move that this {@code Husky} makes
	 * @param info The {@link CritterInfo} object that is used to compute the move
	 */
	public Action getMove (CritterInfo info) {		
		Neighbor front = info.getFront();
		Neighbor back = info.getBack();
		Neighbor right = info.getRight();
		Neighbor left = info.getLeft();
		dir = info.getDirection();
		Action move = null;
		
		setLoc(front, back, right, left, dir);
		
		if (front == Neighbor.OTHER) { // enemy is in front, so we infect
			move = Action.INFECT;
		} else if (right == Neighbor.OTHER) { // enemy is to our right, so we turn right
			move = Action.RIGHT;
		} else if (left == Neighbor.OTHER) { // enemy is to our left, so we turn left
			return Action.LEFT;
		} else if (back == Neighbor.OTHER) { // enemy is to our back, so we rotate to face them
			return Action.RIGHT;
		} else if (!nextMoves.isEmpty()) { // the next move has already been specified
			Action nextMove = nextMoves.pop();
			if (move == Action.HOP) {
				move = hop(dir);
			} else {
				 move = nextMove;
			}
		} else { // make our way to the corner
			if (rotate(dir) == rotate(Direction.NORTH)) {
				move = Action.LEFT;
			} else if (rotate(dir) == rotate(Direction.SOUTH)) {
				nextMoves.push(Action.HOP);
				if (right == Neighbor.EMPTY) {
					move = Action.RIGHT;
				} else {
					move = nextMoves.pop();
				}
			} else if (rotate(dir) == rotate(Direction.EAST)) {
				move = Action.RIGHT;
			} else { // rotate(dir) == rotate(Direction.WEST)
				nextMoves.push(Action.HOP);
				if (left == Neighbor.EMPTY) {
					move = Action.LEFT;
				} else {
					move = nextMoves.pop();
				}
			}
		}
		
		moveCount++;
		if (move == Action.INFECT) {
			kills++;
		}
		Data.update(this);
		return move;
	}
	
	private void setLoc (Neighbor front, Neighbor back, Neighbor left, Neighbor right,
			Direction dir) {
		if (front == Neighbor.WALL) {
			if (left == Neighbor.WALL) {
				if (dir == Direction.NORTH) {
					loc = new Point(0, 0);
				} else if (dir == Direction.SOUTH) {
					loc = new Point(60, 40);
				} else if (dir == Direction.EAST) {
					loc = new Point(60, 0);
				} else { // dir == Direction.WEST
					loc = new Point(0, 40);
				}
			} else if (right == Neighbor.WALL) {
				if (dir == Direction.NORTH) {
					loc = new Point(60, 0);
				} else if (dir == Direction.SOUTH) {
					loc = new Point(0, 40);
				} else if (dir == Direction.EAST) {
					loc = new Point(60, 40);
				} else { // dir == Direction.WEST
					loc = new Point(0, 0);
				}
			}
		} else if (back == Neighbor.WALL) {
			if (left == Neighbor.WALL) {
				if (dir == Direction.NORTH) {
					loc = new Point(0, 40);
				} else if (dir == Direction.SOUTH) {
					loc = new Point(60, 0);
				} else if (dir == Direction.EAST) {
					loc = new Point(0, 0);
				} else { // dir == Direction.WEST
					loc = new Point(60, 40);
				}
			} else if (right == Neighbor.WALL) {
				if (dir == Direction.NORTH) {
					loc = new Point(60, 40);
				} else if (dir == Direction.SOUTH) {
					loc = new Point(0, 0);
				} else if (dir == Direction.EAST) {
					loc = new Point(0, 40);
				} else { // dir == Direction.WEST
					loc = new Point(60, 0);
				}
			}
		}
	}
	
	private static Direction rotate (Direction dir) {
		Corner corner = Data.corner();
		if (corner == Corner.NW) {
			return rotate(dir, 90);
		} else if (corner == Corner.NE) {
			return rotate(dir, 180);
		} else if (corner == Corner.SW) {
			return rotate(dir, 270);
		} else {
			return dir;
		}
	}
	
	/**
	 * Rotates the given {@link Critter#Direction Direction} counterclockwise by the given number
	 * of degrees
	 * @param dir The {@link Critter#Direction Direction} to be rotated
	 * @param deg The number of degrees to be rotated by
	 * @return The rotated {@link Critter#Direction Direction}
	 * @throws BadProgramException if {@code degrees % 90 != 0}
	 */
	private static Direction rotate (Direction dir, int deg) {
		if (deg == 0 || deg == 360) {
			return dir;
		} else if (deg == 90) {
			if (dir == Direction.NORTH) {
				return Direction.EAST;
			} else if (dir == Direction.SOUTH) {
				return Direction.WEST;
			} else if (dir == Direction.EAST) {
				return Direction.SOUTH;
			} else {
				return Direction.NORTH;
			}
		} else if (deg == 180) {
			if (dir == Direction.NORTH) {
				return Direction.SOUTH;
			} else if (dir == Direction.SOUTH) {
				return Direction.NORTH;
			} else if (dir == Direction.EAST) {
				return Direction.WEST;
			} else { // dir == Direction.WEST
				return Direction.EAST;
			}
		} else if (deg == 270) {
			if (dir == Direction.NORTH) {
				return Direction.WEST;
			} else if (dir == Direction.SOUTH) {
				return Direction.EAST;
			} else if (dir == Direction.EAST) {
				return Direction.NORTH;
			} else { // dir == Direction.WEST
				return Direction.SOUTH;
			}
		} else {
			throw new BadProgramException();
		}
	}
	
	/**
	 * Returns {@link Action}{@code .}{@link Action.HOP HOP} and translates this {@
	 * @param direction The {@link Direction} in which this {@code Husky} object is pointing
	 * @return {@link Action}{@code .}{@link Action.HOP HOP} by calling {@link #logHop(int, int)}
	 */
	private Action hop (Direction direction) {
		if (direction == Direction.NORTH) {
			logHop(0, -1);
		} else if (direction == Direction.SOUTH) {
			logHop(0,  1);
		} else if (direction == Direction.EAST) {
			logHop(1, 0);
		} else { // direction == Direction.WEST
			logHop(-1, 0);
		}
		
		return Action.HOP;
	}
	
	/**
	 * Translates the points that are in the fields by the given amount.
	 * @param x The distance to be translated horizontally
	 * @param y The distance to be translated vertically
	 */
	private void logHop (int x, int y) {
		distanceFromStart.translate(x, y);
		if (loc != null) {
			prevLoc = loc.clone();
			loc.translate(x, y);
		}
	}
	
	/**
	 * Returns {@link Color}{@code .}{@link Color.YELLOW YELLOW}
	 */
	public Color getColor () {		
		if (kills < 16) {
			return new Color(kills * 16, 255 - kills * 16, 0);
		} else {
			return Color.BLACK;
		}
	}
	
	/**
	 * Compares two {@code Husky} objects by ID.
	 */
	public int compareTo (Husky o) {
		return id - o.id;
	}
	
	/**
	 * Returns the ID of this {@code Husky} object.
	 */
	public int getId () {
		return id;
	}
	
	public boolean isCounter () {
		return isCounter;
	}
	
	public int startCount () {
		return startCount;
	}
	
	/**
	 * Returns the amount of moves that this {@code Husky} object has made.
	 */
	public int moveCount () {
		return moveCount;
	}
	
	/**
	 * Returns the number of kills that this husky object has made.
	 */
	public int kills () {
		return kills;
	}
	
	public Point getLocation () {
		if (loc != null) {
			return loc.clone();
		} else {
			return null;
		}
	}
	
	public Point getPrevLocation () {
		if (prevLoc != null) {
			return prevLoc.clone();
		} else {
			return loc;
		}
	}
	
	/**
	 * Returns "{@code S}"
	 */
	public String toString() {
		if (kills < 10) {
			return Integer.toString(kills);
		} else if (kills == 10) {
			return "A";
		} else if (kills == 11) {
			return "B";
		} else if (kills == 12) {
			return "C";
		} else if (kills == 13) {
			return "D";
		} else if (kills == 14) {
			return "E";
		} else if (kills == 15) {
			return "F";
		} else {
			return "X";
		}
	}
	
	/**
	 * A class to hold static data that is accessible to every instance of the {@link Husky} class
	 */
	private static class Data {
		private static int nextId = 0;
		private static ArrayList<Husky> huskies = new ArrayList<Husky>();
		private static int prevId;
		private static int curId;
		private static int steps = 0;
		private static int phase = 0;
		private static Corner corner;
		private static Husky[][] board = new Husky[40][60];
		
		private Data () {} // prevent this class from being instantiated
		
		/**
		 * Returns the value to be used for the next {@link Husky} ID, then increments it
		 */
		public static int nextId () {
			return nextId++;
		}
		
		public static void add (Husky h) {
			huskies.add(h);
		}
		
		/**
		 * Searches the list of {@link Husky Huskies} for the {@link Husky} with the given ID
		 * @param id The ID to be searched for
		 * @return The {@link Husky} object if it is found, {@code null} otherwise
		 */
		public static Husky get (int id) {
			int index = Collections.binarySearch(huskies, new Husky(id));
			if (index < 0) {
				return null;
			} else {
				return huskies.get(index);
			}
		}
		
		public static void update (Husky husky) {			
			prevId = curId;
			curId = husky.getId();
			Point loc = husky.getLocation();
			Point prevLoc = husky.getPrevLocation();
			if (loc != null) {
				if (prevLoc != null) {
					Husky h = board[prevLoc.getY()][prevLoc.getX()];
					if (h == husky) {
						h = null;
					}
				}
				board[loc.getY()][loc.getX()] = husky;
			}
			
			if (prevId > curId) {
				Iterator<Husky> itr = huskies.iterator();
				while (itr.hasNext()) {
					Husky h = itr.next();
					if (h.getId() != curId && h.startCount() + h.moveCount() < steps - 1) {
						itr.remove();
					}
				}
				
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (board[i][j] != null) {
							if (board[i][j].startCount() + board[i][j].moveCount() < steps - 1) {
								board[i][j] = null;
							}
						}
					}
				}
			}
		}
		
		public static int prevId () {
			return prevId;
		}
		
		public static int curId () {
			return curId;
		}
		
		public static int getSteps () {
			return steps;
		}
		
		public static int getPhase () {
			return phase;
		}
		
		public static void incrementPhase () {
			phase++;
		}
		
		/**
		 * Randomly assigns the corner that all of the {@link Husky} objects will migrate to
		 */
		public static void setCorner () {
			if (corner != null) {
				throw new BadProgramException();
			}
			
			int theCorner = r.nextInt(4);
			if (theCorner == 0) {
				corner = Corner.NW;
			} else if (theCorner == 1) {
				corner = Corner.NE;
			} else if (theCorner == 2) {
				corner = Corner.SW;
			} else { // corner == 3
				corner = Corner.SE;
			}
		}
		
		public static Corner corner () {
			return corner;
		}
		
		/**
		 * Identifies the {@code Husky} that is at the specified location on the board
		 * @param x The x-coordinate to be examined
		 * @param y The y-coordinate to be examined
		 * @return The {@code Husky} at the specified coordinates, or {@code null} if no husky
		 * 			is present at the coordinates.
		 */
		public static Husky get (int x, int y) {
			return board[x][y];
		}
		
		public static void set (Husky h, int x, int y) {
			board[x][y] = h;
		}
	}
	
	/**
	 * A class to represent points on the board
	 */
	private static class Point {
		private int x;
		private int y;
		private boolean isCoord;
		
		/**
		 * Constructs a {@code Point} with the coordinates {@code(0, 0)}
		 */
		public Point () {
			this(0, 0, true);
		}
		
		public Point (boolean isCoord) {
			this(0, 0, isCoord);
		}
		
		public Point (int x, int y) {
			this(x, y, true);
		}
		
		/**
		 * Constructs a {@code Point} with the given x- and y-coordinates
		 * @param x The x-coordinate. Must be between 0 and 59 inclusive.
		 * @param y The y-coordinate. Must be between 0 and 39 inclusive.
		 * @throws BadProgramException if either coordinate is out of range
		 */
		public Point (int x, int y, boolean isCoord) {
			if ((x < 0 || y < 0 || x >= 60 || y >= 40) && isCoord) {
				throw new BadProgramException();
			} else {
				this.x = x;
				this.y = y;
				this.isCoord = isCoord;
			}
		}
		
		/**
		 * Returns the x-coordinate
		 */
		public int getX () {
			return x;
		}
		
		/**
		 * Returns the y-coordinate
		 */
		public int getY () {
			return y;
		}
		
		/**
		 * Translate the {@code Point} by the given amount
		 * @param x The amount to be added to the x-coordinate
		 * @param y The amount to be added to the y-coordinate
		 * @throws BadProgramException if the x- or y- coordinate is translated out of range, as
		 * 			specified in {@link #Point(int, int)}
		 */
		public void translate (int x, int y) {
			this.x += x;
			this.y += y;
			
			if ((x < 0 || y < 0 || x >= 60 || y >= 60) && isCoord) {
				throw new BadProgramException();
			}
		}
		
		/**
		 * Returns {@code true} if the given {@link Object} is a {@code Point} and the x- and
		 * y-coordinates are the same as this {@code Point Point's} x- and y-coordinates,
		 * {@code false} otherwise
		 * @param o The {@link Object} to be compared for equality
		 */
		public boolean equals (Object o) {
			if (o instanceof Point) {
				Point p = (Point) o;
				return p.x == x && p.y == y && p.isCoord == isCoord;
			} else {
				return false;
			}
		}
		
		public Point clone () {
			return new Point(x, y, isCoord);
		}
	}
	
	/**
	 * Thrown to indicate that a condition that is assumed to be true during the execution
	 * of the program is false, or vice versa. Similar to an {@code assert} statement.
	 */
	public static class BadProgramException extends RuntimeException {
		private static final long serialVersionUID = 4037145900676511778L; // to shut up the compiler
		
		/**
		 * Constructs a {@code BadProgramException} with no message
		 */
		public BadProgramException () {
			super();
		}
		
		/**
		 * Constructs a {@code BadProgramException} with the specified message
		 * @param message
		 */
		public BadProgramException (String message) {
			super(message);
		}
	}
	
	/**
	 * Represents the four corners that exist on the board.
	 */
	private enum Corner {
		NW, NE, SW, SE
	}
}