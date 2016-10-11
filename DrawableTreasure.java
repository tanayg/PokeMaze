/**
 * DrawableTreasure.java
 * Describes the visual effects of the Treasure class
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

public class DrawableTreasure extends Treasure implements Drawable{
    public void draw(){
    }
    
    
    public DrawableTreasure(Maze maze){
        super(maze);
    }
    
    public DrawableTreasure(Maze maze, long seed){
        super(maze, seed);
    }
    
    public DrawableTreasure(Maze maze, Square location){
        super(maze, location);
    }
}