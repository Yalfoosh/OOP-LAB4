package first;

import java.io.*;
import java.nio.file.Path;

/**
 * @author Miljenko
 */

public class MyByteWriter 
{
    protected final InputStream in;
    protected final Path out;
    
    public MyByteWriter(InputStream input, Path output) throws IOException
    {
        in = input;
        out = output;
    }
    
    public void run()
    {
        try(BufferedInputStream _in = new BufferedInputStream(in); 
            FileOutputStream _out = new FileOutputStream(out.toFile());)
        {
            byte[] buffer = new byte[1024];
            int ret;
            
            while((ret = _in.read(buffer)) > 0)
                    _out.write(buffer, 0, ret);
            
            in.close();
            _in.close();
            _out.close();
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
    }
}