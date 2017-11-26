package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import GUI.*;

public class CartScene extends SceneAbst
{
	// ���
	private ImageIcon imgBackground;
	private JLabel lblBackground;
	
	// ���
	private JPanel ingredients;
	private ImageIcon imgContainer;
	
	// ��ư
	private JButton btnTrash, btnRecipe;
	
	// �̺�Ʈ
	private CartListener CartL;
	
	public void onShow() {
		CartL = new CartListener();
		
		// ingredients
		ingredients = new JPanel();
		ingredients.setOpaque(false);
		ingredients.setLocation(1100, 50);
		
		add(ingredients);
		
		// trash button
		btnTrash = new ImageButton("images/trash.png");
		btnTrash.setBounds(1000, 550, 70, 70);
		btnTrash.addActionListener(CartL);
		add(btnTrash);
		
		// recipe button
		btnRecipe = new ImageButton("images/recipe.png");
		btnRecipe.setBounds(1070, 550, 70, 70);
		btnRecipe.addActionListener(CartL);
		add(btnRecipe);
		
		// ���
		imgBackground = new ImageIcon("./images/cartBackground.jpg");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);
	}
		
	public void onHide() {
			
	}
	
	
	private class CartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if (obj == btnTrash) { // �������� ��ư Ŭ��
				// ���õ� ���� ����
				
			} else if (obj == btnRecipe) { // ������ ��ư Ŭ��
				// RecipeListScene���� �̵�
				Main.switchScene(new RecipeListScene());
			}
		}
	}
}
