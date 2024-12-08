import java.sql.*;

public class AddBorrowerData
{
    DataBaseConnection db_connect = new DataBaseConnection();
    private long student_id;

    public void addStudentTable(long student_id, String first_name, String middle_name, String last_name, String email_address, String contact_number, String location)
    {
        String insert_query = "INSERT INTO student(student_number_id, first_name, middle_name, last_name, email_address, contact_number, location) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try
        {
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement insert_student_table = connection.prepareStatement(insert_query, Statement.RETURN_GENERATED_KEYS);

            insert_student_table.setLong(1, student_id);
            insert_student_table.setString(2, first_name);
            insert_student_table.setString(3, middle_name);
            insert_student_table.setString(4, last_name);
            insert_student_table.setString(5, email_address);
            insert_student_table.setString(6, contact_number);
            insert_student_table.setString(7, location);

            // Execute the insert
            int rowsAffected = insert_student_table.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = insert_student_table.getGeneratedKeys();
                if (generatedKeys.next()) {
                    this.student_id = generatedKeys.getLong(1);
                }
            } else {
                System.out.println("No row was inserted.");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addTransactionTable(int book_id, int staff_id, long student_id, int quantity)
    {
        String insert_query = "INSERT INTO librarian_transaction(book_id, staff_id, student_id, quantity, borrow_date) VALUES (?, ?, ?, ?, ?)";
        try
        {
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement insert_transaction_table = connection.prepareStatement(insert_query, Statement.RETURN_GENERATED_KEYS);

            insert_transaction_table.setInt(1, book_id);
            insert_transaction_table.setInt(2, staff_id);
            insert_transaction_table.setLong(3, student_id);
            insert_transaction_table.setInt(4, quantity);
            long millis = System.currentTimeMillis();
            Date currentDate = new Date(millis);
            insert_transaction_table.setDate(5, currentDate);
            insert_transaction_table.executeUpdate();
            this.updateBookQuantity(book_id, quantity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void updateBookQuantity(int book_id,int quantity)
    {
        String update_book_quantity = "UPDATE book SET number_of_copies = ? WHERE book_id = ?";
        String get_book_quantity = "SELECT number_of_copies FROM book WHERE book_id = ?";
        try
        {
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            PreparedStatement get_quantity = connection.prepareStatement(get_book_quantity);
            get_quantity.setInt(1, book_id);

            ResultSet resultSet = get_quantity.executeQuery();
            if (resultSet.next())
            {
                int current_book_quantity = resultSet.getInt("number_of_copies");
                PreparedStatement update_quantity = connection.prepareStatement(update_book_quantity);

                int book_updated_quantity = current_book_quantity - quantity;

                update_quantity.setInt(1, book_updated_quantity);
                update_quantity.setInt(2, book_id);
                update_quantity.executeUpdate();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public long getStudent_id()
    {
        return this.student_id;
    }

}
