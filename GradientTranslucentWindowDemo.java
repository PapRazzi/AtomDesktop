package me.atom.windowsj;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
 
/**
 * 
 * GradientTranslucentWindowDemo.java modified by Guillaume Cendre (aka chlkbumper)
 * From the original from Oracle's Java documentation
 * 
 * This class creates a backgroundless JFrame and draw an image instead of it.
 * 
 */

public class GradientTranslucentWindowDemo extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public GradientTranslucentWindowDemo() {
        super("GradientTranslucentWindow");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();
        int x = screenWidth -(screenWidth/3);
        

        
        setLocation(x, 10);
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setSize(new Dimension((screenWidth/3)-10,(screenHeight/4)-50));
        setAlwaysOnTop(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Atom");
        
        
        JPanel panel = new JPanel() {

        	private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                
            	if (g instanceof Graphics2D) {
                    final int R = 240;
                    final int G = 240;
                    final int B = 240;
                    	
                    Paint p =
                        new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                            0.0f, getHeight(), new Color(R, G, B, 0), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            	
            	try {
        			Image img = ImageIO.read(new File("images/bg.png"));
        			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            	
            }
        };
        setContentPane(panel);
        JButton button = new JButton("");
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        add(button);
    }
 
    public static void main(String[] args) {
        // Determine what the GraphicsDevice can support.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        boolean isPerPixelTranslucencySupported = 
            gd.isWindowTranslucencySupported(PERPIXEL_TRANSLUCENT);
 
        //If translucent windows aren't supported, exit.
        if (!isPerPixelTranslucencySupported) {
            System.out.println(
                "Per-pixel translucency is not supported");
                System.exit(0);
        }
 
        JFrame.setDefaultLookAndFeelDecorated(false);
 
        // Create the GUI on the event-dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GradientTranslucentWindowDemo gtw = new GradientTranslucentWindowDemo();
 
                // Display the window.
                gtw.setVisible(true);
            }
        });
    }
}