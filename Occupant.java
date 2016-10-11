/**
 * Occupant.java
 * Describes the behavior of all occupants of the maze.
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */
public abstract class Occupant implements DelimitedTextIO{
    private Square location;
    
    
    public Occupant(){}
    
    public Occupant(Square start){
        location = start;
    }
    
    
    public Square location(){
        return location;
    }
    
    public void moveTo(Square newLoc){
        location = newLoc;
    }
    
    
    public String toText(char d){
        return getClass().getName() + d + location().row() + d + location().col();
    }
}
