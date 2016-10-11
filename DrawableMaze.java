/**
 * DrawableMaze.java
 * Describes the visual effects of the Maze
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

public class DrawableMaze extends Maze implements Drawable{
    public void draw(){
    }
    
    
    public DrawableMaze(DrawableSquare[][] maze, int rows, int cols){
        super(maze, rows, cols);
    }
}