import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentBorrowedInfo extends JFrame {
    public StudentBorrowedInfo() {
        // Frame setup
        setTitle("Student Borrowed Information");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for table
        JPanel panel = new JPanel(new BorderLayout());

        // Database credentials
        String url = "jdbc:mysql://127.0.0.1:3306/library_management_system";
        String user = "root";
        String password = "";

        try {
            // Connect to database
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // Query for borrowed information
            String query = """
                SELECT 
                    s.student_id, 
                    CONCAT(s.last_name, ', ', s.first_name, ' ', s.middle_name) AS student_name, 
                    b.title AS book_title, 
                    l.borrow_date, 
                    l.return_date
                FROM 
                    librarian_transaction l
                JOIN 
                    student s ON l.student_id = s.student_id
                JOIN 
                    book b ON l.book_id = b.book_id
            """;

            ResultSet resultSet = statement.executeQuery(query);

            // Table columns
            String[] columns = {"Student ID", "Student Name", "Book Title", "Borrow Date", "Return Date"};
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0)
            {
                @Override
                public boolean isCellEditable(int row, int column)
                {
                    return column == 2;
                }
            };

            // Populate table with data
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("book_title"),
                        resultSet.getDate("borrow_date"),
                        resultSet.getDate("return_date")
                };
                tableModel.addRow(row);
            }

            // Close database resources
            resultSet.close();
            statement.close();
            connection.close();

            JTable table = new JTable(tableModel);

            // Add table to JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);

            // Add scroll pane to panel
            panel.add(scrollPane, BorderLayout.CENTER);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Add panel to frame
        add(panel);
    }

    public static void main(String[] args) {
        StudentBorrowedInfo frame = new StudentBorrowedInfo();
        frame.setVisible(true);
    }
}
