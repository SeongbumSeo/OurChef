package Scene;

import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.*;
import Scene.*;
import GUI.*;
import Data.*;

public class RefrigeratorScene extends SceneAbst
{
	private static final int MAX_LINES = 6;
	private static final int[][] LINE_BOUNDS = {
			{ 300, 65, 450, 95 },
			{ 300, 215, 450, 95 },
			{ 330, 335, 410, 95 },
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

	// 검색
	private JTextField txtInput;
	private ImageButton btnSearch;

	// 각 라인의 패널
	private JPanel[] pnlLine;
	// 각 라인의 스크롤 페인
	private JScrollPane[] pnlLineScroll;

	// 이벤트
	private RefrigeratorListener refL;
	
	// 재료
	private static List<Ingredient> ingredients;

	public void onShow() {
		refL = new RefrigeratorListener();
		
		// 재료 정보 가져오기
		ingredients = Main.getIngredients();

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
		Iterator<Ingredient> itr = Main.getIngredients().iterator();
		while (itr.hasNext()) {
			Ingredient item = itr.next();
			JButton tmp = new JButton();
			
			ImageIcon imgTmp = new ImageIcon(item.getIcon());
			Image temp = imgTmp.getImage();
			temp = temp.getScaledInstance(ITEM_SIZE[0]-20, ITEM_SIZE[1], java.awt.Image.SCALE_SMOOTH);
			ImageIcon imgTmp2 = new ImageIcon(temp);
			
			tmp.setIcon(imgTmp2);
			tmp.setPreferredSize(new Dimension(ITEM_SIZE[0], ITEM_SIZE[1]));
			tmp.setContentAreaFilled(false); // 버튼 바탕색 제거
			tmp.setBorderPainted(false); // 버튼 테두리 제거
			pnlLine[item.getType()].add(tmp);
		}

		// CartScene으로 넘어가는 버튼 추가
		btnCart = new ImageButton("images/cart.png", 900, 350);
		btnCart.addActionListener(refL);
		add(btnCart);

		// 검색
		txtInput = new JTextField();
		txtInput.setBounds(950, 70, 430, 55);
		txtInput.addActionListener(refL);
		add(txtInput);
		btnSearch = new ImageButton("images/searchIcon.png", "images/searchIcon_h.png", 1390, 70);
		btnSearch.addActionListener(refL);
		add(btnSearch);

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

	private class RefrigeratorListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if (obj == btnCart) { // 시작 버튼 클릭
				Main.switchScene(new CartScene());
			} else if (obj == btnGoBack) {
				Main.switchScene(new IntroScene());
			} else if (obj == btnGoHome) {
				Main.switchScene(new IntroScene());
			} else if (obj == txtInput || obj == btnSearch) {

			}
		}
	}
}