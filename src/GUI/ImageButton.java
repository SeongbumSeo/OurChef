package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Main.*;

public class ImageButton extends JButton
{
	private ImageIcon image; // 버튼 이미지
	private ImageIcon hoverImage; // 마우스 오버 시 버튼 이미지
	private int iX, iY; // 버튼의 위치
	private boolean onSound;
	
	private ImageButtonListener imageButtonL;
	
	/**
	 * 이미지 버튼의 생성자입니다.
	 * @param imageName 버튼 이미지
	 */
	public ImageButton(String imageName) {
		image = new ImageIcon(imageName);
		hoverImage = null;
		onSound = false;
		constructor();
	}
	/**
	 * 이미지 버튼의 생성자입니다.
	 * @param imageName 버튼 이미지
	 * @param x 버튼의 위치 X
	 * @param y 버튼의 위치 Y
	 */
	public ImageButton(String imageName, int x, int y) {
		iX = x;
		iY = y;
		
		image = new ImageIcon(imageName);
		hoverImage = null;
		constructor();
	}
	
	/**
	 * 이미지 버튼의 생성자입니다.
	 * @param imageName 버튼 이미지
	 * @param hoverImageName 마우스 오버 시 버튼 이미지
	 */
	public ImageButton(String imageName, String hoverImageName) {
		image = new ImageIcon(imageName);
		hoverImage = new ImageIcon(hoverImageName);
		onSound = true;
		constructor();
	}
	/**
	 * 이미지 버튼의 생성자입니다.
	 * @param imageName 버튼 이미지
	 * @param hoverImageName 마우스 오버 시 버튼 이미지
	 * @param x 버튼의 위치 X
	 * @param y 버튼의 위치 Y
	 */
	public ImageButton(String imageName, String hoverImageName, int x, int y) {
		iX = x;
		iY = y;
		
		image = new ImageIcon(imageName);
		hoverImage = new ImageIcon(hoverImageName);
		onSound = true;
		constructor();
	}
	
	public void SoundOff() {
		onSound = false;
	}
	public void SoundOn() {
		onSound = true;
	}
	
	
	/**
	 * 버튼의 생성 및 초기값 설정에 직접 관여하는 메소드입니다.
	 */
	private void constructor() {
		imageButtonL = new ImageButtonListener();

		this.setContentAreaFilled(false); // 버튼 바탕색 제거
		this.setBorderPainted(false); // 버튼 테두리 제거
		this.setIcon(image); // 이미지 적용
		this.setSize(new Dimension(image.getIconWidth(), image.getIconHeight())); // 크기 설정
		this.setLocation(iX, iY); // 위치 설정
		this.addMouseListener(imageButtonL);
	}
	
	/**
	 * 이미지 버튼의 마우스 이벤트 리스너입니다.
	 */
	private class ImageButtonListener implements MouseListener
	{
		public void mouseEntered(MouseEvent event) {
			// 이미지 변경
			if (hoverImage == null)
				return;
			((JButton)event.getSource()).setIcon(hoverImage);
			
			// 효과음
			if (onSound == true) {
				SoundManager onButton = new SoundManager("./sounds/onButton.mp3", false);
				onButton.start();
			}
			
			// 크기 재설정
			setSize(new Dimension(hoverImage.getIconWidth(), hoverImage.getIconHeight()));
			
			// 위치 재설정
			setLocation(iX - (hoverImage.getIconWidth() - image.getIconWidth())/2,
						iY - (hoverImage.getIconHeight() - image.getIconHeight())/2);
		}
		
		public void mouseExited(MouseEvent event) {
			// 이미지 변경
			if (image == null || hoverImage == null)
				return;
			((JButton)event.getSource()).setIcon(image);
			
			// 크기 재설정
			setSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
			
			// 위치 재설정
			setLocation(iX, iY);
		}
		
		public void mouseClicked(MouseEvent event) { }
		public void mousePressed(MouseEvent event) { }
		public void mouseReleased(MouseEvent event) { }
	}
}
