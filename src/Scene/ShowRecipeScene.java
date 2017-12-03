package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import Scene.RecipeListScene.RecipeListener;
import GUI.*;

public class ShowRecipeScene extends SceneAbst
{
	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;
	
	// 뒤로가기 및 홈버튼
	private ImageButton btnGoBack, btnGoHome;
	
	// 이벤트
	private ShowListener ShowL;

	public void onShow() {
	
		// 뒤로가기 버튼 및 홈버튼
	    btnGoBack = new ImageButton("images/goBack.png", 7, 810);
	    btnGoBack.setLayout(null);
	    btnGoBack.setContentAreaFilled(false);
	    btnGoBack.setBorderPainted(false);
	    btnGoBack.addActionListener(ShowL);
	    add(btnGoBack);
	        
	    btnGoHome = new ImageButton("images/goHome.png", 20, 25);
	    btnGoHome.setLayout(null);
	    btnGoHome.setContentAreaFilled(false);
	    btnGoHome.setBorderPainted(false);
	    btnGoHome.addActionListener(ShowL);
	    add(btnGoHome);
		
		// 배경
	    imgBackground = new ImageIcon("./images/showRecipeBackground.png");
	    lblBackground = new JLabel();
	    lblBackground.setIcon(imgBackground);
	    lblBackground.setBounds(0, 0, 1600, 900);
	      
	    lblBackground.setLayout(null);
	      
	    add(lblBackground);
		
	}
	
	public void onHide() {
        
	}
	
	private class ShowListener implements ActionListener
	{
	   public void actionPerformed(ActionEvent event) {
	      Object obj = event.getSource();
	       
	      if (obj == btnGoBack) {
	         Main.switchScene(new RecipeListScene());
	      } else if (obj == btnGoHome) {
	         Main.switchScene(new RecipeListScene());
	      }
	   }
	}
	
}
