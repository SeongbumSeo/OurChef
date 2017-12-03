package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
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
   
    //JScrollPane scrollPane;
    JPanel panel;
    
    //JButton b1,b2,b3,b4,b5,b6;
    
    // 레시피
    private JPanel pnlRecipe;
    private ImageIcon IconDish, IconDish2;
    private ImageIcon IconStar, IconStar2;
    private JLabel lblDish, lblName, lblStar;
    

    private JButton[] btnRecipe;
     
    
   public void onShow() {
      RecipeL = new RecipeListener();
      
      //test
      ScrollPane scrollPanel = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
      scrollPanel.setBounds(133, 150, 1360, 250);
      //scrollPanel.setBackground(Color.green);
      
      // 레시피 패널
      pnlRecipe = new JPanel();
      pnlRecipe.setLayout(null);
      pnlRecipe.setBounds(155,500, 500, 180);
      pnlRecipe.setBorder(BorderFactory.createLineBorder(new Color(166,166,166)));
      pnlRecipe.setOpaque(false);
      
      IconDish = new ImageIcon("./images/recipe/ahi poke/image.png");
      Image imgDish = IconDish.getImage();
      imgDish = imgDish.getScaledInstance(240, 135, java.awt.Image.SCALE_SMOOTH);
      IconDish2 = new ImageIcon(imgDish);
      
      lblDish = new JLabel();
      lblDish.setIcon(IconDish2);
      lblDish.setLayout(null);
      lblDish.setBounds(20, 23, 240, 135);
      lblDish.setOpaque(false);
      
      lblName = new JLabel("Ahi poke");
      lblName.setHorizontalAlignment(SwingConstants.CENTER);
      lblName.setLayout(null);
      lblName.setBounds(270,30,210,40);
      
      IconStar = new ImageIcon("./images/star/one.png");
      Image imgStar = IconStar.getImage();
      imgStar = imgStar.getScaledInstance(210, 38, java.awt.Image.SCALE_SMOOTH);
      IconStar2 = new ImageIcon(imgStar);
      
      lblStar = new JLabel();
      lblStar.setIcon(IconStar2);
      lblStar.setLayout(null);
      lblStar.setBounds(285, 100, 210, 40);
      
      pnlRecipe.add(lblStar);
      pnlRecipe.add(lblName);
      pnlRecipe.add(lblDish);
      
      add(pnlRecipe);
      
      
      
      
      panel=new JPanel();
      panel.setLayout(new FlowLayout());
      panel.setOpaque(false);
      scrollPanel.add(panel);
      add(scrollPanel);
       
      
      
      
      
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
      fish = new ImageButton("images/fish.png","images/fish.png", 680, 500);
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
      
      
      //
      //panel = new JPanel();
      //panel.setLayout(new GridLayout(5,1,0,200));
      /*
      GridBagLayout gridbag = new GridBagLayout();
       panel.setLayout(gridbag);*/
      //panel.setLayout(null);
       
       
       
       
       
       /*
        scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(120, 100, 530, 700);
        lblBackground.add(scrollPane);
       
      
       GridBagConstraints constraint = new GridBagConstraints();
       
       constraint.fill=GridBagConstraints.BOTH;
       constraint.gridwidth = GridBagConstraints.REMAINDER;
       constraint.gridheight = 1;
       constraint.weightx = 1;
       constraint.weighty = 1;
       //constraint.ipady=1;
       */
       

       btnRecipe=new JButton[10];
       
       for(int i=0 ; i<10 ; i++) {
          btnRecipe[i]=new JButton("1");
          btnRecipe[i].setPreferredSize(new Dimension(530,200));//안먹음
          //gridbag.setConstraints(btnRecipe[i], constraint);
          panel.add(btnRecipe[i]);
         }// 수정하셔야 해요 일정 갯수 넣어놓음
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