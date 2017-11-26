package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageButton extends JButton
{
	private ImageIcon image;
	private ImageIcon hoverImage;
	private ImageButtonListener imageButtonL;
	private int iX, iY;
	
	public ImageButton(String imageName) {
		image = new ImageIcon(imageName);
		hoverImage = null;
		constructor();
	}
	public ImageButton(String imageName, String hoverImageName) {
		iX = 0;
		iY = 0;
		
		image = new ImageIcon(imageName);
		hoverImage = new ImageIcon(hoverImageName);
		constructor();
	}
	public ImageButton(String imageName, String hoverImageName, int x, int y) {
		iX = x;
		iY = y;
		
		image = new ImageIcon(imageName);
		hoverImage = new ImageIcon(hoverImageName);
		constructor();
	}
	
	private void constructor() {
		imageButtonL = new ImageButtonListener();

		this.setSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
		this.setContentAreaFilled(false); // 버튼 바탕색 제거
		this.setBorderPainted(false); // 버튼 테두리 제거
		this.setIcon(image); // 이미지 적용
		this.addMouseListener(imageButtonL);
		this.setLocation(iX, iY);
	}
	
	
	private class ImageButtonListener implements MouseListener
	{
		public void mouseEntered(MouseEvent event) {
			if (hoverImage == null)
				return;
			((JButton)event.getSource()).setIcon(hoverImage);
			setSize(new Dimension(hoverImage.getIconWidth(), hoverImage.getIconHeight()));
			
			setLocation(iX - (hoverImage.getIconWidth() - image.getIconWidth())/2,
						iY - (hoverImage.getIconHeight() - image.getIconHeight())/2);
		}
		
		public void mouseExited(MouseEvent event) {
			if (image == null)
				return;
			((JButton)event.getSource()).setIcon(image);
			setSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
			setLocation(iX, iY);
		}
		
		public void mouseClicked(MouseEvent event) { }
		public void mousePressed(MouseEvent event) { }
		public void mouseReleased(MouseEvent event) { }
	}
}
