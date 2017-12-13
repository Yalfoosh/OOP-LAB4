package third;

import java.io.*;
import java.nio.file.*;

/**
 * @author Miljenko
 */

public class Program 
{
    static byte SINGULARITY = 109;
    
    public static void main(String[] args)
    {
        String root = new File(".").getAbsolutePath();
    
        Path source = Paths.get(root + "/racuni/2015/3/Racun_12.txt");
        Path destCrypt = Paths.get(root + "/results/crypt.txt");
        Path destDecrypt = Paths.get(root + "/results/decrypt.txt");
        
        try
        {
            if(Files.exists(destCrypt, LinkOption.NOFOLLOW_LINKS))
                Files.delete(destCrypt);
            
            if(Files.exists(destDecrypt, LinkOption.NOFOLLOW_LINKS))
                Files.delete(destDecrypt);
            
            Files.createFile(destCrypt);
            Files.createFile(destDecrypt);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        
        try(InputStream src1 = Files.newInputStream(source, StandardOpenOption.READ);
            InputStream src2 = Files.newInputStream(destCrypt, StandardOpenOption.READ);)
        {
            new MyCriptByteWriter(SINGULARITY, src1, destCrypt).run();
            new MyCriptByteWriter(SINGULARITY, src2, destDecrypt).run();
            
            src1.close();
            src2.close();
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
}