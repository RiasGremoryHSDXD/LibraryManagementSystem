import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class LogInForm extends JFrame
{
    static class RoundedCornerBorder extends AbstractBorder {
        private final Color ALPHA_ZERO = new Color(0x0, true);
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape border = getBorderShape(x, y, width - 1, height - 1);
            g2.setPaint(ALPHA_ZERO);
            Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
            corner.subtract(new Area(border));
            g2.fill(corner);
            g2.setPaint(Color.GRAY);
            g2.draw(border);
        }
        public Shape getBorderShape(int x, int y, int w, int h) {
            return new RoundRectangle2D.Double(x, y, w, h, 15, 15);
        }
        @Override public Insets getBorderInsets(Component c) {
            return new Insets(4, 8, 4, 8);
        }
    }

    private final JFrame frame;
    JPanel form_panel;

    private final int frame_width;
    private final int frame_height;
    int form_width;
    int form_height;
    LogInForm()
    {
        frame = new JFrame();
        frame_width = 1200;
        frame_height = 700;
        frame.setLayout(new GridBagLayout());
        frame.setSize(frame_width, frame_height);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("src/ImageDirectory/library_logo.png");
        frame.setIconImage(image.getImage());
        frame.setTitle("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.log_in_form_panel();
        this.form_component();

        frame.setVisible(true);
    }

    private void log_in_form_panel()
    {
        form_panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Dimension arcs = new Dimension(30, 30);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics2D = (Graphics2D) g;

                graphics2D.setColor(getBackground());
                graphics2D.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            }
        };

        form_panel.setOpaque(false);
        form_width = (int) (frame_width * 0.3);
        form_height = (int) (frame_height * 0.75);

        form_panel.setPreferredSize(new Dimension(form_width, form_height));
        form_panel.setBackground(new Color(0xffde59));
        form_panel.setLayout(new BoxLayout(form_panel, BoxLayout.Y_AXIS));
        frame.add(form_panel);
    }

    private void form_component()
    {
        TextFieldComponent text_field_component = new TextFieldComponent();

        ImageIcon person_logo = new ImageIcon("src/ImageDirectory/person_logo.png");

        int img_width = (int) (form_width * 0.7);
        int img_height = (int) (form_height * 0.4);
        int margin_height = (int) (form_height * 0.03);

        Image scaled_img = person_logo.getImage().getScaledInstance(img_width, img_height, Image.SCALE_SMOOTH);
        ImageIcon final_logo_size = new ImageIcon(scaled_img);
        JLabel image = new JLabel(final_logo_size);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font font_text = new Font("Arial", Font.PLAIN, 20);
        int box_width = (int) (form_width * 0.8);
        int box_height = (int) (form_height * 0.13);

        JTextField email_text_field = text_field_component.textFieldPanel("Email or username");
        email_text_field.setFont(font_text);
        email_text_field.setMaximumSize(new Dimension(box_width, box_height));
        email_text_field.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField password_text_field = text_field_component.textFieldPanel("Password");
        password_text_field.setFont(font_text);
        password_text_field.setMaximumSize(new Dimension(box_width, box_height));
        password_text_field.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton log_in_button = new JButton("Log in")
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

        log_in_button.setFont(font_text);
        log_in_button.setFocusable(false);
        log_in_button.setBackground(new Color(0x38b6ff));
        log_in_button.setForeground(new Color(0xffde59));
        log_in_button.setMaximumSize(new Dimension(box_width, box_height));
        log_in_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        log_in_button.setCursor(new Cursor(Cursor.HAND_CURSOR));


        log_in_button.addActionListener(e ->
        {
            String email = email_text_field.getText();
            String password = password_text_field.getText();

            LogInAuthentication authentication = new LogInAuthentication("", "");
            authentication.setUserInputEmail(email);
            authentication.setUserInputPassword(password);
            authentication.validateCredentials();

            if (authentication.isCorrectCredentials())
            {
                 frame.dispose();
                 new MainMenu();
            }
            else
            {
                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid email or password. Please try again.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

            }
        }
        );

        form_panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        form_panel.add(image);
        form_panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        form_panel.add(email_text_field);
        form_panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        form_panel.add(password_text_field);
        form_panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        form_panel.add(log_in_button);
    }
}
