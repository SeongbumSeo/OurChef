package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageButton extends JButton
{
	private ImageIcon image; // ��ư �̹���
	private ImageIcon hoverImage; // ���콺 ���� �� ��ư �̹���
	private int iX, iY; // ��ư�� ��ġ
	
	private ImageButtonListener imageButtonL;
	
	/**
	 * �̹��� ��ư�� �������Դϴ�.
	 * @param imageName ��ư �̹���
	 */
	public ImageButton(String imageName) {
		image = new ImageIcon(imageName);
		hoverImage = null;
		constructor();
	}
	/**
	 * �̹��� ��ư�� �������Դϴ�.
	 * @param imageName ��ư �̹���
	 * @param x ��ư�� ��ġ X
	 * @param y ��ư�� ��ġ Y
	 */
	public ImageButton(String imageName, int x, int y) {
		iX = x;
		iY = y;
		
		image = new ImageIcon(imageName);
		hoverImage = null;
		constructor();
	}
	/**
	 * �̹��� ��ư�� �������Դϴ�.
	 * @param imageName ��ư �̹���
	 * @param hoverImageName ���콺 ���� �� ��ư �̹���
	 */
	public ImageButton(String imageName, String hoverImageName) {
		image = new ImageIcon(imageName);
		hoverImage = new ImageIcon(hoverImageName);
		constructor();
	}
	/**
	 * �̹��� ��ư�� �������Դϴ�.
	 * @param imageName ��ư �̹���
	 * @param hoverImageName ���콺 ���� �� ��ư �̹���
	 * @param x ��ư�� ��ġ X
	 * @param y ��ư�� ��ġ Y
	 */
	public ImageButton(String imageName, String hoverImageName, int x, int y) {
		iX = x;
		iY = y;
		
		image = new ImageIcon(imageName);
		hoverImage = new ImageIcon(hoverImageName);
		constructor();
	}
	
	/**
	 * ��ư�� ���� �� �ʱⰪ ������ ���� �����ϴ� �޼ҵ��Դϴ�.
	 */
	private void constructor() {
		imageButtonL = new ImageButtonListener();

		this.setContentAreaFilled(false); // ��ư ������ ����
		this.setBorderPainted(false); // ��ư �׵θ� ����
		this.setIcon(image); // �̹��� ����
		this.setSize(new Dimension(image.getIconWidth(), image.getIconHeight())); // ũ�� ����
		this.setLocation(iX, iY); // ��ġ ����
		this.addMouseListener(imageButtonL);
	}
	
	/**
	 * �̹��� ��ư�� ���콺 �̺�Ʈ �������Դϴ�.
	 */
	private class ImageButtonListener implements MouseListener
	{
		public void mouseEntered(MouseEvent event) {
			// �̹��� ����
			if (hoverImage == null)
				return;
			((JButton)event.getSource()).setIcon(hoverImage);
			
			// ũ�� �缳��
			setSize(new Dimension(hoverImage.getIconWidth(), hoverImage.getIconHeight()));
			
			// ��ġ �缳��
			setLocation(iX - (hoverImage.getIconWidth() - image.getIconWidth())/2,
						iY - (hoverImage.getIconHeight() - image.getIconHeight())/2);
		}
		
		public void mouseExited(MouseEvent event) {
			// �̹��� ����
			if (image == null)
				return;
			((JButton)event.getSource()).setIcon(image);
			
			// ũ�� �缳��
			setSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
			
			// ��ġ �缳��
			setLocation(iX, iY);
		}
		
		public void mouseClicked(MouseEvent event) { }
		public void mousePressed(MouseEvent event) { }
		public void mouseReleased(MouseEvent event) { }
	}
}
