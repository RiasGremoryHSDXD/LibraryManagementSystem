import javax.swing.*;
import java.awt.*;

public class Demo1 extends JFrame {

    public Demo1() {
        // Frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 100);
        this.setLayout(new FlowLayout());

        // Panel to hold the text field and button
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(250, 30));

        // Text field
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Padding for better appearance

        // Button
        JButton button = new JButton("Go");
        button.setPreferredSize(new Dimension(50, 30));

        // Add text field and button to the panel
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);

        // Add the panel to the frame
        this.add(panel);

        // Button action
        button.addActionListener(e -> {
            String text = textField.getText();
            JOptionPane.showMessageDialog(this, "You entered: " + text);
        });

        // Display the frame
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Demo1();
    }
}
