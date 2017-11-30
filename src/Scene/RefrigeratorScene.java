package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import GUI.*;

public class RefrigeratorScene extends SceneAbst
{
	// 배경
	private ImageIcon imgBackground, imgRefrigerator;
	private JLabel lblBackground, lblRefrigerator;
	
	// 카트
	private JButton btnCart;
	
	//칸 패널
	JPanel linePanel1;
	JPanel linePanel2;
	JPanel linePanel3;
	JPanel linePanel4;
	JPanel linePanel5;
	JPanel linePanel6;
	//나중에 배열 고려
	
	
	//칸별 버튼
	private JButton[] btnIngredients_1;
	private JButton[] btnIngredients_2;
	private JButton[] btnIngredients_3;
	private JButton[] btnIngredients_4;
	private JButton[] btnIngredients_5;
	private JButton[] btnIngredients_6;
	
	//칸별 스크롤바
	Scrollbar bar1,bar2,bar3,bar4,bar5,bar6;
	
	// 이벤트
	private RefrigeratorListener refL;
	
	public void onShow() {
		refL = new RefrigeratorListener();
		
		// CartScene으로 넘어가는 버튼 추가
		btnCart = new ImageButton("images/cart.png", 870, 380);
		btnCart.addActionListener(refL);
		add(btnCart);
		
		//냉장고 패널
		linePanel1=new JPanel();
		linePanel1.setBounds(210,130,240,65);
		linePanel1.setBackground(Color.black);
		//linePanel1.setLayout(null);
		linePanel1.setLayout(new GridLayout(1,4,5,0));
		add(linePanel1);
		
		linePanel2=new JPanel();
		linePanel2.setBounds(210,233,240,65);
		linePanel2.setBackground(Color.black);
		add(linePanel2);
		
		linePanel3=new JPanel();
		linePanel3.setBounds(210,333,240,65);
		linePanel3.setBackground(Color.black);
		add(linePanel3);
		
		linePanel4=new JPanel();
		linePanel4.setBounds(210,420,240,65);
		linePanel4.setBackground(Color.black);
		add(linePanel4);
		
		linePanel5=new JPanel();
		linePanel5.setBounds(210,520,240,65);
		linePanel5.setBackground(Color.black);
		add(linePanel5);
		
		linePanel6=new JPanel();
		linePanel6.setBounds(210,690,240,65);
		linePanel6.setBackground(Color.black);
		add(linePanel6);
		
		
		
		// 배경_냉장고
		imgRefrigerator = new ImageIcon("images/refrigerator.png");
		lblRefrigerator = new JLabel();
		lblRefrigerator.setIcon(imgRefrigerator);
		lblRefrigerator.setBounds(150, 35, imgRefrigerator.getIconWidth(), imgRefrigerator.getIconHeight() );
		add(lblRefrigerator);
		
		// 배경
		imgBackground = new ImageIcon("images/background.jpg");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);
		
		
		
		
		
		//냉장고 버튼들
		btnIngredients_1=new JButton[4];
		for(int i=0 ; i<4 ; i++) {
			btnIngredients_1[i]=new JButton("1");
			btnIngredients_1[i].setBackground(Color.white);
			//마진 해야할거임
			
			linePanel1.add(btnIngredients_1[i]);
		}//1라인
		
		/*
		for(int i=0 ; i<9 ; i++) {
			btnIngredients_2[i]=new JButton();
		}//2라인
		for(int i=0 ; i<3 ; i++) {
			btnIngredients_3[i]=new JButton();
		}//3라인
		for(int i=0 ; i<7 ; i++) {
			btnIngredients_4[i]=new JButton();
		}//4라인
		for(int i=0 ; i<6 ; i++) {
			btnIngredients_5[i]=new JButton();
		}//5라인
		for(int i=0 ; i<7 ; i++) {
			btnIngredients_6[i]=new JButton();
		}*///6라인
		
		
		
	}
	
	public void onHide() {
		
	}
	
	private class RefrigeratorListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if (obj == btnCart) { // 시작 버튼 클릭
				Main.switchScene(new CartScene());
			}
		}
	}
}
