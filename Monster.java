/**
 * Monster.java
 * Describes the behavior of all monsters in the maze.
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

public class Monster extends RandomOccupant{
    
    public Monster(Maze maze){
        super(maze);
    }
    
    public Monster(Maze maze, long seed){
        super(maze, seed);
    }
    
    public Monster(Maze maze, Square location){
        super(maze, location);
    }
}
