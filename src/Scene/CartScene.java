package Scene;

import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;

import Main.*;
import Scene.*;
import GUI.*;
import Data.*;

public class CartScene extends SceneAbst
{	
	// 패널 임시
	private JPanel ing;
	
	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;
	
	// 재료
	private JPanel ingredients;
	private ImageIcon imgContainer;
	
	// 버튼
	private ImageButton btnTrash, btnRecipe, btnFavorite;
	
	// 뒤로가기 및 홈버튼
	private ImageButton btnGoBack, btnGoHome;
	
	// 이벤트
	private CartListener CartL;
	private IngredientButtonListener ingButtonL;
	
	private static final int[] ITEM_SIZE = { 100, 100 };
	private JPanel pnlIngredients;
	private JScrollPane pnlIngredientsScroll;
	private JLabel lblIngre;
	private HashMap<JButton, Ingredient> ingButtonMap;
	private List<Ingredient> cart;
	private List<Ingredient> selected;
	
	public void onShow() {
		// 카트 정보 가져오기
		cart = Main.getCart();
		selected = new ArrayList<Ingredient>();
		
		CartL = new CartListener();
		ingButtonL = new IngredientButtonListener();
		
		pnlIngredients = new JPanel();
		pnlIngredients.setLayout(new GridLayout(0, 1));
		pnlIngredients.setOpaque(false);
		pnlIngredientsScroll = new JScrollPane(pnlIngredients);
		pnlIngredientsScroll.setBounds(1150, 210, 200, 400);
		pnlIngredientsScroll.getViewport().setOpaque(false);
		pnlIngredientsScroll.setOpaque(false);
		pnlIngredientsScroll.setBorder(null);
		add(pnlIngredientsScroll);
		
		// 카트 버튼들 추가
		ingButtonMap = new HashMap<JButton, Ingredient>();
		for (Ingredient item : cart) {
			// 아이콘
			ImageIcon icon = new ImageIcon(item.getIcon());
			Image image = icon.getImage();
			image = image.getScaledInstance((int)((float)icon.getIconWidth()/icon.getIconHeight()*ITEM_SIZE[1]), ITEM_SIZE[1], java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
			
			// 재료 버튼
			JButton btn = new JButton();
			btn.setIcon(icon);
			btn.setPreferredSize(new Dimension(100, 140));
			btn.setContentAreaFilled(false); // 버튼 바탕색 제거
			btn.setBorderPainted(false); // 버튼 테두리 제거
			btn.addActionListener(ingButtonL);
			pnlIngredients.add(btn);
			
			ingButtonMap.put(btn, item);
		}
		
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
		btnTrash = new ImageButton("images/trashCan.png", "images/trashCan_h2.png", 1080, 660);
		btnTrash.addActionListener(CartL);
		add(btnTrash);
		
		// favorite button
		btnFavorite = new ImageButton("images/knife.png","images/knife_h.png",1180,665);
		btnFavorite.addActionListener(CartL);
		add(btnFavorite);
		
		// recipe button
		btnRecipe = new ImageButton("images/spatula.png", "images/spatula_h2.png", 1270, 685);
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
				for (Map.Entry<JButton, Ingredient> item : ingButtonMap.entrySet())
					if (selected.contains(item.getValue()))
						pnlIngredients.remove(item.getKey());
				pnlIngredients.repaint();
				
				cart.removeAll(selected);
				selected.clear();
			} else if (obj == btnRecipe) { // 레시피 버튼 클릭
				// RecipeListScene으로 이동
				Main.switchScene(new RecipeListScene());
			} else if (obj == btnGoBack) {
				Main.switchScene(new RefrigeratorScene());
			} else if (obj == btnGoHome) {
				Main.switchScene(new IntroScene());
			}
		}
	}
	
	private class IngredientButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			Ingredient ing = ingButtonMap.get(obj); // 선택한 재료
			
			if (!selected.contains(ing)) {
				selected.add(ing);
				System.out.println("카트 내 재료 선택: " + ing.getName());
			} else {
				selected.remove(ing);
				System.out.println("카트 내 재료 선택 취소: " + ing.getName());
			}
		}
	}
}
