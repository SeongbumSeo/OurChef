package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Main.Main;
import Scene.*;
import GUI.*;

public class CartScene extends SceneAbst
{
	private JButton btnTrash;
	private JButton btnRecipe;
	private JPanel ingredients;
	private CartListener CartL;
	
	public void onShow() {
		CartL = new CartListener();
		
		ingredients = new JPanel();
		ingredients.setBounds(1000, 50, 140, 500);
		ingredients.setBorder(new LineBorder(Color.black, 1));
		ingredients.setBackground(Color.white);
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
