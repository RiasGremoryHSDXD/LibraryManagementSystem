import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;


public class TableModel
{
    String url = "jdbc:mysql://127.0.0.1:3306/library_management_system";
    String user = "root";
    String password = "";

    JScrollPane scroll_pane_table()
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement transaction_date = connection.createStatement();

            String wew = """
                    SELECT CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.book_id, b.title, b.ISBN, t.borrow_date, t.return_date
                    FROM librarian_transaction t
                    inner join book b on b.book_id = t.book_id
                    inner join staff l on l.staff_id = t.staff_id
                    inner join student s on s.student_id = t.student_id
                    """;
            ResultSet query = transaction_date.executeQuery(wew);

            String[] column = {"librarian_name", "student_id", "student_name", "book_id", "book_title", "book_ISBN", "borrow_date", "return_date"};
            DefaultTableModel table_model = new DefaultTableModel(column, 0)
            {
                @Override
                public boolean isCellEditable(int row, int column)
                {
                    return column == 7;
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
                                query.getDate("borrow_date"),
                                query.getDate("return_date")
                        };
                table_model.addRow(row);
            }

            JTable table = new JTable(table_model);
            table.setPreferredScrollableViewportSize(new Dimension(884 - 20, 50));
            return new JScrollPane(table);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new JScrollPane(new JTable());
    }
}
