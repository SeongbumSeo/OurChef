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
		
		// 사용 방법 버튼 추가
		btnAbout = new JButton("사용 방법");
		btnAbout.setBounds(1000, 500, 100, 40);
		btnAbout.addActionListener(introL);
		add(btnAbout);
		
		// 시작 버튼 추가
		btnStart = new JButton("시작");
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
			
			if (obj == btnStart) { // 시작 버튼 클릭
				Main.switchScene(new RefrigeratorScene());
			}
		}
	}
}
