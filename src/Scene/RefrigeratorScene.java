package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;

public class RefrigeratorScene extends SceneAbst
{
	// ���
	private ImageIcon imgBackground, imgRefrigerator;
	private JLabel lblBackground, lblRefrigerator;
	
	// īƮ
	private JButton btnCart;
	private ImageIcon imgCart;
	
	// �̺�Ʈ
	private RefrigeratorListener refL;
	
	public void onShow() {
		refL = new RefrigeratorListener();
		
		// CartScene���� �Ѿ�� ��ư �߰�
		imgCart = new ImageIcon("images/cart.png");
		btnCart = new JButton();
		btnCart.setIcon(imgCart);
		btnCart.setBounds(650,290,470,350);
		btnCart.setContentAreaFilled(false); // ��ư ������ ����
		btnCart.setBorderPainted(false); // ��ư �׵θ� ����
		btnCart.addActionListener(refL);
		add(btnCart);
		
		// ���_�����
		imgRefrigerator = new ImageIcon("images/refrigerator.png");
		lblRefrigerator = new JLabel();
		lblRefrigerator.setIcon(imgRefrigerator);
		lblRefrigerator.setBounds(50, 50, 224, 487);
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
