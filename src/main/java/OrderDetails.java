public class OrderDetails
{
    private DoubleCheese DoubleCheese;

    private String totalSum;

    private String[] pizza;

    private TandooriPanner TandooriPanner;

    private VegDelite VegDelite;

    public DoubleCheese getDoubleCheese ()
    {
        return DoubleCheese;
    }

    public void setDoubleCheese (DoubleCheese DoubleCheese)
    {
        this.DoubleCheese = DoubleCheese;
    }

    public String getTotalSum ()
    {
        return totalSum;
    }

    public void setTotalSum (String totalSum)
    {
        this.totalSum = totalSum;
    }

    public String[] getPizza ()
    {
        return pizza;
    }

    public void setPizza (String[] pizza)
    {
        this.pizza = pizza;
    }

    public TandooriPanner getTandooriPanner ()
    {
        return TandooriPanner;
    }

    public void setTandooriPanner (TandooriPanner TandooriPanner)
    {
        this.TandooriPanner = TandooriPanner;
    }

    public VegDelite getVegDelite ()
    {
        return VegDelite;
    }

    public void setVegDelite (VegDelite VegDelite)
    {
        this.VegDelite = VegDelite;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [DoubleCheese = "+DoubleCheese+", totalSum = "+totalSum+", pizza = "+pizza+", TandooriPanner = "+TandooriPanner+", VegDelite = "+VegDelite+"]";
    }
}