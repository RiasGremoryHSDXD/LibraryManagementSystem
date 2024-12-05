import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame
{
    private final JFrame frame;
    private final int frame_width;
    private final int frame_height;
    MainMenu()
    {
        frame = new JFrame();
        frame_width = 1200;
        frame_height = 700;
        frame.setSize(frame_width, frame_height);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("src/ImageDirectory/library_logo.png");
        frame.setIconImage(image.getImage());
        frame.setLayout(new BorderLayout());
        this.navigation_bar_panel();

        frame.setVisible(true);
    }

    private void navigation_bar_panel()
    {
        TextFieldComponent text_field_component = new TextFieldComponent();
        JPanel navigation_bar = new JPanel();
        navigation_bar.setLayout(new BoxLayout(navigation_bar, BoxLayout.X_AXIS));
        navigation_bar.setPreferredSize(new Dimension(0, (int)(frame_height * 0.1)));
        navigation_bar.setBackground(new Color(0xffde59));

        ImageIcon person_logo = new ImageIcon("src/ImageDirectory/person_logo.png");
        Image scaled_img = person_logo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon final_logo_size = new ImageIcon(scaled_img);
        JLabel image = new JLabel(final_logo_size);
        image.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JPanel search_container = new JPanel();
        search_container.setLayout(new BorderLayout());
        search_container.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        search_container.setPreferredSize(new Dimension((int) (frame_width * 0.46), 45));
        search_container.setMaximumSize(new Dimension((int) (frame_width * 0.46), 45));
        search_container.setOpaque(false);

        JTextField search_bar = text_field_component.textFieldPanel("Search Bar");
        search_bar.setPreferredSize(new Dimension((int) (frame_width * 0.45), 45)); // Set width and height
        search_bar.setMaximumSize(new Dimension((int) (frame_width * 0.45), 45));
        search_bar.setFont(new Font("Arial", Font.PLAIN, 20));

        ImageIcon search_logo = new ImageIcon("src/ImageDirectory/search_logo.png");
        Image scaled_img_1 = search_logo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon search_logo_size = new ImageIcon(scaled_img_1);

        JButton search_button = new JButton(search_logo_size)
        {
            @Override protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof LogInForm.RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((LogInForm.RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                }
                super.paintComponent(g);
            }
            @Override public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new LogInForm.RoundedCornerBorder());
            }
        };
        search_button.setPreferredSize(new Dimension((int) (frame_width * 0.05), 45));
        search_button.setMaximumSize(new Dimension((int) (frame_width * 0.05), 45));
        search_button.setFont(new Font("Arial", Font.PLAIN, 20));
        search_button.setFocusable(false);
        search_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search_button.setBackground(Color.WHITE);

        search_button.addActionListener(e ->
                System.out.println("Search Bar Text: " + search_bar.getText())
        );

        search_container.add(search_bar, BorderLayout.WEST);
        navigation_bar.add(search_container);
        navigation_bar.add(search_button);
        navigation_bar.add(Box.createHorizontalGlue());
        navigation_bar.add(image);
        frame.add(navigation_bar, BorderLayout.NORTH);
    }

}
