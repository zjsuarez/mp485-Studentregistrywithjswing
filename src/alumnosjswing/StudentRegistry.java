package alumnosjswing;

import Frames.MainFrame;
import libraries.*;

import java.io.*;
import java.util.Scanner;

public class StudentRegistry {

    static StudentManager studentManager;

    static {
        try {
            studentManager = new StudentManager("registry.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static  MainFrame mainframe = new MainFrame();

    
    
    
    public static void main(String[] args) throws IOException {
       mainframe.setVisible(true);
    }
    

    
}
