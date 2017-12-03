package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import GUI.*;

public class ShowRecipeScene extends SceneAbst
{
	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;

	public void onShow() {
	
		
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
	
}
