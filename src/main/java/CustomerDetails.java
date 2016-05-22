/**
 * Created by sree on 22/05/16.
 */
public class CustomerDetails
{
    private String cell;

    private String name;

    public String getCell ()
    {
        return cell;
    }

    public void setCell (String cell)
    {
        this.cell = cell;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "customerDetails [cell = "+cell+", name = "+name+"]";
    }
}

