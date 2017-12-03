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
   private ImageIcon imgBackground;
   private JLabel lblBackground;
   
   // 뒤로가기 및 홈버튼
   private ImageButton btnGoBack, btnGoHome;
   
   // 카트
   private ImageButton btnCart;
   
   // 검색
   private JTextField txtInput;
   private ImageButton btnSearch;
   
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
      btnCart = new ImageButton("images/cart.png", 900, 350);
      btnCart.addActionListener(refL);
      add(btnCart);
      
      //냉장고 패널
      linePanel1=new JPanel();//main이라고 가정
      linePanel1.setLayout(null);
      linePanel1.setBounds(300,65,450,100);
      linePanel1.setOpaque(false);
      //linePanel1.setBackground(Color.black);
      add(linePanel1);
      
      // 검색
      txtInput = new JTextField();
      txtInput.setBounds(950, 70, 430, 55);
      txtInput.addActionListener(refL);
      add(txtInput);
      btnSearch = new ImageButton("images/searchIcon.png","images/searchIcon_h.png", 1390, 70);
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
      
      
      ScrollPane scrollPanel = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
      scrollPanel.setBounds(300,215,450,105);      
      /*JScrollPane scrollPanel = new JScrollPane();
      scrollPanel.setBounds(300,215,450,105);
      scrollPanel.setOpaque(false);*/
            
      linePanel2=new JPanel();
      linePanel2.setLayout(new FlowLayout());
      // linePanel2.setLayout(null);
      //linePanel2.setBounds(290, 250, 410, 80);\
      linePanel2.setOpaque(false);
      scrollPanel.add(linePanel2);
      add(scrollPanel);
      
      linePanel3=new JPanel();
      linePanel3.setLayout(null);
      linePanel3.setBounds(330,335,410,100);
      //linePanel3.setBackground(Color.black);
      linePanel3.setOpaque(false);
      add(linePanel3);
      
      ScrollPane scrollPanel2 = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
      scrollPanel2.setBounds(290,490,480,105);  
      
      linePanel4=new JPanel();
      linePanel4.setLayout(new FlowLayout());
      linePanel4.setBounds(290,490,480,100);
      //linePanel4.setBackground(Color.black);
      scrollPanel2.add(linePanel4);
      add(scrollPanel2);
      
      linePanel5=new JPanel();
      linePanel5.setLayout(null);
      linePanel5.setBounds(320,610,460,100);
      linePanel5.setOpaque(false);
      //linePanel5.setBackground(Color.black);
      add(linePanel5);
      
      linePanel6=new JPanel();
      linePanel6.setLayout(null);
      linePanel6.setBounds(320,760,460,100);
      linePanel6.setOpaque(false);
      //linePanel6.setBackground(Color.black);
      add(linePanel6);
      
      // 배경
      imgBackground = new ImageIcon("images/refBackground.png");
      lblBackground = new JLabel();
      lblBackground.setIcon(imgBackground);
      lblBackground.setBounds(0, 0, 1600, 900);
      add(lblBackground);
      
      btnIngredients_1=new JButton[4];
      for(int i=0 ; i<4 ; i++) {
         btnIngredients_1[i]=new JButton();
         btnIngredients_1[i].setBounds(30+100*i, 18, 65, 65);
         linePanel1.add(btnIngredients_1[i]);
      }//1라인
      
      btnIngredients_2=new JButton[9];
      for(int i=0 ; i<9 ; i++) {
         btnIngredients_2[i]=new JButton();
         btnIngredients_2[i].setPreferredSize(new Dimension(65,65));
         linePanel2.add(btnIngredients_2[i]);
      }//2라인
      
      
      btnIngredients_3=new JButton[3];
      for(int i=0 ; i<3 ; i++) {
         btnIngredients_3[i]=new JButton();
         btnIngredients_3[i].setBounds(30+150*i, 18, 65, 65);
         linePanel3.add(btnIngredients_3[i]);
      }//3라인
      
      
      btnIngredients_4=new JButton[7];
      for(int i=0 ; i<7 ; i++) {
         btnIngredients_4[i]=new JButton();
         //btnIngredients_4[i].setBounds(30+50*i, 18, 65, 65);
         btnIngredients_4[i].setPreferredSize(new Dimension(65,65));
         linePanel4.add(btnIngredients_4[i]);
      }//4라인
      
      btnIngredients_5=new JButton[6];
      for(int i=0 ; i<6 ; i++) {
         btnIngredients_5[i]=new JButton();
         btnIngredients_5[i].setBounds(10+75*i, 18, 65, 65);
         linePanel5.add(btnIngredients_5[i]);
      }//5라인
      
      btnIngredients_6=new JButton[7];
      for(int i=0 ; i<7 ; i++) {
         btnIngredients_6[i]=new JButton();
         btnIngredients_6[i].setBounds(10+75*i, 18, 65, 65);
         linePanel6.add(btnIngredients_6[i]);
      }//6라인
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