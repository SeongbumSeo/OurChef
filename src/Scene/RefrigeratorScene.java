package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import GUI.*;

public class RefrigeratorScene extends SceneAbst
{
	// 배경
	private ImageIcon imgBackground, imgRefrigerator;
	private JLabel lblBackground, lblRefrigerator;
	
	// 카트
	private JButton btnCart;
	
	// 이벤트
	private RefrigeratorListener refL;
	
	public void onShow() {
		refL = new RefrigeratorListener();
		
		// CartScene으로 넘어가는 버튼 추가
		btnCart = new ImageButton("images/cart.png", 870, 380);
		btnCart.addActionListener(refL);
		add(btnCart);
		
		
		// 배경_냉장고
		imgRefrigerator = new ImageIcon("images/refrigerator.png");
		lblRefrigerator = new JLabel();
		lblRefrigerator.setIcon(imgRefrigerator);
		lblRefrigerator.setBounds(150, 35, imgRefrigerator.getIconWidth(), imgRefrigerator.getIconHeight() );
		add(lblRefrigerator);
		
		// 배경
		imgBackground = new ImageIcon("images/background.jpg");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);
		
	}
	
	public void onHide() {
		
	}
	
	private class RefrigeratorListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if (obj == btnCart) { // 시작 버튼 클릭
				Main.switchScene(new CartScene());
			}
		}
	}
}
