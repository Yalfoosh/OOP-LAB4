package third;

import first.MyByteWriter;
import java.io.*;
import java.nio.file.*;

/**
 * @author Miljenko
 */

public class MyCriptByteWriter extends MyByteWriter
{
    private final byte key;
    
    public MyCriptByteWriter(Number key, InputStream input, Path output) throws IOException
    {
        super(input, output);
        this.key = (byte)key;
    }
    
    @Override
    public void run()
    {
        try(BufferedInputStream _in = new BufferedInputStream(in);  
            OutputStream _out = new MaskStream(key, new FileOutputStream(out.toFile()));)
        {
            byte[] buffer = new byte[1024];
            int ret;
            
            while((ret = _in.read(buffer)) > 0)
            {
                for(int i = 0; i < ret; ++i)
                    _out.write(buffer[i]);
            }
            
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