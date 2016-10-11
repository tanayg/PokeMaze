/**
 * DrawableExplorer.java
 * Describes the visual effects of the Explorer class
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

public class DrawableExplorer extends Explorer implements Drawable{
    public void draw(){
    }
    
    
    public DrawableExplorer(Square location, Maze maze, String name){
        super(location, maze, name);
    }
}