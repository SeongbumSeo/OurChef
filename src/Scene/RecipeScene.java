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
	
	// 슬라이드
	private JPanel pnlSlider;
	private JButton btnPrevious, btnNext;
	private JLabel lblImage, lblText;
	private Recipe currentRecipe;
	private Slide currentSlide;

	public void onShow() {
		// 카트의 재료들로 만들 수 있는 레시피들 탐색
		List<Recipe> recipes = Recipe.searchRecipes(DataManager.getRecipes(), DataManager.getCart());
		
		RecipeL = new RecipeListener();
		recButtonL = new RecipeButtonListener();
		
		createSlider();
		createRecipePanel(recipes);
		
		// 뒤로가기 버튼 및 홈버튼
		btnGoBack = new ImageButton("images/buttons/goBack.png", 7, 810);
		btnGoBack.setLayout(null);
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setBorderPainted(false);
		btnGoBack.addActionListener(RecipeL);
		add(btnGoBack);

		btnGoHome = new ImageButton("images/buttons/goHome.png", 20, 25);
		btnGoHome.setLayout(null);
		btnGoHome.setContentAreaFilled(false);
		btnGoHome.setBorderPainted(false);
		btnGoHome.addActionListener(RecipeL);
		add(btnGoHome);

		// 물꼬기
		fish = new ImageButton("images/recipeScene/fish.png", 650, 500);
		fish.setLayout(null);
		fish.setContentAreaFilled(false);
		fish.setBorderPainted(false);
		fish.addActionListener(RecipeL);
		add(fish);

		// 배경
		imgBackground = new ImageIcon("./images/recipeScene/recipeListBackground2.png");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		lblBackground.setLayout(null);
		add(lblBackground);
	}

	public void onHide() {

	}
	
	private void showSlide(Slide slide) {
		lblImage.setIcon(getSlideImage(slide.getImage()));
		lblText.setText("<html><center>" + slide.getText() + "</center></html>");
		pnlSlider.setVisible(true);
		currentSlide = slide;
	}
	
	private ImageIcon getSlideImage(String path) {
		ImageIcon icon = new ImageIcon(path);
		Image image = icon.getImage();
		image = image.getScaledInstance(600, 338, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	private void createSlider() {
		pnlSlider = new JPanel();
		pnlSlider.setBounds(700, 200, 770, 486);
		pnlSlider.setOpaque(false);
		pnlSlider.setLayout(null);
		pnlSlider.setVisible(false);
		add(pnlSlider);
		
		// 이미지
		lblImage = new JLabel();
		lblImage.setBounds(385-300, 20, 600, 338);
		pnlSlider.add(lblImage);
		
		// 텍스트
		lblText = new JLabel();
		lblText.setBounds(385-300, 486-100-15, 600, 100);
		lblText.setFont(new Font(lblText.getFont().getFontName(), Font.PLAIN, 20));
		lblText.setVerticalAlignment(SwingConstants.TOP);
		lblText.setLayout(null);
		pnlSlider.add(lblText);
		
		// 뒤로 버튼
		btnPrevious = new JButton(new ImageIcon("./images/recipeScene/slider_previous.png"));
		btnPrevious.setBounds(20, 486-46-15, 46, 46);
		btnPrevious.setContentAreaFilled(false);
		btnPrevious.setBorderPainted(false);
		btnPrevious.addActionListener(RecipeL);
		pnlSlider.add(btnPrevious);
		
		// 앞으로 버튼
		btnNext= new JButton(new ImageIcon("./images/recipeScene/slider_next.png"));
		btnNext.setBounds(770-46-20, 486-46-15, 46, 46);
		btnNext.setContentAreaFilled(false);
		btnNext.setBorderPainted(false);
		btnNext.addActionListener(RecipeL);
		pnlSlider.add(btnNext);
		
		// 배경
		ImageIcon iconBackground = new ImageIcon("./images/recipeScene/slider_background.png");
		Image imgBackground = iconBackground.getImage();
		imgBackground = imgBackground.getScaledInstance(770, 486, java.awt.Image.SCALE_SMOOTH);
		iconBackground = new ImageIcon(imgBackground);
		JLabel lblBackground = new JLabel(iconBackground);
		lblBackground.setBounds(0, 0, 770, 486);
		pnlSlider.add(lblBackground);
	}
	
	private void createRecipePanel(List<Recipe> recipes) {
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
	}

	private class RecipeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if (obj == btnGoBack) {
				// 효과음
				SoundManager onButton = new SoundManager("./sounds/click.mp3", false);
				onButton.start();
				SceneManager.switchScene(new CartScene());
			} else if (obj == btnGoHome) {
				// 효과음
				SoundManager onButton = new SoundManager("./sounds/click.mp3", false);
				onButton.start();
				SceneManager.switchScene(new IntroScene());
			} else if (obj == btnPrevious) {
				int index = currentRecipe.getSlides().indexOf(currentSlide);
				if (index == 0)
					return;
				
				showSlide(currentRecipe.getSlides().get(index-1));
			} else if (obj == btnNext) {
				int index = currentRecipe.getSlides().lastIndexOf(currentSlide);
				if (index >= currentRecipe.getSlides().size()-1)
					return;
				
				showSlide(currentRecipe.getSlides().get(index+1));
			}
		}
	}
	
	private class RecipeButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			currentRecipe = recButtonMap.get(obj);
			
			showSlide(currentRecipe.getSlides().get(0));
		}
	}
}