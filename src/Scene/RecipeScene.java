package Scene;

import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import Data.*;
import Main.*;
import Scene.*;
import GUI.*;

public class RecipeScene extends SceneAbstract
{
	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;

	// 뒤로가기 및 홈버튼
	private ImageButton btnGoBack, btnGoHome;

	// 이벤트
	private RecipeListener RecipeL;
	private RecipeButtonListener recButtonL;

	// 아저씨
	private ImageButton fish;

	private JScrollPane pnlRecipesScroll;
	private JPanel pnlRecipes;

	// 레시피
	private JPanel pnlItem;
	private ImageIcon IconDish, IconDish2;
	private ImageIcon IconStar, IconStar2;
	private JLabel lblDish, lblName, lblStar;

	private HashMap<JButton, Recipe> recButtonMap;

	public void onShow() {
		// 카트의 재료들로 만들 수 있는 레시피들 탐색
		List<Recipe> recipes = Recipe.searchRecipes(DataManager.getRecipes(), DataManager.getCart());
		
		RecipeL = new RecipeListener();
		recButtonL = new RecipeButtonListener();

		pnlRecipes = new JPanel();
		pnlRecipes.setLayout(new GridLayout(0, 1));
		pnlRecipes.setOpaque(false);
		pnlRecipesScroll = new JScrollPane(pnlRecipes);
		pnlRecipesScroll.setBounds(133, 107, 500, 685);
		pnlRecipesScroll.getViewport().setOpaque(false);
		pnlRecipesScroll.setOpaque(false);
		pnlRecipesScroll.setBorder(null);
		add(pnlRecipesScroll);

		// 레시피 버튼들 추가
		recButtonMap = new HashMap<JButton, Recipe>();
		for (Recipe item : recipes) {
			// 레시피 버튼
			JButton button = new JButton();
			button.setPreferredSize(new Dimension(480, 160));
			button.setContentAreaFilled(false); // 버튼 바탕색 제거
			button.setBorderPainted(false); // 버튼 테두리 제거
			button.addActionListener(recButtonL);
			pnlRecipes.add(button);

			// 레시피 버튼 내부의 패널
			pnlItem = new JPanel();
			pnlItem.setLayout(null);
			pnlItem.setBounds(155, 500, 500, 160);
			pnlItem.setBorder(BorderFactory.createLineBorder(new Color(166, 166, 166)));
			pnlItem.setOpaque(false);

			// 아이콘
			IconDish = new ImageIcon(item.getIcon());
			Image imgDish = IconDish.getImage();
			imgDish = imgDish.getScaledInstance(170, 100, java.awt.Image.SCALE_SMOOTH);
			IconDish2 = new ImageIcon(imgDish);
			lblDish = new JLabel();
			lblDish.setIcon(IconDish2);
			lblDish.setLayout(null);
			lblDish.setBounds(20, 10, 240, 135);
			lblDish.setOpaque(false);

			// 이름
			lblName = new JLabel("<html>" + item.getName() + "</html>");
			lblName.setBounds(200, 15, 250, 50);
			lblName.setFont(new Font(lblName.getFont().getFontName(), lblName.getFont().getStyle(), 20));
			lblName.setVerticalAlignment(SwingConstants.TOP);
			lblName.setLayout(null);

			// 난이도 별(star) 이미지
			IconStar = new ImageIcon("./images/star/" + item.getLevel() + ".png");
			Image imgStar = IconStar.getImage();
			imgStar = imgStar.getScaledInstance(210, 38, java.awt.Image.SCALE_SMOOTH);
			IconStar2 = new ImageIcon(imgStar);
			lblStar = new JLabel();
			lblStar.setIcon(IconStar2);
			lblStar.setLayout(null);
			lblStar.setBounds(200, 70, 210, 40);

			pnlItem.add(lblStar);
			pnlItem.add(lblName);
			pnlItem.add(lblDish);
			button.add(pnlItem);
			
			recButtonMap.put(button, item);
		}
		
		// 레시피가 없는 경우
		if (recipes.size() == 0) {
			JLabel lblNone = new JLabel("<html><center>There are no recipes with the selected ingredients!</center></html>");
			lblNone.setPreferredSize(new Dimension(480, 0));
			pnlRecipes.add(lblNone);
		}

		// 뒤로가기 버튼 및 홈버튼
		btnGoBack = new ImageButton("images/goBack.png", 7, 810);
		btnGoBack.setLayout(null);
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setBorderPainted(false);
		btnGoBack.addActionListener(RecipeL);
		add(btnGoBack);

		btnGoHome = new ImageButton("images/goHome.png", 20, 25);
		btnGoHome.setLayout(null);
		btnGoHome.setContentAreaFilled(false);
		btnGoHome.setBorderPainted(false);
		btnGoHome.addActionListener(RecipeL);
		add(btnGoHome);

		// 물꼬기
		fish = new ImageButton("images/fish.png", "images/fish.png", 680, 500);
		fish.setLocation(1000, 500);
		fish.setLayout(null);
		fish.setContentAreaFilled(false);
		fish.setBorderPainted(false);
		fish.addActionListener(RecipeL);
		add(fish);

		// 배경
		imgBackground = new ImageIcon("./images/recipeListBackground2.png");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		lblBackground.setLayout(null);
		add(lblBackground);
	}

	public void onHide() {

	}

	private class RecipeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if (obj == btnGoBack) {
				SceneManager.switchScene(new CartScene());
			} else if (obj == btnGoHome) {
				SceneManager.switchScene(new IntroScene());
			}
		}
	}
	
	private class RecipeButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			Recipe rec = recButtonMap.get(obj);
			
			System.out.println(rec.getName());
		}
	}
}