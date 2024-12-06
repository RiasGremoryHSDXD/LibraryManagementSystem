import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;


public class TableModel
{
    String url = "jdbc:mysql://127.0.0.1:3306/library_management_system";
    String user = "root";
    String password = "";
    DefaultTableModel table_model;
    String sql_query;

    JScrollPane scroll_pane_table(int table_width, int type_of_table)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement transaction_statement = connection.createStatement();
            if(type_of_table == 1)
            {
                 sql_query =
                        """
                        SELECT CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.book_id, b.title, b.ISBN, t.quantity, t.borrow_date, t.return_date
                        FROM librarian_transaction t
                        inner join book b on b.book_id = t.book_id
                        inner join staff l on l.staff_id = t.staff_id
                        inner join student s on s.student_id = t.student_id
                         """;
            }

            if(type_of_table == 2)
            {
                sql_query =
                        """
                        SELECT CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.book_id, b.title, b.ISBN, t.quantity, t.borrow_date, t.return_date
                        FROM librarian_transaction t
                        inner join book b on b.book_id = t.book_id
                        inner join staff l on l.staff_id = t.staff_id
                        inner join student s on s.student_id = t.student_id
                        WHERE return_date is null
                        """;
            }

            if(type_of_table == 3)
            {

                sql_query =
                        """
                        SELECT CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.book_id, b.title, b.ISBN, t.quantity, t.borrow_date, t.return_date
                        FROM librarian_transaction t
                        inner join book b on b.book_id = t.book_id
                        inner join staff l on l.staff_id = t.staff_id
                        inner join student s on s.student_id = t.student_id
                        WHERE return_date is not null
                        """;
            }

            if(type_of_table == 4)
            {
                sql_query =
                        """
                        SELECT CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.book_id, b.title, b.ISBN, t.quantity, t.borrow_date, t.return_date
                        FROM librarian_transaction t
                        inner join book b on b.book_id = t.book_id
                        inner join staff l on l.staff_id = t.staff_id
                        inner join student s on s.student_id = t.student_id
                        WHERE borrow_date = CURRENT_DATE
                        """;
            }

            ResultSet query = transaction_statement.executeQuery(sql_query);
            String[] column = {"librarian_name", "student_id", "student_name", "book_id", "book_title", "book_ISBN", "book_quantity", "borrow_date", "return_date"};
            table_model = new DefaultTableModel(column, 0)
            {
                @Override
                public boolean isCellEditable(int row, int column)
                {
                    return column == 8;
                }
            };

            while (query.next())
            {
                Object[] row =
                        {
                                query.getString("librarian_name"),
                                query.getInt("student_id"),
                                query.getString("student_name"),
                                query.getInt("book_id"),
                                query.getString("title"),
                                query.getString("ISBN"),
                                query.getInt("quantity"),
                                query.getDate("borrow_date"),
                                query.getDate("return_date")
                        };
                table_model.addRow(row);
            }

            JTable table = new JTable(table_model);
            table.setPreferredScrollableViewportSize(new Dimension((int) (table_width * 0.98), 50));
            return new JScrollPane(table);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new JScrollPane(new JTable());
    }
}
