// Imports
import java.util.ArrayList;
import java.io.*;

public class TestRetrieve {
  public static void main(String[] args) {
    File file = new File("test.txt");
    File file2 = new File("test2.txt");
    
    try(FileEnterRetrieve fer = new FileEnterRetrieve(file, true, true, false, ",")) {
      System.out.println(retrieveTest(fer));
    }
    catch(IOException e) { }
    
    try(FileEnterRetrieve fer = new FileEnterRetrieve(file2, true, true, false, ",")) {
      System.out.println(retrieveTest2(fer));
    }
    catch(IOException e) { }
    
    file.delete();
    file2.delete();
  }
  
  public static boolean retrieveTest(FileEnterRetrieve fer) {
    ArrayList<?> read = fer.retrieveData();
    boolean worked = false;
    
    if(read.size() != 0) {
      worked = true;
      for(Object data: read) {
        System.out.println((String)data);
      }
    }
    
    return worked;
  }
  
  public static boolean retrieveTest2(FileEnterRetrieve fer) {
    ArrayList<?> read = fer.retrieveData(6, 0, 1);
    boolean worked = false;
    
    if(read.size() != 0) {
      for(Object list: read) {
        for(Object data: (ArrayList)list) {
          System.out.println(data.toString());
          if(data instanceof Boolean) {
            worked = (Boolean)data;
          }
        }
      }
    }
    
    return worked;
  }
}