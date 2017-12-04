package Scene;

import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.io.File;
import java.awt.*;
import javax.swing.*;
import Main.*;
import Scene.*;
import GUI.*;
import Data.*;

public class RefrigeratorScene extends SceneAbstract
{
	// 상수
	private static final int MAX_LINES = 6;
	private static final int[][] LINE_BOUNDS = {
			{ 300, 65, 450, 95 },
			{ 300, 215, 450, 95 },
			{ 330, 345, 410, 95 },
			{ 310, 490, 450, 95 },
			{ 330, 620, 430, 95 },
			{ 340, 770, 410, 95 }
	};
	private static final int[] ITEM_SIZE = { 77, 65 };

	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;

	// 뒤로가기 및 홈버튼
	private ImageButton btnGoBack, btnGoHome;

	// 카트
	private ImageButton btnCart;

	//검색된 버튼
	JButton[] btnSearchedIngredient; 

	// 검색
	private JTextField txtSearch;
	private ImageButton btnSearch;
	//검색으로 인한 패널
	private JPanel pnlSearch;
	// 검색 스크롤페인
	private JScrollPane pnlSearchScroll;

	
	// 각 라인의 패널
	private JPanel[] pnlLine;
	// 각 라인의 스크롤 페인
	private JScrollPane[] pnlLineScroll;
	
	// 재료 버튼
	private HashMap<JButton, Ingredient> ingButtonMap;
	private HashMap<JButton, Ingredient> ingSearchMap;
	
	// 애니메이션
	private Animation animCartMove;

