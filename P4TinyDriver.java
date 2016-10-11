
public class P4TinyDriver
{
   private static Square[][] fixedSquares = new Square[5][5];
   
   public static void main(String[] args) throws Exception
   {
       // Set up maze for unit tests
       fixedSquares[0][0] = new Square(true, false, false, true, 0, 0);
       fixedSquares[0][1] = new Square(true, false, true, false, 0, 1);
       fixedSquares[0][2] = new Square(true, false, true, false, 0, 2);
       fixedSquares[0][3] = new Square(true, false, false, false, 0, 3);
       fixedSquares[0][4] = new Square(true, true, false, false, 0, 4);
       fixedSquares[1][0] = new Square(false, false, true, true, 1, 0);
       fixedSquares[1][1] = new Square(true, false, true, false, 1, 1);
       fixedSquares[1][2] = new Square(true, true, false, false, 1, 2);
       fixedSquares[1][3] = new Square(false, true, false, true, 1, 3);
       fixedSquares[1][4] = new Square(false, true, false, true, 1, 4);
       fixedSquares[2][0] = new Square(true, false, false, true, 2, 0);
       fixedSquares[2][1] = new Square(true, false, true, false, 2, 1);
       fixedSquares[2][2] = new Square(false, true, false, false, 2, 2);
       fixedSquares[2][3] = new Square(false, true, false, true, 2, 3);
       fixedSquares[2][4] = new Square(false, true, false, true, 2, 4);
       fixedSquares[3][0] = new Square(false, true, false, true, 3, 0);
       fixedSquares[3][1] = new Square(true, false, false, true, 3, 1);
       fixedSquares[3][2] = new Square(false, true, false, false, 3, 2);
       fixedSquares[3][3] = new Square(false, true, true, true, 3, 3);
       fixedSquares[3][4] = new Square(false, true, false, true, 3, 4);
       fixedSquares[4][0] = new Square(false, true, true, true, 4, 0);
       fixedSquares[4][1] = new Square(false, true, true, true, 4, 1);
       fixedSquares[4][2] = new Square(false, false, true, true, 4, 2);
       fixedSquares[4][3] = new Square(true, false, true, false, 4, 3);
       fixedSquares[4][4] = new Square(false, true, true, false, 4, 4); 
       
       testMaze();
    }
    private static void testMaze() throws Exception
    {
        Maze maze, maze2;
        Explorer ex;
        Treasure t1, t2;
        Monster m1, m2;
        
        maze = new Maze(fixedSquares, 5, 5);
        
        ex = new Explorer(fixedSquares[0][0], maze, "Scary Name");
        maze.setExplorer(ex);
        
        t1 = new Treasure(maze, fixedSquares[4][4]);      
        t2 = new Treasure(maze, fixedSquares[2][2]);
        m1 = new Monster(maze, fixedSquares[4][4]);
        m2 = new Monster(maze, fixedSquares[3][3]);
        t1.setFound();
        
        maze.addRandomOccupant(t1);
        maze.addRandomOccupant(t2);
        maze.addRandomOccupant(m1);
        maze.addRandomOccupant(m2);
        
        maze.writeMazeToFile("test.txt");
        
        
        try {   
            maze.writeMazeToFile("test.txt");  // should be identical to mazeGood.txt
            
            // comment the stuff after this out to test initial "write" code
            maze2 = new Maze();
           maze2.readMazeFromFile("test.txt"); // or read from mazeGood.txt

           maze2.writeMazeToFile("test2.txt"); // test2.txt should be identical to test.txt
            
            // try also reading from a known bad file to see if exceptions are thrown.
            
            System.out.println("No Exceptions.");
        }
        catch (MazeReadException e)
        {
            System.out.println("**** " + e.getMessage());
            System.out.println("**** " + e.getLine());
            System.out.println("**** Line: " + e.getLineNum());
        }
        
        
    }
}