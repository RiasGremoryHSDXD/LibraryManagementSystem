import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LogInForm extends JFrame
{
    private JPanel log_in_panel;
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
        frame.setVisible(true);

        frame.setLayout(new GridBagLayout());
        log_in_form_panel(frame, frame_width, frame_height);
    }

    private  void log_in_form_panel(JFrame frame, int frame_width, int frame_height)
    {
        log_in_panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
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
        log_in_panel.setBackground(new Color(0xffde59));
        log_in_panel.setOpaque(false);

        log_in_panel.setPreferredSize(new Dimension((int) (frame_width * 0.3), (int) (frame_height * 0.8)));
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e)
            {
                int current_frame_width = frame.getWidth();
                int current_frame_height = frame.getHeight();
                log_in_panel.setPreferredSize(new Dimension((int) (current_frame_width * 0.4), (int) (current_frame_height * 0.75)));
                log_in_panel.revalidate();
                log_in_panel.repaint();
            }
        });

        log_in_panel.revalidate();
        log_in_panel.repaint();

        frame.add(log_in_panel);
    }

    private void log_in_panel_component(JPanel panel, int panel_width, int panel_height)
    {

    }
}
