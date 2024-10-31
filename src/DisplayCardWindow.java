import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DisplayCardWindow 
{
    JFrame displayCardFrame = new JFrame("MyCarCards");
    private JLabel carImageLabel;

    String imagePath;
    BufferedImage carImage;

    SQLiteConnection connObj = new SQLiteConnection();

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public int frameWidth = gd.getDisplayMode().getWidth();
    public int frameHeight = gd.getDisplayMode().getHeight();

    public DisplayCardWindow(int keyValue)
    {
        displayCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayCardFrame.setLayout(new FlowLayout());
        displayCardFrame.setSize(frameWidth, frameHeight);
        displayCardFrame.setVisible(true);

        imagePath = connObj.findExactFileName(keyValue);

        try 
        {
            carImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        carImageLabel = new JLabel(new ImageIcon(carImage));
        displayCardFrame.add(carImageLabel);  

    }
}
