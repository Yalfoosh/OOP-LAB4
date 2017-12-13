package first;

import java.io.*;
import java.nio.file.*;

/**
 * @author Miljenko
 */

public class SingleFileProgram 
{
    public static void main(String[] args)
    {
        String root = new File(".").getAbsolutePath();
        
        Path source = Paths.get(root + "/racuni/2015/1/Racun_0.txt");
        Path destination = Paths.get(root + "/results/singleout.txt");
        
        try(InputStream src = Files.newInputStream(source, StandardOpenOption.READ))
        {
            new MyByteWriter(src, destination).run();
            
            src.close();
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
}