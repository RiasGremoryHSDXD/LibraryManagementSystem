import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentBorrowedInfo_2 extends JFrame
{
    StudentBorrowedInfo_2()
    {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(500, 500);

        JPanel panel = new JPanel();

        String[] table_column = {"student_name", "course"};
        DefaultTableModel table_model = new DefaultTableModel(table_column, 0);
        table_model.addRow(new Object[]{"topi tagupa", "BSIT"});
        table_model.addRow(new Object[]{"Cardo Dalisay", "BSIT"});
        table_model.addRow(new Object[]{"Dog", "BSIT"});
        table_model.addRow(new Object[]{"Cat", "BSIT"});
        JTable table = new JTable(table_model);
        table.setPreferredScrollableViewportSize(new Dimension(200, 20));
        JScrollPane scroll_pane = new JScrollPane(table);
        panel.add(scroll_pane,  BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(true);
    }
    public static void main(String[] args)
    {
        new StudentBorrowedInfo_2();
    }
}
