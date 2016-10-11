/**
 * DrawableMonster.java
 * Describes the visual effects of the Monster class
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

public class DrawableMonster extends Monster implements Drawable{
    public void draw(){
    }
    
    
    public DrawableMonster(Maze maze){
        super(maze);
    }
    
    public DrawableMonster(Maze maze, long seed){
        super(maze, seed);
    }
    
    public DrawableMonster(Maze maze, Square location){
        super(maze, location);
    }
}