import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class RoundBorder extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        double x = 50;
        double y = 50;
        frame.setShape(new RoundRectangle2D.Double(x, y, 100, 100, 50, 50));
        frame.setSize(600, 500);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}