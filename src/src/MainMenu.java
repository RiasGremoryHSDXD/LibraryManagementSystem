import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame
{
    private final JFrame frame;
    private final int frame_width;
    private final int frame_height;
    private String user_name;
    MainMenu(String user_name)
    {
        this.user_name = user_name;
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
        this.information_panel();
        this.margin_information_panel();

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
                {
                    System.out.println("Search Bar Text: " + search_bar.getText());
                    System.out.println("User name: " + user_name);
                }
        );

        search_container.add(search_bar, BorderLayout.WEST);
        navigation_bar.add(search_container);
        navigation_bar.add(search_button);
        navigation_bar.add(Box.createHorizontalGlue());
        navigation_bar.add(image);
        frame.add(navigation_bar, BorderLayout.NORTH);
    }

    private void information_panel()
    {
        TableModel tableModel = new TableModel();
        JScrollPane scroll_pane = tableModel.scroll_pane_table();
        JPanel central_panel = new JPanel();
        central_panel.setBackground(Color.yellow);

        // Add a listener to get dimensions after the panel is laid out
        central_panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                System.out.println("Width: " + central_panel.getWidth());
                System.out.println("Height: " + central_panel.getHeight());
            }
        });

        central_panel.add(scroll_pane);
        frame.add(central_panel);
    }

    private void margin_information_panel()
    {
        JPanel west_panel = new JPanel();
        JPanel east_panel = new JPanel();
        JPanel south_panel = new JPanel();

        west_panel.setPreferredSize(new Dimension(300, 0));
//        east_panel.setPreferredSize(new Dimension(100, 0));
        south_panel.setPreferredSize(new Dimension(0, (int)(frame_height * 0.1)));

        west_panel.setBackground(Color.green);
        east_panel.setBackground(Color.green);
        south_panel.setBackground(Color.green);

        frame.add(west_panel, BorderLayout.WEST);
//        frame.add(east_panel, BorderLayout.EAST);
        frame.add(south_panel, BorderLayout.SOUTH);
    }

    public static void main(String[] args)
    {
        new MainMenu("WEw");
    }
}
