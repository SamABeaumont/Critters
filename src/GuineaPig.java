import java.awt.*;
import java.util.*;

/**
 * A subclass of {@link Critter} to be used for testing (hence the name {@code GuineaPig})
 * @author Sam Beaumont
 */
public class GuineaPig extends Critter {
	private static int nextId;
	private static ArrayList<GuineaPig> guineaPigs = new ArrayList<GuineaPig>();
	
	private int id;
	
	public GuineaPig () {
		id = nextId;
		guineaPigs.add(this);
		nextId++;
	}
	
	public Action getMove (CritterInfo info) {
		for (GuineaPig g : guineaPigs) {
			System.out.println(g.id);
		}
		
		return Action.INFECT;
	}
	
	public String foo () {
		return "bar";
	}
	
	/**
	 * Returns {@link Color}{@code .}{@link Color#BLACK BLACK}
	 */
	public Color getColor () {
		return Color.BLACK;
	}
	
	/**
	 * Returns "{@code G}"
	 */
	public String toString () {
		return "G";
	}
}
