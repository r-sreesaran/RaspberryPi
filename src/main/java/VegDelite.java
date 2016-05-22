/**
 * Created by sree on 22/05/16.
 */
public class VegDelite
{
    private String cost;

    private String size;

    public String getCost ()
    {
        return cost;
    }

    public void setCost (String cost)
    {
        this.cost = cost;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cost = "+cost+", size = "+size+"]";
    }
}