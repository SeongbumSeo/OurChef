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
		lblWelcome = new JLabel("�����ǿ� ���� ���� ȯ���մϴ�~~~");
		lblWelcome.setBounds(500, 250, 500, 50);
		add(lblWelcome);
		
	}
		
	public void onHide() {
			
	}
}
