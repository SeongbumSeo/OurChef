package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import GUI.*;

public class RefrigeratorScene extends SceneAbst
{
	// ���
	private ImageIcon imgBackground, imgRefrigerator;
	private JLabel lblBackground, lblRefrigerator;
	
	// īƮ
	private JButton btnCart;
	
	// �̺�Ʈ
	private RefrigeratorListener refL;
	
	public void onShow() {
		refL = new RefrigeratorListener();
		
		// CartScene���� �Ѿ�� ��ư �߰�
		btnCart = new ImageButton("images/cart.png", 870, 380);
		btnCart.addActionListener(refL);
		add(btnCart);
		
		
		// ���_�����
		imgRefrigerator = new ImageIcon("images/refrigerator.png");
		lblRefrigerator = new JLabel();
		lblRefrigerator.setIcon(imgRefrigerator);
		lblRefrigerator.setBounds(150, 35, imgRefrigerator.getIconWidth(), imgRefrigerator.getIconHeight() );
		add(lblRefrigerator);
		
		// ���
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
			
			if (obj == btnCart) { // ���� ��ư Ŭ��
				Main.switchScene(new CartScene());
			}
		}
	}
}
