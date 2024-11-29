import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LogInForm_2 extends JFrame
{
    JFrame frame;
    JPanel form_panel;

    private final int frame_width;
    private final int frame_height;
    int form_width;
    int form_height;
    LogInForm_2()
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
        int box_height = (int) (form_width * 0.13);

        JTextField email_text_field = new JTextField();
        email_text_field.setFont(font_text);
        email_text_field.setSize(new Dimension(box_width, box_height));

        form_panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        form_panel.add(image);
        form_panel.add(Box.createRigidArea(new Dimension(0, margin_height)));
        this.create_round_border(email_text_field);
    }

    private void create_round_border(JTextField text_field)
    {
        JPanel panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Dimension arcs = new Dimension(15, 15);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics2D = (Graphics2D) g;

                graphics2D.setColor(getBackground());
                graphics2D.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            }
        };

        panel.setOpaque(false);
        int panel_width = (int) (form_width * 0.8);
        int panel_height = (int) (form_width * 0.13);
        panel.setBackground(Color.red);
        panel.setMaximumSize(new Dimension(panel_width, panel_height));

        // Adjust text field to fit the panel
        text_field.setPreferredSize(new Dimension(panel_width - 20, panel_height - 11)); // Slight padding
        text_field.setMaximumSize(new Dimension(panel_width - 20, panel_height - 11));
        text_field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add some padding inside the text field

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(text_field);
        form_panel.add(panel);
    }
    public static void main(String[] args)
    {
        new LogInForm_2();
    }
}
