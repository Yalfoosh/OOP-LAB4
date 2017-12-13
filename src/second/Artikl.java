package second;

import java.util.Objects;

/**
 * @author Miljenko
 */

public class Artikl implements Comparable<Artikl>
{
    private final String naziv;
    private final double cijena;
    
    public Artikl(String name, double price)
    {
        naziv = name;
        cijena = price;
    }
    
    public String getName()
    {
        return naziv;
    }
    
    public double getPrice()
    {
        return cijena;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(other != null && other instanceof Artikl)
            return equals((Artikl)other);
        
        return false;
    }
    
    private boolean equals(Artikl other)
    {
        return other.getName() != null && other.getName().equals(naziv);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.naziv);
        return hash;
    }

    @Override
    public int compareTo(Artikl other)
    {
        return naziv.compareTo(other.getName());
    }
}