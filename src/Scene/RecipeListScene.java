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
	
	public void onShow() {
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
		add(lblBackground);
	}
		
	public void onHide() {
			
	}
	
	private class RecipeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if (obj == btnGoBack) {
				
			} else if (obj == btnGoHome) {
				
			}
		}
	}
}
