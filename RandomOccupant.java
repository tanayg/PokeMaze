/**
 * RandomOccupant.java
 * Describes the behavior of all random occupants of the maze.
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

import java.util.Random;
import java.util.Scanner;

public abstract class RandomOccupant extends Occupant{
    private Random random;
    private Maze m;
    
    
    public RandomOccupant(Maze maze){
        m = maze;
        random = new Random();
        moveTo(m.getSquare(random.nextInt(m.rows()), random.nextInt(m.cols())));
    }
    
    public RandomOccupant(Maze maze, long seed){
        m = maze;
        random = new Random(seed);
        moveTo(m.getSquare(random.nextInt(m.rows()), random.nextInt(m.cols())));
    }
    
    public RandomOccupant(Maze maze, Square location){
        m = maze;
        random = new Random();
        moveTo(location);
    }
    
    
    public void move(){
        int rando = random.nextInt(4);
        while (location().wall(rando)){
            rando = random.nextInt(4);
        }
        if (rando == Square.UP){
            moveTo(m.getSquare(location().row() - 1, location().col()));
        }
        if (rando == Square.DOWN){
            moveTo(m.getSquare(location().row() + 1, location().col()));
        }
        if (rando == Square.LEFT){
            moveTo(m.getSquare(location().row(), location().col() - 1));
        }
        if (rando == Square.RIGHT){
            moveTo(m.getSquare(location().row(), location().col() + 1));
        }
    }
    
    
    public void toObject(Scanner in){
        moveTo(m.getSquare(in.nextInt(), in.nextInt()));
    }
}
