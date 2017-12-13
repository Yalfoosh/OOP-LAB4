package second;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


/**
 * @author Miljenko
 */

public class MySecondByteReader implements FileVisitor<Path>
{
    private HashMap<String, Artikl> memory;
    
    public MySecondByteReader()
    {
        memory = new HashMap<>();
    }
    
    public void WriteToOutput(Path path, Charset set)
    {
        Collection<Artikl> t = memory.values();
        List<Artikl> l = new ArrayList<>(t.size());
        
        for(Artikl a : t)
            l.add(a);
        
        l.sort(Artikl::compareTo);
        
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toFile()), set)))
        {
            for(Artikl a : l)
            {
                String currLine = "Ime artikla: " + a.getName() + "\nCijena: " + a.getPrice() + "\n\n";
                bw.write(currLine);
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
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
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.toFile()))))
            {
                String currLine;
            
                while((currLine = br.readLine()) != null)
                {
                    if(currLine.startsWith("Racun ") || currLine.startsWith("Kupac:") || currLine.startsWith("Proizvod") 
                       || currLine.startsWith("UKUPNO") || currLine.startsWith("Račun ") || currLine.startsWith("---") 
                       || currLine.startsWith(" ") || currLine.length() < 1)
                        continue;
                    
                    String[] params = currLine.split("\\s{4,}");

                    if(!memory.containsKey(params[0]))
                    {
                        Artikl t = new Artikl(params[0], Double.parseDouble(params[1].split("\\s+")[0]));
                        memory.put(params[0], t);
                    }
                }
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