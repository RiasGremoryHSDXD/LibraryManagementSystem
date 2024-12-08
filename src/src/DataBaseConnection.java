public class DataBaseConnection
{
    public String[] get_connect()
    {
        String url = "jdbc:mysql://127.0.0.1:3306/library_management_system";
        String user = "root";
        String password = "";
        return new String[]{url, user, password};
    }
}
