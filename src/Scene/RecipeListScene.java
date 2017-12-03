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
    private ImageButton btnMan1, btnMan2;
   
    //JScrollPane scrollPane;
    JPanel panel;
    
    //JButton b1,b2,b3,b4,b5,b6;
    

    private JButton[] btnRecipe;
     
    
   public void onShow() {
      RecipeL = new RecipeListener();
      
      //test
      ScrollPane scrollPanel = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
       scrollPanel.setBounds(120, 100, 1360, 250);
       //scrollPanel.setBackground(Color.green);
              
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
      
       // 아저씨
       btnMan1 = new ImageButton("images/man_1.png", 370, -100);
       btnMan1.setLocation(370, -100);
       btnMan1.setLayout(null);
       btnMan1.setContentAreaFilled(false);
       btnMan1.setBorderPainted(false);
       btnMan1.addActionListener(RecipeL);
       add(btnMan1);
       
      // 배경
      imgBackground = new ImageIcon("./images/recipeListBackground.png");
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