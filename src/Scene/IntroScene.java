package Scene;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Main.Main;
import Scene.*;
import GUI.*;

public class IntroScene extends SceneAbst
{
	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;
	
	// 시작화면 버튼
	private ImageButton btnStart, btnStart2;
	private ImageButton btnAbout;
	
	// 사용 설명 화면
	private JPanel pnlAbout;
	private JLabel lblAbout;
	private ImageButton btnGoBack;
	
	private IntroListener introL;	
	
	public void onShow() {
		introL = new IntroListener();
		
		// 사용 방법 패널 생성
		createAboutPanel();
		
		// 사용 방법 버튼 추가
		btnAbout = new ImageButton("images/bottle.png", "images/bottle_h.png", 350, 395);
		btnAbout.addActionListener(introL);
		add(btnAbout);
		
		// 시작 버튼 추가
		btnStart = new ImageButton("images/basket.png","images/basket_h.png", 1090, 558);
		btnStart.addActionListener(introL);
		add(btnStart);
		
		// 배경 설정
		imgBackground = new ImageIcon("images/imgBackground.jpg");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);
	}
	
	public void onHide() {
		
	}
	
	/**
	 * 사용 방법 패널을 생성하는 메소드입니다.
	 */
	private void createAboutPanel() {
		pnlAbout = new JPanel();
		pnlAbout.setBounds(0, 0, 1600, 900);
		pnlAbout.setLayout(null);
		pnlAbout.setVisible(false);

		btnStart2 = new ImageButton("images/basket.png","images/basket_h.png", 1180, 630);
		btnStart2.addActionListener(introL);
		pnlAbout.add(btnStart2);
		
		btnGoBack = new ImageButton("images/goBack.png", 7, 810);
		btnGoBack.setLayout(null);
		btnGoBack.setContentAreaFilled(false);
		btnGoBack.setBorderPainted(false);
		btnGoBack.addActionListener(introL);
		pnlAbout.add(btnGoBack);
		
		lblAbout = new JLabel();
		lblAbout.setIcon(new ImageIcon("images/imgPnlBackground.png"));
		lblAbout.setBounds(0, 0, 1600, 900);
		pnlAbout.add(lblAbout);
		
		add(pnlAbout);
	}
	
	private class IntroListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if (obj == btnAbout) { // 사용 방법 버튼 클릭
				pnlAbout.setVisible(true);
			} else if (obj == btnStart) { // 시작 버튼 클릭
				Main.switchScene(new RefrigeratorScene());
			} else if (obj == btnGoBack) { // 뒤로 버튼 클릭
				pnlAbout.setVisible(false);
			}
		}
	}
}
