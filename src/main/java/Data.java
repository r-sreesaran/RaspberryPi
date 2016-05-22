/**
 * Created by sree on 22/05/16.
 */
public class Data
{
    private String time;

    private OrderDetails orderDetails;

    private String billNumber;

    private CustomerDetails customerDetails;

    private String rating;

    private String table;

    private String date;

    private String employeeName;

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public OrderDetails getOrderDetails ()
    {
        return orderDetails;
    }

    public void setOrderDetails (OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public String getBillNumber ()
    {
        return billNumber;
    }

    public void setBillNumber (String billNumber)
    {
        this.billNumber = billNumber;
    }

    public CustomerDetails getCustomerDetails ()
    {
        return customerDetails;
    }

    public void setCustomerDetails (CustomerDetails customerDetails)
    {
        this.customerDetails = customerDetails;
    }

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public String getTable ()
    {
        return table;
    }

    public void setTable (String table)
    {
        this.table = table;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getEmployeeName ()
    {
        return employeeName;
    }

    public void setEmployeeName (String employeeName)
    {
        this.employeeName = employeeName;
    }

    @Override
    public String toString()
    {
        return "Data [time = "+time+", orderDetails = "+orderDetails+", billNumber = "+billNumber+", customerDetails = "+customerDetails+", rating = "+rating+", table = "+table+", date = "+date+", employeeName = "+employeeName+"]";
    }
}
