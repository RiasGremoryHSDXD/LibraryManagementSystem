import java.sql.*;

public class UpdateBorrowerData
{
    DataBaseConnection db_connect = new DataBaseConnection();

    public boolean validate_transaction_number(int transaction_number)
    {
        try
        {
            String get_transaction_number = "SELECT librarian_transaction_id FROM librarian_transaction where return_date is null";
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement all_transaction_number = connection.prepareStatement(get_transaction_number);


            ResultSet resultSet = all_transaction_number.executeQuery();
            while (resultSet.next())
            {
                if(resultSet.getInt("librarian_transaction_id") == transaction_number)
                {
                    return true;
                }
            }
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public Object[] borrower_return_info(int transaction_number)
    {
        try
        {
            String borrower_query =
                    """
                    SELECT student_number_id AS student_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) AS student_name, l.quantity, l.borrow_date
                    FROM librarian_transaction l
                    INNER JOIN student s ON s.student_id = l.student_id
                    WHERE librarian_transaction_id = ?
                    """;
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement get_borrower_info = connection.prepareStatement(borrower_query);

            get_borrower_info.setInt(1, transaction_number);
            ResultSet resultSet = get_borrower_info.executeQuery();

            if(resultSet.next())
            {
                long student_id = resultSet.getLong("student_id");
                String student_name = resultSet.getString("student_name");
                int quantity = resultSet.getInt("quantity");
                Date borrowed_date = resultSet.getDate("borrow_date");
                return new Object[]{student_id, student_name, quantity, borrowed_date};
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new Object[]{};
    }

    public void update_returned_date(int transaction_id)
    {
        try
        {
            String set_returned_date = "UPDATE librarian_transaction SET return_date = ? WHERE librarian_transaction_id = ?";
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement update_returned_date = connection.prepareStatement(set_returned_date);

            long milli_seconds = System.currentTimeMillis();
            Date current_date = new Date(milli_seconds);

            update_returned_date.setDate(1, current_date);
            update_returned_date.setLong(2, transaction_id);
            update_returned_date.executeUpdate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
