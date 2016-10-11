/**
 * Treasure.java
 * Describes the behavior of all treasures of the maze.
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

import java.util.Scanner;

public class Treasure extends RandomOccupant{
    private boolean found = false;
    
    
    public Treasure(Maze maze){
        super(maze);
        location().setTreasure(this);
    }
    
    public Treasure(Maze maze, long seed){
        super(maze, seed);
        location().setTreasure(this);
    }
    
    public Treasure(Maze maze, Square location){
        super(maze, location);
        location().setTreasure(this);
    }
    
    
    public boolean found(){
        return found;
    }
    
    public void setFound(){
        found = true;
    }
    
    public void move(){
    }
    
    //4
    public void moveTo(Square newLoc){
        if (location() != null){
            super.location().setTreasure(null);
        }
        super.moveTo(newLoc);
    }
    
    
    public String toText(char d){
        return super.toText(d) + d + found;
    }
    
    public void toObject(Scanner s){
        super.toObject(s);
        found = s.nextBoolean();
    }
}
