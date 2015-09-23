

// Hale AP CS Critters Project
// Modified from UW CSE 142 Homework 8 (Critters)
// Authors: Stuart Reges and Marty Stepp; modified by Kevin Wallace
//
// This is the superclass of all of the Critter classes.  Your class should
// extend this class.  The class provides several kinds of constants:
//
//    type Neighbor  : WALL, EMPTY, SAME, OTHER
//    type Action    : HOP, LEFT, RIGHT, INFECT
//    type Direction : NORTH, SOUTH, EAST, WEST
//
// Override the following methods to change the behavior of your Critter:
//
//     public Action getMove(CritterInfo info)
//     public Color getColor()
//     public String toString()
//
// The CritterInfo object passed to the getMove method has the following
// available methods:
//
//     public Neighbor getFront();         returns neighbor in front of you
//     public Neighbor getBack();          returns neighbor in back of you
//     public Neighbor getLeft();          returns neighbor to your left
//     public Neighbor getRight();         returns neighbor to your right
//     public Direction getDirection();    returns direction you are facing
//     public int getInfectCount();        returns your current infect count
//                                         (# of Critters you have infected)

import java.awt.*;

public abstract class Critter {
    public static enum Neighbor {
        WALL, EMPTY, SAME, OTHER
    };

    public static enum Action {
        HOP, LEFT, RIGHT, INFECT
    };

    public static enum Direction {
        NORTH, SOUTH, EAST, WEST
    };

    // The following three methods must be overridden.
    public abstract Action getMove(CritterInfo info);
    public abstract Color getColor();
    public abstract String toString();

    // This prevents critters from trying to redefine the definition of
    // object equality, which is important for the simulator to work properly.
    public final boolean equals(Object other) {
        return this == other;
    }
}