import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        this.CRUD_panel();

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

        JButton search_button = button_rounded_corner(search_logo_size, true);
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

        search_button.addMouseListener(new MouseAdapter()
        {
            final Color default_color = Color.WHITE;
            final Color hover_color = new Color(0xe6e6e6);

            @Override
            public void mouseEntered(MouseEvent e)
            {
                search_button.setBackground(hover_color);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                search_button.setBackground(default_color);
            }
        });

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
        JPanel central_panel = new JPanel();
        central_panel.setBackground(new Color(0xffffff));
        central_panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e)
            {
                JScrollPane scroll_pane = tableModel.scroll_pane_table(central_panel.getWidth());
                central_panel.add(scroll_pane);
            }
        });

        frame.add(central_panel);
    }


    private void CRUD_panel()
    {
        JPanel west_panel = new JPanel();
        west_panel.setPreferredSize(new Dimension(300, 0));
        west_panel.setLayout(new BoxLayout(west_panel, BoxLayout.Y_AXIS));
        west_panel.setBackground(new Color(0xffffff));

        JButton add_button = button_rounded_corner(null, false);
        crud_button_attribute(add_button, "Add");

        JButton update_button = button_rounded_corner(null, false);
        crud_button_attribute(update_button, "Update");

        JButton delete_button = button_rounded_corner(null, false);
        crud_button_attribute(delete_button, "Delete");

        JButton today_transaction_button = button_rounded_corner(null, false);
        crud_button_attribute(today_transaction_button, "Today's Transaction");

        JButton pending_return = button_rounded_corner(null, false);
        crud_button_attribute(pending_return, "Pending Return");

        JButton return_book = button_rounded_corner(null, false);
        crud_button_attribute(return_book, "Returned");

        add_button.addActionListener(e -> System.out.println("Add"));
        update_button.addActionListener(e -> System.out.println("Update"));
        delete_button.addActionListener(e -> System.out.println("Delete"));
        today_transaction_button.addActionListener(e -> System.out.println("Today's Transaction"));
        pending_return.addActionListener(e -> System.out.println("Pending Return"));
        return_book.addActionListener(e -> System.out.println("Returned"));

        west_panel.add(Box.createVerticalGlue());
        west_panel.add(add_button);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(update_button);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(delete_button);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(today_transaction_button);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(pending_return);
        west_panel.add(Box.createRigidArea(new Dimension(0, 10)));
        west_panel.add(return_book);
        west_panel.add(Box.createVerticalGlue());

        frame.add(west_panel, BorderLayout.WEST);
    }

    private void crud_button_attribute(JButton button, String button_message)
    {
        button.setPreferredSize(new Dimension((int) (frame_width * 0.2), 45));
        button.setMaximumSize(new Dimension((int) (frame_width * 0.2), 45));
        button.setText(button_message);
        button.setBackground(new Color(0x38b6ff));
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setFocusable(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter()
        {
            final Color default_back_ground_color = new Color(0x38b6ff);
            final Color hover_color = new Color(0xe6e6e6);

            @Override
            public void mouseEntered(MouseEvent e)
            {
                button.setBackground(hover_color);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                button.setBackground(default_back_ground_color);
            }
        });
    }

    private JButton button_rounded_corner(ImageIcon image, boolean has_image)
    {
        JButton button_corner;
        if(has_image)
        {
             button_corner = new JButton(image)
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
        }
        else
        {
             button_corner = new JButton()
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
        }
        return button_corner;
    }
    public static void main(String[] args)
    {
        new MainMenu("WEw");
    }
}
