package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageButton extends JButton
{
	private ImageIcon image;
	private ImageIcon hoverImage;
	private ImageButtonListener imageButtonL;
	
	public ImageButton(String imageName) {
		image = new ImageIcon(imageName);
		hoverImage = null;
		
		imageButtonL = new ImageButtonListener();
		
		this.setIcon(image);
		this.addMouseListener(imageButtonL);
	}
	public ImageButton(String imageName, String hoverImageName) {
		image = new ImageIcon(imageName);
		hoverImage = new ImageIcon(hoverImageName);
		
		imageButtonL = new ImageButtonListener();
		
		this.setIcon(image);
		this.addMouseListener(imageButtonL);
	}
	
	private class ImageButtonListener implements MouseListener
	{
		public void mouseEntered(MouseEvent event) {
			if (hoverImage == null)
				return;
			((JButton)event.getSource()).setIcon(hoverImage);
		}
		
		public void mouseExited(MouseEvent event) {
			if (image == null)
				return;
			((JButton)event.getSource()).setIcon(image);
		}
		
		public void mouseClicked(MouseEvent event) { }
		public void mousePressed(MouseEvent event) { }
		public void mouseReleased(MouseEvent event) { }
	}
}
