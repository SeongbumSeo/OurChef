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
		constructor();
	}
	public ImageButton(String imageName, String hoverImageName) {
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
