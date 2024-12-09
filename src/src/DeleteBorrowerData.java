import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteBorrowerData
{
    DataBaseConnection db_connect = new DataBaseConnection();

    public void deleteStudentRecord(long student_id, int book_quantity, String book_name, long transaction_id)
    {
        try
        {
            String delete_query = "DELETE FROM student WHERE student_id = ?";
            String get_current_book_quantity_query = "SELECT number_of_copies FROM book WHERE title = ?";
            String validate_transaction_id_query = "SELECT librarian_transaction_id FROM librarian_transaction";
            String add_book_quantity_query = "UPDATE book SET number_of_copies = ? WHERE title = ?";

            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement delete_student_record = connection.prepareStatement(delete_query);
            PreparedStatement get_book_quantity = connection.prepareStatement(get_current_book_quantity_query);
            PreparedStatement validate_transaction_id = connection.prepareStatement(validate_transaction_id_query);
            PreparedStatement add_book_quantity = connection.prepareStatement(add_book_quantity_query);

            ResultSet resultSet = validate_transaction_id.executeQuery();
            while (resultSet.next())
            {
                if(resultSet.getInt("librarian_transaction_id") == transaction_id)
                {
                    delete_student_record.setLong(1, student_id);
                    get_book_quantity.setString(1, book_name.trim());
                    delete_student_record.executeUpdate();
                    ResultSet resultSet1 = get_book_quantity.executeQuery();
                    if(resultSet1.next())
                    {
                        int current_quantity = resultSet1.getInt("number_of_copies");
                        int update_current_quantity = current_quantity + book_quantity;
                        add_book_quantity.setInt(1, update_current_quantity);
                        add_book_quantity.setString(2, book_name);
                        add_book_quantity.executeUpdate();
                    }
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
