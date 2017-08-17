// Imports
import java.io.*;

public class TestEnter {
  public static void main(String[] args) {
    File file = new File("test.txt");
    File file2 = new File("test2.txt");
    System.out.println(file.delete());
    System.out.println(file2.delete());
    
    try {
      System.out.println(file.createNewFile());
    }
    catch(IOException e) { }
    
    try(FileEnterRetrieve fer = new FileEnterRetrieve(file, true, true, false, ",")) {
      System.out.println(fer.enterData(new String[]{"hello", "world"}));
    }
    catch(IOException e) { }
    
    try(FileEnterRetrieve fer = new FileEnterRetrieve(file2, true, true, false, ",")) {
      System.out.println(fer.enterData(new Object[]{1, "fish", true}));
    }
    catch(IOException e) { }
  }
}