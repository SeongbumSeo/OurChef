package Scene;

import java.awt.event.*;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;
import Data.*;
import Main.*;
import Scene.*;
import GUI.*;

public class RecipeListScene extends SceneAbst
{
	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;

	// 뒤로가기 및 홈버튼
	private ImageButton btnGoBack, btnGoHome;

	// 이벤트
	private RecipeListener RecipeL;

	// 아저씨
	private ImageButton fish;

	private JScrollPane pnlRecipesScroll;
	private JPanel pnlRecipes;

	// JButton b1,b2,b3,b4,b5,b6;

	// 레시피
	private JPanel pnlItem;
	private ImageIcon IconDish, IconDish2;
	private ImageIcon IconStar, IconStar2;
	private JLabel lblDish, lblName, lblStar;

	private JButton[] btnRecipe;

	public void onShow() {
		RecipeL = new RecipeListener();

		pnlRecipes = new JPanel();
		pnlRecipes.setLayout(new GridLayout(0, 1));
		pnlRecipes.setOpaque(false);
		pnlRecipesScroll = new JScrollPane(pnlRecipes);
		pnlRecipesScroll.setBounds(133, 150, 1360, 250);
		pnlRecipesScroll.getViewport().setOpaque(false);
		pnlRecipesScroll.setOpaque(false);
		pnlRecipesScroll.setBorder(null);
		add(pnlRecipesScroll);

		// 레시피 버튼들 추가
		btnRecipe = new JButton[Main.getRecipes().size()];
		Iterator<Recipe> itr = Main.getRecipes().iterator();
		for (int i = 0; itr.hasNext(); i++) {
			Recipe item = itr.next();

			// 레시피 버튼
			btnRecipe[i] = new JButton();
			btnRecipe[i].setPreferredSize(new Dimension(530, 200));// 안먹음
			btnRecipe[i].setContentAreaFilled(false); // 버튼 바탕색 제거
			btnRecipe[i].setBorderPainted(false); // 버튼 테두리 제거
			pnlRecipes.add(btnRecipe[i]);

			// 레시피 버튼 내부의 패널
			pnlItem = new JPanel();
			pnlItem.setLayout(null);
			pnlItem.setBounds(155, 500, 500, 180);
			pnlItem.setBorder(BorderFactory.createLineBorder(new Color(166, 166, 166)));
			pnlItem.setOpaque(false);

			// 아이콘
			IconDish = new ImageIcon(item.getIcon());
			Image imgDish = IconDish.getImage();
			imgDish = imgDish.getScaledInstance(240, 135, java.awt.Image.SCALE_SMOOTH);
			IconDish2 = new ImageIcon(imgDish);
			lblDish = new JLabel();
			lblDish.setIcon(IconDish2);
			lblDish.setLayout(null);
			lblDish.setBounds(20, 23, 240, 135);
			lblDish.setOpaque(false);

			// 이름
			lblName = new JLabel(item.getName());
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setLayout(null);
			lblName.setBounds(270, 30, 210, 40);

			// 난이도 별(star) 이미지
			IconStar = new ImageIcon("./images/star/" + item.getLevel() + ".png");
			Image imgStar = IconStar.getImage();
			imgStar = imgStar.getScaledInstance(210, 38, java.awt.Image.SCALE_SMOOTH);
			IconStar2 = new ImageIcon(imgStar);
			lblStar = new JLabel();
			lblStar.setIcon(IconStar2);
			lblStar.setLayout(null);
			lblStar.setBounds(285, 100, 210, 40);

			pnlItem.add(lblStar);
			pnlItem.add(lblName);
			pnlItem.add(lblDish);
			btnRecipe[i].add(pnlItem);
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
				Main.switchScene(new CartScene());
			} else if (obj == btnGoHome) {
				Main.switchScene(new IntroScene());
			}
		}
	}
}