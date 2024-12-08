import java.sql.*;

public class BookDetails
{
    DataBaseConnection db_connect = new DataBaseConnection();

    public int[] BookInfo(String book_title) {
        try {
            String query = "SELECT number_of_copies AS quantity, book_id FROM book WHERE title = ?";
            Connection connection = DriverManager.getConnection(
                    db_connect.get_connect()[0],
                    db_connect.get_connect()[1],
                    db_connect.get_connect()[2]
            );
            PreparedStatement count_book = connection.prepareStatement(query);
            count_book.setString(1, book_title);

            ResultSet resultSet = count_book.executeQuery();

            if (resultSet.next())
            {
                int quantity = resultSet.getInt("quantity");
                int bookId = resultSet.getInt("book_id");
                return new int[] { quantity, bookId };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[] {};
    }

}
