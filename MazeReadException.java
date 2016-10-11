/**
 * Maze Read Exception
 * 
 * @author Tanay Gottigundala 
 * @version CPE 102-11
 */
public class MazeReadException extends Exception{
    private String line;
    private int lineNumber;

    public MazeReadException(String message, String line, int lineNumber){
        super(message);
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public String getLine(){
        return line;
    }
    
    public int getLineNum(){
        return lineNumber;
    }
}
