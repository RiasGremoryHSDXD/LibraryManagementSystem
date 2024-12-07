import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComboBoxData
{
    String url = "jdbc:mysql://127.0.0.1:3306/library_management_system";
    String user = "root";
    String password = "";
    String[] book_array;

    ComboBoxData()
    {
        List<String> book_title = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement book_statement = connection.createStatement();
            String query =
                    """
                    select title, isbn
                    from book
                    where number_of_copies > 0
                    """;

            ResultSet resultSet = book_statement.executeQuery(query);
            while (resultSet.next())
            {
                book_title.add(resultSet.getString("title"));
            }

            String[] book_title_list = book_title.toArray(new String[0]);
            this.available_book(book_title_list);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void available_book(String[] book_title)
    {
        book_array = book_title;
    }

    public String[] return_available_book()
    {
        return book_array;
    }

}
