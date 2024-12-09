import java.sql.Date;

public class DateTesting
{
    public static void main(String[] args)
    {
        long millis = System.currentTimeMillis();
        Date sql_date = new Date(millis);
        System.out.println(sql_date);
    }
}
