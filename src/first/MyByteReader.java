package first;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Miljenko
 */

public class MyByteReader implements FileVisitor<Path>
{
    BufferedOutputStream out;
    
    public MyByteReader(OutputStream output)
    {
        out = new BufferedOutputStream(output);
    }
    
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
    {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
    {
        if(file.toString().endsWith(".txt"))
        {
            try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file.toFile())))
            {
                byte[] buffer = new byte[1024];
                int ret;
            
                while((ret = bis.read(buffer)) > 0)
                    out.write(buffer, 0, ret);
            }
        }
        
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
    {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
    {
        return FileVisitResult.CONTINUE;
    }
}