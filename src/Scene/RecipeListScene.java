package Scene;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import GUI.*;

public class RecipeListScene extends SceneAbst
{
	private JLabel lblWelcome;
	
	public void onShow() {
		lblWelcome = new JLabel("레시피에 오신 것을 환영합니다~~~");
		lblWelcome.setBounds(500, 250, 500, 50);
		add(lblWelcome);
		
	}
		
	public void onHide() {
			
	}
}
