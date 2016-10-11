/**
 * Squares.java
 * Describes the behavior of all squares in the maze.
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

import java.util.Scanner;

public class Square implements DelimitedTextIO{
    public static final int SQUARE_SIZE = 50;
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    
    
    private boolean[] walls = new boolean[4];
    private boolean seen = false;
    private boolean inView = false;
    private int row;
    private int col;
    private Treasure t;
    
    
    //4
    public Square(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public Square(boolean up, boolean right, boolean down, boolean left, int row, int col){
        walls[UP] = up;
        walls[RIGHT] = right;
        walls[DOWN] = down;
        walls[LEFT] = left;
        this.row = row;
        this.col = col;
    }
    
    
    public boolean wall(int direction){
        return walls[direction];
    }
    
    public boolean seen(){
        return seen;
    }
    
    public boolean inView(){
        return inView;
    }
    
    public int row(){
        return row;
    }
    
    public int col(){
        return col;
    }
    
    public Treasure treasure(){
        return t;
    }
    
    public int x(){
        return col * SQUARE_SIZE;
    }
    
    public int y(){
        return row * SQUARE_SIZE;
    }
    
    public void setInView(boolean inView){
        this.inView = inView;
        if (inView){
            seen = true;
        }
    }
    
    public void setTreasure(Treasure t){
        this.t = t;
    }
    
    public void enter(){
        if (t != null){
            t.setFound();
        }
    }
    
    public String toText(char delimiter){
        return getClass().getName() + delimiter + row + delimiter + col + delimiter + wall(UP) + delimiter + wall(RIGHT) + delimiter + wall(DOWN) + delimiter + wall(LEFT) + delimiter + seen() + delimiter + inView();
    }
    
    public void toObject(Scanner in){
        String line = in.nextLine();
        Scanner s = new Scanner(line);
        s.useDelimiter(",");
        walls[UP] = s.nextBoolean();
        walls[RIGHT] = s.nextBoolean();
        walls[DOWN] = s.nextBoolean();
        walls[LEFT] = s.nextBoolean();
        seen = s.nextBoolean();
        inView = s.nextBoolean();
    }
}
