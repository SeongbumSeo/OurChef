package Scene;

import java.util.*;
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
	private static final int[] ITEM_SIZE = { 65, 65 };

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

	public void onShow() {
		refL = new RefrigeratorListener();

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
			tmp.setText(item.getName());
			tmp.setPreferredSize(new Dimension(ITEM_SIZE[0], ITEM_SIZE[1]));
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
		imgBackground = new ImageIcon("images/refBackground.png");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);

		/*
		 * //냉장고 패널 linePanel1=new JPanel();//main이라고 가정 linePanel1.setLayout(null);
		 * linePanel1.setBounds(300,65,450,100); linePanel1.setOpaque(false);
		 * //linePanel1.setBackground(Color.black); add(linePanel1);
		 * 
		 * linePanel2=new JPanel(); linePanel2.setLayout(new FlowLayout());
		 * linePanel2.setOpaque(false); JScrollPane scrollPanel = new
		 * JScrollPane(linePanel2); scrollPanel.setBounds(300,215,450,95);
		 * scrollPanel.getViewport().setOpaque(false); scrollPanel.setOpaque(false);
		 * scrollPanel.setBorder(null); add(scrollPanel);
		 * 
		 * linePanel3=new JPanel(); linePanel3.setLayout(null);
		 * linePanel3.setBounds(330,335,410,100);
		 * //linePanel3.setBackground(Color.black); linePanel3.setOpaque(false);
		 * add(linePanel3);
		 * 
		 * linePanel4=new JPanel(); linePanel4.setLayout(new FlowLayout());
		 * linePanel4.setOpaque(false); JScrollPane scrollPanel2 = new
		 * JScrollPane(linePanel4); scrollPanel2.setBounds(290,490,480,95);
		 * scrollPanel2.getViewport().setOpaque(false); scrollPanel2.setOpaque(false);
		 * scrollPanel2.setBorder(null); add(scrollPanel2);
		 * 
		 * linePanel5=new JPanel(); linePanel5.setLayout(null);
		 * linePanel5.setBounds(320,610,460,100); linePanel5.setOpaque(false);
		 * //linePanel5.setBackground(Color.black); add(linePanel5);
		 * 
		 * linePanel6=new JPanel(); linePanel6.setLayout(null);
		 * linePanel6.setBounds(320,760,460,100); linePanel6.setOpaque(false);
		 * //linePanel6.setBackground(Color.black); add(linePanel6);
		 * 
		 * // 배경 imgBackground = new ImageIcon("images/refBackground.png");
		 * lblBackground = new JLabel(); lblBackground.setIcon(imgBackground);
		 * lblBackground.setBounds(0, 0, 1600, 900); add(lblBackground);
		 * 
		 * btnIngredients_1=new JButton[4]; for(int i=0 ; i<4 ; i++) {
		 * btnIngredients_1[i]=new JButton(); btnIngredients_1[i].setBounds(30+100*i,
		 * 18, 65, 65); linePanel1.add(btnIngredients_1[i]); }//1라인
		 * 
		 * btnIngredients_2=new JButton[9]; for(int i=0 ; i<9 ; i++) {
		 * btnIngredients_2[i]=new JButton(); btnIngredients_2[i].setPreferredSize(new
		 * Dimension(65,65)); linePanel2.add(btnIngredients_2[i]); }//2라인
		 * 
		 * 
		 * btnIngredients_3=new JButton[3]; for(int i=0 ; i<3 ; i++) {
		 * btnIngredients_3[i]=new JButton(); btnIngredients_3[i].setBounds(30+150*i,
		 * 18, 65, 65); linePanel3.add(btnIngredients_3[i]); }//3라인
		 * 
		 * 
		 * btnIngredients_4=new JButton[7]; for(int i=0 ; i<7 ; i++) {
		 * btnIngredients_4[i]=new JButton(); //btnIngredients_4[i].setBounds(30+50*i,
		 * 18, 65, 65); btnIngredients_4[i].setPreferredSize(new Dimension(65,65));
		 * linePanel4.add(btnIngredients_4[i]); }//4라인
		 * 
		 * btnIngredients_5=new JButton[6]; for(int i=0 ; i<6 ; i++) {
		 * btnIngredients_5[i]=new JButton(); btnIngredients_5[i].setBounds(10+75*i, 18,
		 * 65, 65); linePanel5.add(btnIngredients_5[i]); }//5라인
		 * 
		 * btnIngredients_6=new JButton[7]; for(int i=0 ; i<7 ; i++) {
		 * btnIngredients_6[i]=new JButton(); btnIngredients_6[i].setBounds(10+75*i, 18,
		 * 65, 65); linePanel6.add(btnIngredients_6[i]); }//6라인
		 */
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