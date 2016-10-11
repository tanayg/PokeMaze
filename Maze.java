/**
 * Maze.java
 * Class that contains all the logic to model a Maze with Treasures, Monsters, and an Explorer.
 * @author Tanay Gottigundala CPE102-11
 * @version 4/20/2016
 */

import java.util.*;
import java.io.*;

public class Maze
{
    // named constants
    public static final int ACTIVE = 0;
    public static final int EXPLORER_WIN = 1;
    public static final int MONSTER_WIN = 2;

    // instance variables
    private Square[][] squares;
    private ArrayList<RandomOccupant> randOccupants;
    private Explorer explorer;
    private int rows;
    private int cols;

    /**
     * Constructor for objects of class Maze
     */

    //4
    public Maze(){
        randOccupants = new ArrayList<RandomOccupant>();
    }

    public Maze(Square[][] squares, int rows, int cols)
    {
        this.squares = squares;
        this.rows = rows;
        this.cols = cols;

        randOccupants = new ArrayList<RandomOccupant>();
    }

    // QUERIES
    public Square getSquare(int row, int col) { return squares[row][col]; }

    public int rows() {return rows;}

    public int cols() {return cols;}

    public String explorerName() {return explorer.name();}

    public Explorer getExplorer() {return explorer;}

    public RandomOccupant getRandomOccupant(int index) {
        return randOccupants.get(index);
    }

    public int getNumRandOccupants() {
        return randOccupants.size();
    }

    // COMMANDS
    public void addRandomOccupant(RandomOccupant ro) {
        randOccupants.add(ro);
    }

    public void setExplorer(Explorer e) {explorer = e;}

    public void explorerMove(int key)
    {
        explorer.move(key);
    }

    public void randMove()
    {
        for (RandomOccupant r : randOccupants){
            r.move();
        }
    }

    /**
     * Returns the status of the game.
     *
     * If all treasures have been found, return EXPLORER_WIN.
     * If not, check each maze occupant, if it is a Monster and
     *    it is in the same location as the Explorer, return
     *    MONSTER_WIN.  Note that you can use == to check locations, do you know why?
     * Otherwise, return ACTIVE.
     */
    public int gameStatus()
    {
        int status = ACTIVE;

        if (foundAllTreasures()){
            status = EXPLORER_WIN;
        }
        else{
            for(RandomOccupant r : randOccupants){
                if (r instanceof Monster && ((Monster)r).location() == explorer.location()){
                    status = MONSTER_WIN;
                }
            }
        }

        return status;
    }

    private boolean foundAllTreasures()
    {
        boolean foundAll = true;

        for (RandomOccupant r : randOccupants){
            if (r instanceof Treasure && !((Treasure)r).found()){
                foundAll = false;
            }
        }

        return foundAll;
    }

    public void lookAround(Square s)
    {
        int row = s.row();
        int col = s.col();

        // Clear what was previously in view
        resetInView();

        // Set the current square so that we are viewing it (obviously)
        s.setInView(true);

        if (row - 1 >= 0 && !squares[row - 1][col].wall(Square.DOWN)){
            squares[row - 1][col].setInView(true);
        }

        if (col + 1 < cols && !squares[row][col + 1].wall(Square.LEFT)){
            squares[row][col + 1].setInView(true);
        }

        if (row + 1 < rows && !squares[row + 1][col].wall(Square.UP)){
            squares[row + 1][col].setInView(true);
        }

        if (col - 1 >= 0 && !squares[row][col - 1].wall(Square.RIGHT)){
            squares[row][col - 1].setInView(true);
        }

        if (row - 1 >= 0 && col - 1 >= 0){
            if (!(squares[row][col].wall(Square.UP) && squares[row][col].wall(Square.LEFT))){
                if (!(squares[row - 1][col - 1].wall(Square.DOWN) && squares[row - 1][col - 1].wall(Square.RIGHT))){
                    if (!(squares[row][col - 1].wall(Square.UP) && squares[row][col].wall(Square.UP))){
                        if (!(squares[row][col].wall(Square.LEFT) && squares[row - 1][col].wall(Square.LEFT))){
                            squares[row - 1][col - 1].setInView(true);
                        }
                    }
                }
            }
        }

        if (row - 1 >= 0 && col + 1 < cols){
            if (!(squares[row][col].wall(Square.UP) && squares[row][col].wall(Square.RIGHT))){
                if (!(squares[row - 1][col + 1].wall(Square.DOWN) && squares[row - 1][col + 1].wall(Square.LEFT))){
                    if (!(squares[row - 1][col].wall(Square.RIGHT) && squares[row][col].wall(Square.RIGHT))){
                        if (!(squares[row][col].wall(Square.UP) && squares[row][col + 1].wall(Square.UP))){
                            squares[row - 1][col + 1].setInView(true);
                        }
                    }
                }
            } 
        }

        if (row + 1 < rows && col + 1 < cols){
            if (!(squares[row][col].wall(Square.DOWN) && squares[row][col].wall(Square.RIGHT))){
                if (!(squares[row + 1][col + 1].wall(Square.UP) && squares[row + 1][col + 1].wall(Square.LEFT))){
                    if (!(squares[row][col].wall(Square.RIGHT) && squares[row + 1][col].wall(Square.RIGHT))){
                        if (!(squares[row][col].wall(Square.DOWN) && squares[row][col + 1].wall(Square.DOWN))){
                            squares[row + 1][col + 1].setInView(true);
                        }
                    }
                }
            }
        }

        if (row + 1 < rows && col - 1 >= 0){
            if (!(squares[row][col].wall(Square.DOWN) && squares[row][col].wall(Square.LEFT))){
                if (!(squares[row + 1][col - 1].wall(Square.UP) && squares[row + 1][col - 1].wall(Square.RIGHT))){
                    if (!(squares[row][col].wall(Square.DOWN) && squares[row][col - 1].wall(Square.DOWN))){
                        if (!(squares[row][col].wall(Square.LEFT) && squares[row + 1][col].wall(Square.LEFT))){
                            squares[row + 1][col - 1].setInView(true);
                        }
                    }
                }
            }
        }
    }

