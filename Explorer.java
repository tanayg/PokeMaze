/**
 * Explorer.java
 * Describes the behavior of the main character.
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Explorer extends Occupant{
    private String name;
    private Maze m;
    
    
    //4
    public Explorer(Maze maze){
        m = maze;
    }
    
    public Explorer(Square location, Maze maze, String name){
        super(location);
        this.name = name;
        m = maze;
        m.lookAround(location);
    }
    
    
    public String name(){
        return name;
    }
    
    public void move(int key){
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_KP_LEFT){
            if (location().col() > 0 && !location().wall(Square.LEFT)){
                moveTo(m.getSquare(location().row(), location().col() - 1));
            }
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP){
            if (location().row() > 0 && !location().wall(Square.UP)){
                moveTo(m.getSquare(location().row() - 1, location().col()));
            }
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT){
            if (location().col() < (m.cols() - 1) && !location().wall(Square.RIGHT)){
                moveTo(m.getSquare(location().row(), location().col() + 1));
            }
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_KP_DOWN){
            if (location().row() < (m.rows() - 1) && !location().wall(Square.DOWN)){
                moveTo(m.getSquare(location().row() + 1, location().col()));
            }
        }
    }
    
    public void moveTo(Square s){
        super.moveTo(s);
        s.enter();
        m.lookAround(s);
    }
    
    
    public String toText(char d){
        return super.toText(d) + d + name;
    }
    
    public void toObject(Scanner in){
        moveTo(m.getSquare(in.nextInt(),in.nextInt()));
        name = in.next();
    }
}
