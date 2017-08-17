// Imports
import java.io.File;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

// Exceptions
import java.io.IOException;
import java.io.FileNotFoundException;

/* This class will allow a user to enter a file to read or write to.
 * When reading, the user will be able to seperate each of the data
 * by any delimiter that they choose. */
public class FileEnterRetrieve implements AutoCloseable {
  File rfile;
  File wfile;
  //Scanner read;
  //PrintWriter write;
  String delimiter;
  
  public FileEnterRetrieve() {
    
  }
  
  /* This allows the user to set if they want to read, write, or do both
   * to a file. It also gives the option that if an exception is thrown,
   * whether or not the user needs to see it */
  public FileEnterRetrieve(File file, boolean r, boolean w, boolean thrown) throws IOException{
    try {
      if(r) {
        //read = new Scanner(file);
        rfile = file;
        //delimiter = read.delimiter().pattern();
      }
      if(w) {
        wfile = file;
        //write = new PrintWriter(new BufferedWriter(new FileWriter(file)));
      }
    }
    catch(Exception e) {
      // Gives the user the option to see why the file won't open
      if(thrown)
        throw e;
    }
    
    delimiter = " ";
  }
  
  /* Does the same as the previous constructor, but also allows the
   * user to set the delimiter as well */
  public FileEnterRetrieve(File file, boolean r, boolean w, boolean thrown, String delimit) throws IOException {
    this(file, r, w, thrown);
    //read.useDelimiter(delimit);
    delimiter = delimit;
  }
  
  /* This will write to a file any data that's needed. If the delimiter
   * is a newline, it will use the PrintWriter function to place the 
   * appropriate newline when it is computer specific. */
  public boolean enterData(Object[] data) {
    boolean entered = false;

    try(PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(wfile)))) {
      if(delimiter == "\n") {
        for(Object object: data) {
          write.println(object);
        }
        entered = true;
      }
      else {
        for(Object object: data) {
          write.print(object);
          write.print(delimiter);
        }
        entered = true;
      }
    }
    catch(Exception e) { }
    
    return entered;
  }

      
  
  /* This takes a list of integers to show which primative
   * should the next token be treated as. It returns an Arraylist of
   * Arraylists that each second dimension arraylist corresponds to a
   * specific primitive */
  public ArrayList<?> retrieveData(int... order) {
    int index = 0;
    Object object;
    
    /* Makes sure the scanner has a file to read */
    try(Scanner read = new Scanner(rfile)) {
      /* Set the delimiter */
      if(!delimiter.equals(read.delimiter().toString())) 
        read.useDelimiter(delimiter);
      
      /* Makes an ArrayList according to what the user needs */
      if(order.length == 0) {
        ArrayList<Object> holder = new ArrayList<Object>();
        
        while(read.hasNext()) {
          object = read.next();
          holder.add(object);
        }
        
        return holder;
      }
    
      else {
        ArrayList<ArrayList> holder = new ArrayList<ArrayList>();
      
        for(int i = 0; i < order.length; i++) {
          holder.add((ArrayList)createArray(order[i]));
        }
      
        while(read.hasNext()) {
          populateArray((ArrayList)holder.get(index), order[index], read);
          index++;
          if(index >= order.length) {
            index = 0;
          }
        }
        
        return holder;
      }
    }
    catch(IOException e) { 
      System.out.println("This should not display");
    }
    
    return null;
  }
  public String fileRetrievalUsage() {
    return "fileRetrieval(int... order)\n0 = object\n1 = boolean\n2 = byte\n 3 = char\n" +
           "4 = double\n5 = float\n6 = int\n7 = long\n8 = small";
  }
  private ArrayList<?> createArray(int order) {
    ArrayList returned;
    
    switch (order) {
      case 0: {
        returned = new ArrayList<Object>();
        break;
      }
      case 1: {
        returned = new ArrayList<Boolean>();
        break;
      }
      case 2: {
        returned = new ArrayList<Byte>();
        break;
      }
      case 3: {
        returned = new ArrayList<Character>();
        break;
      }
      case 4: {
        returned = new ArrayList<Double>();
        break;
      }
      case 5: {
        returned = new ArrayList<Float>();
        break;
      }
      case 6: {
        returned = new ArrayList<Integer>();
        break;
      }
      case 7: {
        returned = new ArrayList<Long>();
        break;
      }
      case 8: {
        returned = new ArrayList<Short>();
        break;
      }
      default: {
        returned = new ArrayList<Object>();
        break;
      }
    }
    return returned;
  }
  private void populateArray(ArrayList array, int order, Scanner read) {
    switch (order) {
      case 0: {
        array.add(read.next());
        break;
      }
      case 1: {
        array.add(read.nextBoolean());
        break;
      }
      case 2: {
        array.add(read.nextByte());
        break;
      }
      case 3: {
        array.add((char)read.nextByte());
        break;
      }
      case 4: {
        array.add(read.nextDouble());
        break;
      }
      case 5: {
        array.add(read.nextFloat());
        break;
      }
      case 6: {
        array.add(read.nextInt());
        break;
      }
      case 7: {
        array.add(read.nextLong());
        break;
      }
      case 8: {
        array.add(read.nextShort());
        break;
      }
    }
  }
  
  /* Allow the user to use the writer and reader on different files */
  public boolean setReadFile(File file) {
    try {
      rfile = file;
    }
    catch(Exception e) {
      return false;
    }
    return true;
  }
  public boolean setWriteFile(File file) {
    try {
      wfile = file;
    }
    catch(Exception e) {
      return false;
    }
    return true;
  }
  
  /* Allow the user to set the delimiter or reset it to system delimiter*/
  public void setDelimiter(String s) {
    delimiter = s;
  }
  public void setDelimiter(Pattern p) {
    delimiter = p.toString();
  }
  public void resetDelimiter() {
    Scanner d = new Scanner(System.in);
    delimiter = d.delimiter().toString();
    d.close();
  }
    
  /* Allows user to see if read and write were set */
  public boolean[] exists() {
    boolean[] set = {false, false};
    
    if((rfile != null) && rfile.exists()) {
      set[0] = true;
    }
    if((wfile != null) && wfile.exists()) {
      set[1] = true;
    }
    
    return set;
  }
  public String existsHelp() {
    return "boolean[0] = read\nboolean[1]=write";
  }
  
  /* Needed to implement autocloseable (optional in program) */
  public void close() {
    // Nothing needs to happen as all resources are closed in their methods
  }
}