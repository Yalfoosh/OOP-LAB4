package first;

import java.io.*;
import java.nio.file.*;

/**
 * @author Miljenko
 */

public class MultipleFileProgram 
{
    public static void main(String[] args)
    {
        String root = new File(".").getAbsolutePath();
    
        Path source = Paths.get(root + "/racuni");
        Path destination = Paths.get(root + "/results/multiple.txt");
        
        try
        {
            if(!Files.exists(destination, LinkOption.NOFOLLOW_LINKS))
                Files.createFile(destination);
            
            OutputStream arg = new FileOutputStream(destination.toFile());
            
            Files.walkFileTree(source, new MyByteReader(arg));
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}