    private void resetInView()
    {
        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<cols; j++) {
                squares[i][j].setInView(false);
            }
        }
    }

    public void writeMazeToFile(String filename) throws IOException{
        PrintWriter p  = new PrintWriter(filename, "UTF-8");
        //rows, cols
        p.println(rows + "," + cols);
        //Squares
        for (int i = 0; i < rows; i ++){
            for(int j = 0; j < cols; j++){
                p.println(squares[i][j].toText(','));
            }
        }
        //Explorer
        p.println(explorer.toText(','));
        //Treasures
        for (int i = 0; i < randOccupants.size(); i++){
            RandomOccupant r = randOccupants.get(i);
            if (r instanceof Treasure){
                p.println(((Treasure)r).toText(','));
            }
            else if (r instanceof Monster){
                p.println(((Monster)r).toText(','));
            }
        }
        p.flush();
        p.close();
    }

    public void readMazeFromFile(String fileName) throws IOException, FileNotFoundException, MazeReadException{
        int line = 1;
        Scanner file = null;
        try{
            file = new Scanner(new FileReader(fileName));
        }
        catch (FileNotFoundException e){
        }

        while (file.hasNextLine()){
            String string = file.nextLine();
            Scanner ls = new Scanner(string);
            ls.useDelimiter(",");

            if (line == 1){
                if (ls.hasNextInt()){
                    rows = ls.nextInt();
                }
                else{
                    throw new MazeReadException("Rows and columns not specified.", string, line);
                }
                if (ls.hasNextInt()){
                    cols = ls.nextInt();
                }
                else{
                    throw new MazeReadException("Rows and columns not specified.", string, line);
                }
                squares = new Square[rows][cols];
                line ++;
                string = file.nextLine();
                ls = new Scanner(string);
                ls.useDelimiter(",");
            }

            String name;
            if (ls.hasNext()){
                name = ls.next();
            }
            else{
                throw new MazeReadException("Line format or other error.", string, line);
            }

            int row, col;

            if (name.equals("Square")){
                if (ls.hasNextInt()){
                    row = ls.nextInt();
                }
                else{
                    throw new MazeReadException("Rows and columns not specified.", string, line);
                }
                if (ls.hasNextInt()){
                    col = ls.nextInt();
                }
                else{
                    throw new MazeReadException("Rows and columns not specified.", string, line);
                }
                if (squares[row][col] == null){
                    squares[row][col] = new Square(row, col);
                    try{
                        squares[row][col].toObject(ls);
                    }
                    catch (Exception exp){
                        throw new MazeReadException("Line format or other error.", string, line);
                    }
                }
                else{
                    throw new MazeReadException("Duplicate square.", string, line);
                }
            }
            else if (name.equals("Explorer")){
                explorer = new Explorer(this);
                try{
                    explorer.toObject(ls);
                }
                catch(Exception e){
                    throw new MazeReadException("Line format or other error.", string, line);
                }
            }
            else if (name.equals("Treasure")){
                randOccupants.add(new Treasure(this));
                try{
                    randOccupants.get(randOccupants.size() - 1).toObject(ls);
                }
                catch(Exception e){
                    throw new MazeReadException("Line format or other error.", string, line);
                }
            }
            else if (name.equals("Monster")){
                randOccupants.add(new Monster(this));
                try{
                    randOccupants.get(randOccupants.size() - 1).toObject(ls);
                }
                catch(Exception e){
                    throw new MazeReadException("Line format or other error.", string, line);
                }
            }
            else{
                throw new MazeReadException("Unknown type.", string, line);
            }
            line++;
        }
    }
}