package third;

import java.io.*;



/**
 * @author Miljenko
 */

public class MaskStream extends OutputStream
{
    private final OutputStream stream;
    private final byte key;
    
    public MaskStream(Number key, OutputStream output)
    {
        this.key = (byte)key;
        stream = output;
    }
    
    @Override
    public void write(int read) throws IOException
    {
        stream.write(read ^ key);
    }
}