/**
 * DelimitedTextIO
 * 
 * @author Tanay Gottigundala 
 * @version CPE102-11
 */

import java.util.Scanner;

public interface DelimitedTextIO{
    String toText(char delimiter);
    void toObject(Scanner input);
}
