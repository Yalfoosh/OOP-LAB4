package second;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;

/**
 * @author Miljenko
 */

public class Program 
{
    public static void main(String[] args)
    {
        String root = new File(".").getAbsolutePath();
    
        Path source = Paths.get(root + "/racuni");
        Path destination1 = Paths.get(root + "/results/cjenik.88592.txt");
        Path destination2 = Paths.get(root + "/results/cjenik.utf8.txt");
        
        try
        {
            if(Files.exists(destination1, LinkOption.NOFOLLOW_LINKS))
                Files.delete(destination1);
            
            if(Files.exists(destination2, LinkOption.NOFOLLOW_LINKS))
                Files.delete(destination2);
            
            Files.createFile(destination1);
            Files.createFile(destination2);
            
            FileVisitor<Path> test = new MySecondByteReader();
            
            Files.walkFileTree(source, test);
            
            ((MySecondByteReader)test).WriteToOutput(destination1, Charset.forName("ISO-8859-2"));
            ((MySecondByteReader)test).WriteToOutput(destination2, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}