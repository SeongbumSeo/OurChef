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
	private JPanel pnlAbout;
	private JLabel lblHeader;
	private JButton btnClose;
	private JLabel lblAbout;
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
		
		// 사용 방법 패널 생성
		createAboutPanel();
	}
	
	public void onHide() {
		
	}
	
	/**
	 * 사용 방법 패널을 생성하는 메소드입니다.
	 */
	private void createAboutPanel() {
		// 패널 생성
		pnlAbout = new JPanel();
		pnlAbout.setBounds(Main.getFrame().getWidth()/4, Main.getFrame().getHeight()/4, Main.getFrame().getWidth()/2, Main.getFrame().getHeight()/2);
		pnlAbout.setBackground(Color.gray);
		pnlAbout.setLayout(null);
		pnlAbout.setVisible(false);
		add(pnlAbout);
		
		// 헤더 라벨 추가
		lblHeader = new JLabel("사용 방법");
		lblHeader.setBounds(0, 0, pnlAbout.getWidth(), 50);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(Color.white);
		lblHeader.setBackground(Color.black);
		lblHeader.setOpaque(true);
		pnlAbout.add(lblHeader);
		
		// 닫기 버튼 추가
		btnClose = new JButton("x");
		btnClose.setBounds(pnlAbout.getWidth()-50, 0, 50, 50);
		btnClose.addActionListener(introL);
		lblHeader.add(btnClose);
		
		// 내용 추가
		lblAbout = new JLabel("<html>사용방법입니다~~사용방법입니다~~사용방법입니다~~사용방법입니다~~사용방법입니다~~사용방법입니다~~사용방법입니다~~<br>사용방법입니다~~사용방법입니다~~</html>");
		lblAbout.setBounds(10, 60, pnlAbout.getWidth()-20, pnlAbout.getHeight()-70);
		lblAbout.setVerticalAlignment(SwingConstants.TOP);
		lblAbout.setForeground(Color.white);
		lblAbout.setBackground(Color.black);
		lblAbout.setOpaque(true);
		pnlAbout.add(lblAbout);
	}
	
	private class IntroListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if (obj == btnAbout) { // 사용 방법 버튼 클릭
				pnlAbout.setVisible(true);
			} else if (obj == btnStart) // 시작 버튼 클릭
				Main.switchScene(new RefrigeratorScene());
			else if (obj == btnClose) // 닫기 버튼 클릭
				pnlAbout.setVisible(false);
		}
	}
}
