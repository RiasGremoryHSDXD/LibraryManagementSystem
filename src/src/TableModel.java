import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;


public class TableModel
{

    DataBaseConnection db_connect = new DataBaseConnection();
    DefaultTableModel table_model;
    String sql_query;

    JScrollPane scroll_pane_table(int table_width, int table_height, int type_of_table)
    {
        try
        {
            Connection connection = DriverManager.getConnection(db_connect.get_connect()[0], db_connect.get_connect()[1], db_connect.get_connect()[2]);
            Statement transaction_statement = connection.createStatement();
            if(type_of_table == 1)
            {
                 sql_query =
                        """
                        SELECT librarian_transaction_id as transaction_id, CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id as record_number,s.student_number_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.title, t.quantity, t.borrow_date, t.return_date
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
                        SELECT librarian_transaction_id as transaction_id, CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id as record_number, s.student_number_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.title, t.quantity, t.borrow_date, t.return_date
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
                        SELECT librarian_transaction_id as transaction_id, CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id as record_number, s.student_number_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.title, t.quantity, t.borrow_date, t.return_date
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
                        SELECT librarian_transaction_id as transaction_id, CONCAT(l.last_name, ", ", l.first_name, " ", l.middle_name) as librarian_name, s.student_id as record_number, s.student_number_id, CONCAT(s.last_name, ", ", s.first_name, " ", s.middle_name) as student_name, b.title, t.quantity, t.borrow_date, t.return_date
                        FROM librarian_transaction t
                        inner join book b on b.book_id = t.book_id
                        inner join staff l on l.staff_id = t.staff_id
                        inner join student s on s.student_id = t.student_id
                        WHERE borrow_date = CURRENT_DATE
                        """;
            }

            ResultSet query = transaction_statement.executeQuery(sql_query);
            String[] column = {"Transaction #","Librarian Name", "record #" ,"Student ID", "Student Name", "Book Title", "Quantity", "Borrow Date", "Return Date"};
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
                                query.getLong("transaction_id"),
                                query.getString("librarian_name"),
                                query.getLong("record_number"),
                                query.getLong("student_number_id"),
                                query.getString("student_name"),
                                query.getString("title"),
                                query.getInt("quantity"),
                                query.getDate("borrow_date"),
                                query.getDate("return_date")
                        };
                table_model.addRow(row);
            }

            JTable table = new JTable(table_model);
            table.setCursor(new Cursor(Cursor.HAND_CURSOR));
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2)
                    {
                        row_is_right_click(table, e);
                    }
                }

                private void row_is_right_click(JTable table, MouseEvent e)
                {
                    int row = table.rowAtPoint(e.getPoint());
                    if(row >= 0)
                    {
                        long record_number = (long) table.getValueAt(row, 2);
                        long transaction_id = (long) table.getValueAt(row, 0);
                        int book_quantity = (int) table.getValueAt(row, 6);
                        String book_name = (String) table.getValueAt(row, 5);
                        int result = JOptionPane.showConfirmDialog
                                (
                                        null,
                                        "Are you sure you want to delete this row with transaction ID: " + transaction_id,
                                        "Delete Record",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.WARNING_MESSAGE
                                );

                        if(result == JOptionPane.OK_OPTION)
                        {
                            DeleteBorrowerData delete_borrower_data = new DeleteBorrowerData();
                            delete_borrower_data.deleteStudentRecord(record_number, book_quantity, book_name, transaction_id);
                        }
                    }
                }
            });
            table.setPreferredScrollableViewportSize(new Dimension((int) (table_width * 0.98), (int) (table_height * 0.70)));
            return new JScrollPane(table);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new JScrollPane(new JTable());
    }
}
