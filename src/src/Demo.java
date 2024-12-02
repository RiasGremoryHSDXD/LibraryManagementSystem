import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo extends JFrame {

    private JTextField searchField;
    private JButton searchButton;

    public Demo() {
        // Frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(400, 100);

        // Search field
        searchField = new JTextField(20); // 20 is the column width
        this.add(searchField);

        // Search button
        searchButton = new JButton("Search");
        this.add(searchButton);

        // Add action listener to the button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                JOptionPane.showMessageDialog(
                        null,
                        "You searched for: " + query,
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // Display frame
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Demo();
    }
}
