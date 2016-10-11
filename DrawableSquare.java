/**
 * DrawableSquare.java
 * Describes the visual effects of the Square class
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */
public class DrawableSquare extends Square implements Drawable{
    public void draw(){
    }
    
    
    public DrawableSquare(boolean up, boolean right, boolean down, boolean left, int row, int col){
        super(up, right, down, left, row, col);
    }
}