	// 이벤트
	private RefrigeratorListener refL;
	private IngredientButtonListener ingButtonL;
	private SearchResultListener searchResultL;
	private ImageListener imgL;

	
	public void onShow() {
		// 재료 정보 가져오기
		List<Ingredient> ingredients = DataManager.getIngredients();
		
		refL = new RefrigeratorListener();
		ingButtonL = new IngredientButtonListener();
		searchResultL = new SearchResultListener();
		imgL = new ImageListener();
		
		createSearchBar(ingredients); // 검색UI 생성
		createLinePanels(ingredients); // 라인 패널들 생성

		// CartScene으로 넘어가는 버튼 추가
		btnCart = new ImageButton("images/cart.png", 900, 350);
		btnCart.addActionListener(refL);
		add(btnCart);

		// 뒤로가기 버튼 및 홈버튼
		btnGoBack = new ImageButton("images/goBack.png", 7, 810);
		btnGoBack.setLayout(null);
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setBorderPainted(false);
		btnGoBack.addActionListener(refL);
		add(btnGoBack);

		btnGoHome = new ImageButton("images/goHome.png", 20, 25);
		btnGoHome.setLayout(null);
		btnGoHome.setContentAreaFilled(false);
		btnGoHome.setBorderPainted(false);
		btnGoHome.addActionListener(refL);
		add(btnGoHome);
		
		// 배경
		imgBackground = new ImageIcon("images/refBackground2.png");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);
	}

	public void onHide() {

	}
	
	private void createSearchBar(List<Ingredient> ingredients) {
		// 검색 입력 & 버튼
		txtSearch = new JTextField();
		txtSearch.setBounds(950, 70, 430, 55);
		txtSearch.addActionListener(refL);
		add(txtSearch);
		btnSearch = new ImageButton("images/searchIcon.png", "images/searchIcon_h.png", 1390, 70);
		btnSearch.addActionListener(refL);
		add(btnSearch);
		
		//검색 스크롤패인 생성
		pnlSearch=new JPanel();
		pnlSearch.setOpaque(false);
		pnlSearch.setLayout(new GridLayout(0, 1));
		pnlSearchScroll=new JScrollPane(pnlSearch);
		pnlSearchScroll.getViewport().setOpaque(false);
		pnlSearchScroll.setOpaque(false);
		pnlSearchScroll.setBorder(null);
		pnlSearchScroll.setBounds(950, 125, 430, ITEM_SIZE[1]+10); // (ITEM_SIZE[1]+10)*n
		pnlSearchScroll.setVisible(false); // 숨김
		add(pnlSearchScroll);

		ingSearchMap = new HashMap<JButton, Ingredient>();
	}
	
	private JButton addSearchResultButton(Ingredient ing) {
		// 재료 버튼
		JButton btn = new JButton() { // 반투명 버튼
			protected void paintComponent(Graphics g)
			{
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setPreferredSize(new Dimension(410, ITEM_SIZE[1]+10));
		btn.setLayout(null);
		btn.setOpaque(false);
		btn.setBackground(new Color(1f, 1f, 1f, .5f));
		btn.setBorderPainted(false); // 버튼 테두리 제거
		btn.addActionListener(searchResultL);
		pnlSearch.add(btn);

		// 아이콘
		ImageIcon icon = getIngredientIcon(ing.getIcon());
		JLabel lblIcon = new JLabel(icon);
		lblIcon.setBounds(0, 0, ITEM_SIZE[0]+50, ITEM_SIZE[1]+10);
		btn.add(lblIcon);
		
		// 이름
		JLabel lblName = new JLabel(ing.getName());
		lblName.setBounds(ITEM_SIZE[0]+60, 0, 350-ITEM_SIZE[0], ITEM_SIZE[1]+10);
		lblName.setFont(getDefaultFont());
		btn.add(lblName);
		
		ingSearchMap.put(btn, ing);
		
		return btn;
	}
	
	private void createLinePanels(List<Ingredient> ingredients) {
		pnlLine = new JPanel[MAX_LINES];
		pnlLineScroll = new JScrollPane[MAX_LINES];
		for (int i = 0; i < MAX_LINES; i++) {
			// 라인 패널 생성
			pnlLine[i] = new JPanel();
			pnlLine[i].setLayout(new FlowLayout());
			pnlLine[i].setOpaque(false);
			
			// 라인 스크롤 페인 생성
			pnlLineScroll[i] = new JScrollPane(pnlLine[i]);
			pnlLineScroll[i].setBounds(LINE_BOUNDS[i][0], LINE_BOUNDS[i][1], LINE_BOUNDS[i][2], LINE_BOUNDS[i][3]);
			pnlLineScroll[i].getViewport().setOpaque(false);
			pnlLineScroll[i].setOpaque(false);
			pnlLineScroll[i].setBorder(null);
			add(pnlLineScroll[i]);
		}
		
		
		// 재료 버튼 추가
		ingButtonMap = new HashMap<JButton, Ingredient>();
		for (Ingredient ing : ingredients) {
			// 아이콘
			ImageIcon icon = getIngredientIcon(ing.getIcon());
			if (DataManager.getCart().contains(ing))
				icon = getIngredientIcon(ing.getIconBlack());

			// 재료 버튼
			JButton btn = new JButton();
			btn.setIcon(icon);
			btn.setPreferredSize(new Dimension(ITEM_SIZE[0], ITEM_SIZE[1]));
			btn.setContentAreaFilled(false); // 버튼 바탕색 제거
			btn.setBorderPainted(false); // 버튼 테두리 제거
			btn.addActionListener(ingButtonL);
			btn.addMouseListener(imgL);
			pnlLine[ing.getType()].add(btn);
			
			ingButtonMap.put(btn, ing);
		}
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
	
	private void search(String keyword) {
		int results = 0;
		
		if (keyword.length() > 0) { // 검색어가 공백이 아닌 경우
			pnlSearch.removeAll();
			ingSearchMap.clear();
			for (Ingredient ing : DataManager.getIngredients())
				if (ing.getName().toLowerCase().contains(keyword.toLowerCase())) {
					addSearchResultButton(ing);
					results++;
				}
			pnlSearch.revalidate();
			pnlSearch.repaint();
		}
		
		if (results == 0) { // 검색 결과가 없는 경우
			pnlSearchScroll.setVisible(false);
		} else {
			pnlSearchScroll.setSize(new Dimension(pnlSearchScroll.getWidth(), (ITEM_SIZE[1]+10)*((results > 3) ? 3 : results)));
			pnlSearchScroll.setVisible(true);
		}
	}

	private class RefrigeratorListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if (obj == btnCart) { // 카트 버튼 클릭
				animCartMove = new Animation(btnCart, false, new MoveListener());
				animCartMove.move(1500, 250, 500);
			} else if (obj == btnGoBack) { // 뒤로 버튼 클릭
				SceneManager.switchScene(new IntroScene());
			} else if (obj == btnGoHome) { // 홈 버튼 클릭
				SceneManager.switchScene(new IntroScene());
			} else if (obj == txtSearch || obj == btnSearch) {
				search(txtSearch.getText());
			}
		}
	}
	
	private class IngredientButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			List<Ingredient> cart = DataManager.getCart(); // 카트 리스트 객체
			Ingredient ing = ingButtonMap.get(obj); // 선택한 재료
			
			if (!cart.contains(ing)) {
				// 선택한 재료를 카트에 추가
				cart.add(ing);
				changeBlack((JButton)obj, ing);
				
				// 효과음
				SoundManager onButton = new SoundManager("./sounds/selectIngre.mp3", false);
				onButton.start();
			} else {
				// 카트에서 제거
				cart.remove(ing);
				changeOriginal((JButton)obj, ing);
				
				// 효과음
				SoundManager onButton = new SoundManager("./sounds/cancelIngre.mp3", false);
				onButton.start();
			}
		}
	}
	
	private class SearchResultListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			List<Ingredient> cart = DataManager.getCart(); // 카트 리스트 객체
			Ingredient ing = ingSearchMap.get(obj); // 선택한 재료
			
			for (Map.Entry<JButton, Ingredient> item : ingButtonMap.entrySet())
				if (item.getValue() == ing)
					if (!cart.contains(ing)) {
						// 선택한 재료를 카트에 추가
						cart.add(ing);
						changeBlack(item.getKey(), ing);
					} else {
						// 카트에서 제거
						cart.remove(ing);
						changeOriginal(item.getKey(), ing);
					}
		}
	}
	
	private class MoveListener implements AnimationListener
	{
		public void onCompleted() {
			SceneManager.switchScene(new CartScene());
		}
	}
	
	private class ImageListener implements MouseListener
	{
		// 마우스가 컴포넌트 안으로 들어온 상태
		public void mouseEntered(MouseEvent event) {
			Object obj = event.getSource();
			List<Ingredient> cart = DataManager.getCart(); // 카트 리스트 객체
			Ingredient ing = ingButtonMap.get(obj); // 선택한 재료
			
			if (!cart.contains(ing)) { // 선택되지 않은 상태라면
				changeLight((JButton)obj, ing);
			}
		}
				
		// 마우스가 컴포넌트 밖으로 나간 상태
		public void mouseExited(MouseEvent event) {
			Object obj = event.getSource();
			List<Ingredient> cart = DataManager.getCart(); // 카트 리스트 객체
			Ingredient ing = ingButtonMap.get(obj); // 선택한 재료
			
			if (!cart.contains(ing)) { // 선택되지 않은 상태라면
				changeOriginal((JButton)obj, ing);
			}
		}

		public void mouseClicked(MouseEvent event) { } 
		public void mousePressed(MouseEvent event) { }
		public void mouseReleased(MouseEvent event) { }
	}
}