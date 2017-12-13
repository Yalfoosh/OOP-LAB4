package third;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * @author Miljenko
 */

public class Test 
{
    public static void main(String[] args)
    {
        String root = new File(".").getAbsolutePath();
    
        Path original = Paths.get(root + "/racuni/2015/3/Racun_12.txt");
        Path altered = Paths.get(root + "/results/decrypt.txt");
        
        try
        {
            if(Files.size(original) == Files.size(altered))
            {
                if(Arrays.equals(Files.readAllLines(original).toArray(), Files.readAllLines(altered).toArray()))
                    System.out.println("Sve štima!");
                else
                    System.out.println("Sadržaj dviju datoteka nije jednak!");
            }
            else
                System.out.println("Velicine nisu jednake!");
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}