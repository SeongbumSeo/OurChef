package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;

public class RefrigeratorScene extends SceneAbst
{
	private JButton btnCart;
	private RefrigeratorListener RefL;
	private ImageIcon cart;
	//private Image cart;
	
	public void onShow() {
		RefL = new RefrigeratorListener();
		
		// CartScene���� �Ѿ�� ��ư �߰�
		cart = new ImageIcon("./images/cart.png");
		btnCart = new JButton();
		btnCart.setIcon(cart);
		btnCart.setBounds(650,290,470,350);
		btnCart.addActionListener(RefL);
		add(btnCart);
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
