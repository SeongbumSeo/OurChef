package Scene;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Main.Main;
import Scene.*;

public class IntroScene extends SceneAbst
{
	private JButton btnAbout;
	private JButton btnStart;
	private IntroListener introL;
	
	public void onShow() {
		introL = new IntroListener();
		
		// ��� ��� ��ư �߰�
		btnAbout = new JButton("��� ���");
		btnAbout.setBounds(1000, 500, 100, 40);
		btnAbout.addActionListener(introL);
		add(btnAbout);
		
		// ���� ��ư �߰�
		btnStart = new JButton("����");
		btnStart.setBounds(1000, 550, 100, 40);
		btnStart.addActionListener(introL);
		add(btnStart);
	}
	
	public void onHide() {
		
	}
	
	private class IntroListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if (obj == btnStart) { // ���� ��ư Ŭ��
				Main.switchScene(new RefrigeratorScene());
			}
		}
	}
}
