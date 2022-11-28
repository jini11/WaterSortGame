package utils;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImageUtil {
	
	public JLabel makeLabel(String name, int width, int height) {
		ImageIcon icon = new ImageIcon(name);
		Image image = icon.getImage();
		image = image.getScaledInstance(width, height, Image.SCALE_FAST);	    
	    return new JLabel(new ImageIcon(image));
	}
	
	public JButton makeUI(String name, int width) {
		ImageIcon icon = new ImageIcon(name);
		Image image = icon.getImage();
		image = image.getScaledInstance(width, 100, Image.SCALE_FAST);
		JButton btn = new JButton(new ImageIcon(image));
		limpidity(btn);
		return btn;
	}
	
	public ImageIcon makeImage(String name, int width, int height) {
		ImageIcon icon = new ImageIcon(name);
		Image image = icon.getImage();
		image = image.getScaledInstance(width, height, Image.SCALE_FAST);
		return new ImageIcon(image);
	}
	
	private void limpidity(JButton btn) {
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setOpaque(false);
	}
}
