import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class example extends JFrame
{

    JTextField text;
    example()
    {
        this.setLayout(null);


        text = new JTextField("SADSADSADSADSADSA");
        text.setBounds(105, 101, 92, 16); // Explicitly set position and size
        text.setBorder(null);
        this.add(text); // Add text field to the frame
        this.setSize(500, 500);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);

    }


    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        Insets inset = this.getInsets();
        Shape shapes = new RoundRectangle2D.Double(100 + inset.left, 100 + inset.top, 100, 18, 15, 15);
        Shape shape2 = new RoundRectangle2D.Double(100 + inset.left, 150 + inset.top, 100, 18, 15, 15);
        g2D.setColor(Color.cyan);
        g2D.fill(shapes);
        g2D.setColor(Color.red);

        g2D.draw(shapes);
        g2D.draw(shape2);
    }
    public static void main(String[] args)
    {
        new example();
    }
}