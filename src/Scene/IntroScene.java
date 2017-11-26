package Scene;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Main.Main;
import Scene.*;

public class IntroScene extends SceneAbst
{
	// 배경
	private ImageIcon imgBackground;
	private JLabel lblBackground;
	
	// 시작화면 버튼
	private JButton btnStart;
	private JButton btnAbout;
	
	// 사용 설명 화면
	private JPanel pnlAbout;
	private JLabel lblAbout;
	private JButton btnGoBack;
	private ImageIcon imgPnlBackground, imgGoBack;
	
	
	private IntroListener introL;	
	
	public void onShow() {
		introL = new IntroListener();
		
		// 사용 방법 버튼 추가
		btnAbout = new JButton("사용 방법");
		btnAbout.setBounds(370, 500, 70, 300);
		btnAbout.addActionListener(introL);
		add(btnAbout);
		
		// 시작 버튼 추가
		btnStart = new JButton("시작");
		btnStart.setBounds(1125, 650, 110, 140);
		btnStart.addActionListener(introL);
		add(btnStart);
		
		// 배경 설정
		imgBackground = new ImageIcon("./images/tmpImgBackground.png");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);
		
		// 사용 방법 패널 생성
		createAboutPanel();
		btnGoBack.setVisible(false);
	}
	
	public void onHide() {
		
	}
	
	public void onStartHide() {
		btnAbout.setVisible(false);
		btnStart.setVisible(false);
		lblBackground.setVisible(false);
	}
	
	public void onStartShow() {
		btnAbout.setVisible(true);
		btnStart.setVisible(true);
		lblBackground.setVisible(true);
	}
	
	public void onAboutHide() {
		pnlAbout.setVisible(false);
		btnGoBack.setVisible(false);
	}
	
	/**
	 * 사용 방법 패널을 생성하는 메소드입니다.
	 */
	private void createAboutPanel() {
		pnlAbout = new JPanel();
		pnlAbout.setBounds(0, 0, 1600, 900);
		pnlAbout.setLayout(null);
		
		imgGoBack = new ImageIcon("./images/goBack.png");
		btnGoBack = new JButton();
		btnGoBack.setIcon(imgGoBack);
		btnGoBack.setLayout(null);
		btnGoBack.setBounds(1425, 50, 118, 118);
		btnGoBack.addActionListener(introL);
		pnlAbout.add(btnGoBack);
		
		imgPnlBackground = new ImageIcon("./images/imgPnlBackground.png");
		lblAbout = new JLabel();
		lblAbout.setIcon(imgPnlBackground);
		lblAbout.setBounds(0, 0, 1600, 900);
		pnlAbout.add(lblAbout);
		
		add(pnlAbout);		
	} // createAboutPanel()
	
	private class IntroListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if (obj == btnAbout) { // 사용 방법 버튼 클릭
				onStartHide();
				pnlAbout.setVisible(true);
				btnGoBack.setVisible(true);
			} else if (obj == btnStart) {// 시작 버튼 클릭
				Main.switchScene(new RefrigeratorScene());
			} else if (obj == btnGoBack) {
				onAboutHide();
				onStartShow();
			}
		}
	}
}
