package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;

public class RefrigeratorScene extends SceneAbst
{
	private JButton btnCart;
	private ImageIcon imgCart;
	private RefrigeratorListener refL;
	//private Image cart;
	
	public void onShow() {
		refL = new RefrigeratorListener();
		
		// CartScene���� �Ѿ�� ��ư �߰�
		imgCart = new ImageIcon("./images/cart.png");
		btnCart = new JButton();
		btnCart.setIcon(imgCart);
		btnCart.setBounds(650,290,470,350);
		btnCart.addActionListener(refL);
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
