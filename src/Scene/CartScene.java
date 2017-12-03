package Scene;

import java.awt.event.*;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;

import Data.Recipe;
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
	
	private static final int[] ITEM_SIZE = { 100, 100 };
	private JPanel pnlIngredients;
	private JScrollPane pnlIngredientsScroll;
	private JButton[] btnIngredients;
	private ImageIcon IconIngre;
	private JLabel lblIngre;
	
	public void onShow() {
		CartL = new CartListener();
		
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
		btnIngredients = new JButton[Main.getIngredients().size()];
		Iterator<Ingredient> itr = Main.getIngredients().iterator();
		for (int i = 0; itr.hasNext(); i++) {
			Ingredient item = itr.next();
			
			// 재료 버튼
			btnIngredients[i] = new JButton();
			btnIngredients[i].setPreferredSize(new Dimension(100, 100));
			btnIngredients[i].setContentAreaFilled(false); // 버튼 바탕색 제거
			btnIngredients[i].setBorderPainted(false); // 버튼 테두리 제거
			pnlIngredients.add(btnIngredients[i]);
			
			
			// 아이콘
			ImageIcon imgTmp = new ImageIcon(item.getIcon());
			Image temp = imgTmp.getImage();
			temp = temp.getScaledInstance((int)((float)imgTmp.getIconWidth()/imgTmp.getIconHeight()*ITEM_SIZE[1]), ITEM_SIZE[1], java.awt.Image.SCALE_SMOOTH);
			ImageIcon imgTmp2 = new ImageIcon(temp);
			
			lblIngre = new JLabel();
			lblIngre.setIcon(imgTmp2);
			lblIngre.setLayout(null);
			lblIngre.setBounds(0,0,imgTmp2.getIconWidth(),imgTmp2.getIconHeight());
			lblIngre.setHorizontalAlignment(SwingConstants.CENTER);
			lblIngre.setOpaque(false);
			
			pnlIngredients.add(lblIngre);
		}
		
		
		/*// 카트 안에 들어있는 목록들   
        ing = new JPanel();
        ing.setLayout(new GridLayout(12,1));
        ing.setOpaque(false);
                
        btnIngredients=new JButton[12];
        for(int i=0 ; i<12 ; i++) {
        	btnIngredients[i]=new JButton("1");
            btnIngredients[i].setBounds(30+30*i, 18, 45, 45);
            ing.add(btnIngredients[i]);
        }//1라인
                
        JScrollPane scrollPanel = new JScrollPane(ing);
        scrollPanel.setBounds(1200, 200, 100, 400);
        scrollPanel.getViewport().setOpaque(false);
        scrollPanel.setOpaque(false);
        scrollPanel.setBorder(null);
        add(scrollPanel); 
		
		// ingredients
		ingredients = new JPanel();
		ingredients.setOpaque(false);
		ingredients.setLocation(1100, 50);
		
		add(ingredients);*/
		
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
}
