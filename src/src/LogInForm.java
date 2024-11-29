import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
    LogInForm()
    {
        int frame_width = 1200;
        int frame_height = 700;
        JFrame frame = new JFrame();
        ImageIcon image = new ImageIcon("src/ImageDirectory/library_logo.png");
        frame.setIconImage(image.getImage());
        frame.setTitle("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frame_width, frame_height);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setLayout(new GridBagLayout());
        log_in_form_panel(frame, frame_width, frame_height);
        frame.revalidate();
        frame.repaint();
    }

    private  void log_in_form_panel(JFrame frame, int frame_width, int frame_height)
    {
        JPanel log_in_panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(30, 30);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            }
        };

        int panel_width = (int) (frame_width * 0.3);
        int panel_height = (int) (frame_height * 0.75);

        log_in_panel.setBackground(new Color(0xffde59));
        log_in_panel.setOpaque(false);
        log_in_panel.setPreferredSize(new Dimension(panel_width, panel_height));

        log_in_panel.setLayout(new BoxLayout(log_in_panel, BoxLayout.Y_AXIS));
        log_in_panel_component(log_in_panel, panel_width, panel_height);
        frame.add(log_in_panel);
    }

    private void log_in_panel_component(JPanel panel, int panel_width, int panel_height) {
        ImageIcon logo_img = new ImageIcon("src/ImageDirectory/person_logo.png");

        int img_width = (int) (panel_width * 0.7);
        int img_height = (int) (panel_height * 0.4);
        int margin_height = (int) (panel_height * 0.03);
        Font font_text = new Font("Arial", Font.PLAIN, 20);

        Image scaled_image = logo_img.getImage().getScaledInstance(img_width, img_height, Image.SCALE_SMOOTH);
        ImageIcon resized_icon = new ImageIcon(scaled_image);
        JLabel person_logo = new JLabel(resized_icon);
        person_logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        panel.add(person_logo);

        int box_width = (int) (panel_width * 0.8);
        int box_height = (int) (panel_height * 0.13);

        JTextField email_field = textFieldPanel("Email or username");
        email_field.setFont(font_text);
        email_field.setMaximumSize(new Dimension(box_width, box_height));
        email_field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        panel.add(email_field);

        // Password field (unchanged)
        JTextField password_field = textFieldPanel("Password");
        password_field.setFont(font_text);
        password_field.setMaximumSize(new Dimension(box_width, box_height));
        password_field.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        panel.add(password_field);

        // Log in button (unchanged)
        JButton log_in_button = new JButton("Log in")
        {
            @Override protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                }
                super.paintComponent(g);
            }
            @Override public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };
        log_in_button.setFont(font_text);
        log_in_button.setBackground(new Color(0x38b6ff));
        log_in_button.setForeground(new Color(0xffde59));
        log_in_button.setMaximumSize(new Dimension(box_width, box_height));
        panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        log_in_button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(log_in_button);

        log_in_button.addActionListener( e ->
                System.out.println(email_field.getText())
        );
    }

    JTextField textFieldPanel(String placeholder) {

        JTextField textField = new JTextField(){
            @Override protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                }
                super.paintComponent(g);
            }
            @Override public void updateUI() {
                super.updateUI();
                setOpaque(false);
                setBorder(new RoundedCornerBorder());
            }
        };
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
        return textField;
    }

}
