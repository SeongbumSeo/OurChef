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

public class CartScene extends SceneAbstract
{
	// 상수
	private static final int[] ITEM_SIZE = { 100, 100 };
	
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
	private ImageListener imgL;
	
	private JPanel pnlIngredients;
	private JScrollPane pnlIngredientsScroll;
	private JLabel lblIngre;
	private HashMap<JButton, Ingredient> ingButtonMap;
	private List<Ingredient> cart;
	private List<Ingredient> selected;
	
	public void onShow() {
		// 카트 정보 가져오기
		cart = DataManager.getCart();
		selected = new ArrayList<Ingredient>();
		
		CartL = new CartListener();
		ingButtonL = new IngredientButtonListener();
		imgL = new ImageListener();
		
		pnlIngredients = new JPanel();
		pnlIngredients.setLayout(new GridLayout(0, 1));
		pnlIngredients.setOpaque(false);
		pnlIngredientsScroll = new JScrollPane(pnlIngredients);
		pnlIngredientsScroll.setBounds(1150, 210, 200, 420);
		pnlIngredientsScroll.getViewport().setOpaque(false);
		pnlIngredientsScroll.setOpaque(false);
		pnlIngredientsScroll.setBorder(null);
		add(pnlIngredientsScroll);
		
		// 선택된 재료가 없는 경우				
		if (cart.size() == 0) {
			JLabel lblNone = new JLabel("<html><center>There are no ingredients!</center></html>");
			lblNone.setFont(new Font("./fonts/JejuGothic.ttf", Font.PLAIN, 40));
			lblNone.setHorizontalAlignment(SwingConstants.CENTER);
			lblNone.setPreferredSize(new Dimension(200, 400));
			pnlIngredients.add(lblNone);
		}
		
		// 카트 버튼들 추가
		ingButtonMap = new HashMap<JButton, Ingredient>();
		for (Ingredient item : cart) {
			// 아이콘
			ImageIcon icon = getIngredientIcon(item.getIcon());
			
			// 재료 버튼
			JButton btn = new JButton();
			btn.setIcon(icon);
			btn.setPreferredSize(new Dimension(100, 140));
			btn.setContentAreaFilled(false); // 버튼 바탕색 제거
			btn.setBorderPainted(false); // 버튼 테두리 제거
			btn.addActionListener(ingButtonL);
			btn.addMouseListener(imgL);
			pnlIngredients.add(btn);
			
			ingButtonMap.put(btn, item);
		}
		
		// 뒤로가기 버튼 및 홈버튼
	    btnGoBack = new ImageButton("images/buttons/goBack.png", 7, 810);
	    btnGoBack.setLayout(null);
	    btnGoBack.setContentAreaFilled(false);
	    btnGoBack.setBorderPainted(false);
	    btnGoBack.addActionListener(CartL);
	    add(btnGoBack);
	      
	    btnGoHome = new ImageButton("images/buttons/goHome.png", 20, 25);
	    btnGoHome.setLayout(null);
	    btnGoHome.setContentAreaFilled(false);
	    btnGoHome.setBorderPainted(false);
	    btnGoHome.addActionListener(CartL);
	    add(btnGoHome);
		
		// trash button
		btnTrash = new ImageButton("images/cartScene/trashCan.png", "images/cartScene/trashCan_h2.png", 1100, 665);
		btnTrash.addActionListener(CartL);
		add(btnTrash);
		
		// favorite button
		/*btnFavorite = new ImageButton("images/knife.png","images/knife_h.png",1180,665);
		btnFavorite.addActionListener(CartL);
		add(btnFavorite);*/
		
		// recipe button
		btnRecipe = new ImageButton("images/cartScene/spatula.png", "images/cartScene/spatula_h2.png", 1230, 680);
		btnRecipe.addActionListener(CartL);
		add(btnRecipe);
		
		// 배경
		imgBackground = new ImageIcon("./images/cartScene/cartBackground.png");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);
	}
		
	public void onHide() {
			
	}
	
	private ImageIcon getIngredientIcon(String iconPath) {
		ImageIcon icon = new ImageIcon(iconPath);
		Image image = icon.getImage();			
		image = image.getScaledInstance((int)((float)icon.getIconWidth()/icon.getIconHeight()*ITEM_SIZE[1]), ITEM_SIZE[1], java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	private void changeBlack(JButton button, Ingredient ing) {
		// 아이콘
		ImageIcon icon = getIngredientIcon(ing.getIconBlack());
		// 아이콘 적용
		button.setIcon(icon);
	}
	
	private void changeLight(JButton button, Ingredient ing) {
		// 아이콘
		ImageIcon icon = getIngredientIcon(ing.getIconLight());
		// 아이콘 적용
		button.setIcon(icon);
		
	}
	
	private void changeOriginal(JButton button, Ingredient ing) {
		// 아이콘
		ImageIcon icon = getIngredientIcon(ing.getIcon());
		// 아이콘 적용
		button.setIcon(icon);
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
				SceneManager.switchScene(new RecipeScene());
			} else if (obj == btnGoBack) {
				// 효과음
				SoundManager onButton = new SoundManager("./sounds/click.mp3", false);
				onButton.start();
				SceneManager.switchScene(new RefrigeratorScene());
				
			} else if (obj == btnGoHome) {
				// 효과음
				SoundManager onButton = new SoundManager("./sounds/click.mp3", false);
				onButton.start();
				SceneManager.switchScene(new IntroScene());
			}
		}
	}
	
	private class IngredientButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			Ingredient ing = ingButtonMap.get(obj); // 선택한 재료
			
			if (!selected.contains(ing)) {
				// 카트 내 재료 선택
				selected.add(ing);
				changeBlack((JButton)obj, ing);
				
				// 효과음
				SoundManager onButton = new SoundManager("./sounds/cancelIngre.mp3", false);
				onButton.start();
			} else {
				// 카트 내 재료 선택 취소
				selected.remove(ing);
				changeOriginal((JButton)obj, ing);
				
				// 효과음
				SoundManager onButton = new SoundManager("./sounds/selectIngre.mp3", false);
				onButton.start();
			}
		}
	}
	
	private class ImageListener implements MouseListener
	{
		// 마우스가 컴포넌트 안으로 들어온 상태
		public void mouseEntered(MouseEvent event) {
			Object obj = event.getSource();
			Ingredient ing = ingButtonMap.get(obj); // 선택한 재료
			
			if (!selected.contains(ing)) { // 선택되지 않은 상태라면
				changeLight((JButton)obj, ing);
			}
		}
				
		// 마우스가 컴포넌트 밖으로 나간 상태
		public void mouseExited(MouseEvent event) {
			Object obj = event.getSource();
			Ingredient ing = ingButtonMap.get(obj); // 선택한 재료
			
			if (!selected.contains(ing)) { // 선택되지 않은 상태라면
				changeOriginal((JButton)obj, ing);
			}
		}

		public void mouseClicked(MouseEvent event) { } 
		public void mousePressed(MouseEvent event) { }
		public void mouseReleased(MouseEvent event) { }
	}
}
