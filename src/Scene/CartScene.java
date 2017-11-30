package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import GUI.*;

public class CartScene extends SceneAbst
{
	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;
	
	// 재료
	private JPanel ingredients;
	private ImageIcon imgContainer;
	
	// 버튼
	private JButton btnTrash, btnRecipe;
	
	// 뒤로가기 및 홈버튼
	private ImageButton btnGoBack, btnGoHome;
	
	// 이벤트
	private CartListener CartL;
	
	public void onShow() {
		CartL = new CartListener();
		
		// ingredients
		ingredients = new JPanel();
		ingredients.setOpaque(false);
		ingredients.setLocation(1100, 50);
		
		add(ingredients);
		
		// 뒤로가기 버튼 및 홈버튼
	    btnGoBack = new ImageButton("images/goBack.png", 7, 810);
	    btnGoBack.setLayout(null);
	    btnGoBack.setContentAreaFilled(false);
	    btnGoBack.setBorderPainted(false);
	    btnGoBack.addActionListener(CartL);
	    add(btnGoBack);
	      
	    btnGoHome = new ImageButton("images/goHome.png", 20, 25);
	    btnGoHome.setLayout(null);
	    btnGoHome.setContentAreaFilled(false);
	    btnGoHome.setBorderPainted(false);
	    btnGoHome.addActionListener(CartL);
	    add(btnGoHome);
		
		// trash button
		btnTrash = new ImageButton("images/trashCan.png", "images/trashCan_h.png", 1090, 660);
		btnTrash.addActionListener(CartL);
		add(btnTrash);
		
		// recipe button
		btnRecipe = new ImageButton("images/spatula.png", "images/spatula_h.png", 1220, 660);
		btnRecipe.addActionListener(CartL);
		add(btnRecipe);
		
		// 배경
		imgBackground = new ImageIcon("./images/cartBackground.png");
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
			
			if (obj == btnTrash) { // 쓰레기통 버튼 클릭
				// 선택된 재료들 삭제
				
			} else if (obj == btnRecipe) { // 레시피 버튼 클릭
				// RecipeListScene으로 이동
				Main.switchScene(new RecipeListScene());
			} else if (obj == btnGoBack) {
				
			} else if (obj == btnGoHome) {
				
			}
		}
	}
}
