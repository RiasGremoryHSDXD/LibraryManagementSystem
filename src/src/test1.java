import javax.swing.*;
import java.awt.*;

public class test1 extends JFrame {

    test1() {
        // Set up the frame
        this.setLayout(new GridBagLayout());
        this.setTitle("Testing");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);

        center_panel(); // Add the centered panel
        this.repaint();
        this.revalidate();
    }

    private void center_panel() {
        JPanel box = new JPanel();

        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(Color.red);
        box.setPreferredSize(new Dimension(250, 250));
        JTextField one = new JTextField(10);
        one.setMaximumSize(new Dimension(100, 20));
        one.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        box.add(one);
        JLabel two = new JLabel("Second element");
        two.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        two.setForeground(Color.red);
        box.add(two);
        this.add(box);

        this.add(box);

    }

    public static void main(String[] args) {
        new test1();
    }
}
