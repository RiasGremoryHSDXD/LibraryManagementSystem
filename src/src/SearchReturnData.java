import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchReturnData
{
    DataBaseConnection db_connect = new DataBaseConnection();

    public boolean bookTitleExist(String book_title)
    {
        try
        {
            String book_is_exist_query = "SELECT title FROM book";
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement book_is_exist_stmt = connection.prepareStatement(book_is_exist_query);

            ResultSet resultSet = book_is_exist_stmt.executeQuery();
            while (resultSet.next())
            {
                if(resultSet.getString("title").trim().toLowerCase().equals(book_title))
                {
                    return true;
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }


    public Object[] book_details(String book_title)
    {
        try
        {
            String get_book_details = "SELECT * FROM book WHERE title = ?";
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement book_details = connection.prepareStatement(get_book_details);

            book_details.setString(1, book_title);

            ResultSet resultSet = book_details.executeQuery();
            if(resultSet.next())
            {
                long book_id = resultSet.getLong("book_id");
                String book_name = resultSet.getString("title");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("number_of_copies");
                String ISBN = resultSet.getString("ISBN");
                String author = resultSet.getString("author");

                return new Object[]{book_id, book_name, category, quantity, ISBN, author};
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new Object[]{};
    }
}
