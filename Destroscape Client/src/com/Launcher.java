package com;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;



/**
 * Launcher - Shows splash screen and starts client.
 * @author Klepto
 */
public class Launcher extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1011546544514182943L;
	/**
	 * Splash screen duration (milliseconds).
	 */
	private int splashDuration = 3000;

	/**
	 * Splash screen image.
	 */
	//public String fileName = Config.SplashLocation;

	/**
	 * Shows splash screen in the center of desktop.
	 * @throws IOException 
	 */
	public void showSplash() throws IOException {
		URL url = new URL("http://projectx.x10.bz/local/Splash.gif");
		Image fileName = ImageIO.read(url);  
		
		JPanel content = (JPanel)getContentPane();
		Image img = new ImageIcon(fileName).getImage();
		int width = img.getWidth(null);
		int height = img.getHeight(null);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);
		JLabel label = new JLabel(new ImageIcon(fileName));
		content.setOpaque(false);
		label.setOpaque(false);
		content.add(label, BorderLayout.CENTER);
		setVisible(true);
		/**
		 * Sleep can be replaced with various loadings.
		 */
		try { Thread.sleep(splashDuration); } catch (Exception e) {}
		setVisible(false);
	}

	/**
	 * Starts splash screen.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Launcher splash = new Launcher();
		splash.showSplash();
		Client.main(new String[] {"10", "0", "highmem", "members", "32"});
	}

}