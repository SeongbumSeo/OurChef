package Scene;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Main.Main;
import Scene.*;

public class IntroScene extends SceneAbst
{
	// ���
	private ImageIcon imgBackground;
	private JLabel lblBackground;
	
	// ����ȭ�� ��ư
	private JButton btnStart;
	private JButton btnAbout;
	
	// ��� ���� ȭ��
	private JPanel pnlAbout;
	private JLabel lblAbout;
	private JButton btnGoBack;
	private ImageIcon imgPnlBackground, imgGoBack;
	
	
	private IntroListener introL;	
	
	public void onShow() {
		introL = new IntroListener();
		
		// ��� ��� ��ư �߰�
		btnAbout = new JButton("��� ���");
		btnAbout.setBounds(370, 500, 70, 300);
		btnAbout.addActionListener(introL);
		add(btnAbout);
		
		// ���� ��ư �߰�
		btnStart = new JButton("����");
		btnStart.setBounds(1125, 650, 110, 140);
		btnStart.addActionListener(introL);
		add(btnStart);
		
		// ��� ����
		imgBackground = new ImageIcon("./images/tmpImgBackground.png");
		lblBackground = new JLabel();
		lblBackground.setIcon(imgBackground);
		lblBackground.setBounds(0, 0, 1600, 900);
		add(lblBackground);
		
		// ��� ��� �г� ����
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
	 * ��� ��� �г��� �����ϴ� �޼ҵ��Դϴ�.
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
			
			if (obj == btnAbout) { // ��� ��� ��ư Ŭ��
				onStartHide();
				pnlAbout.setVisible(true);
				btnGoBack.setVisible(true);
			} else if (obj == btnStart) {// ���� ��ư Ŭ��
				Main.switchScene(new RefrigeratorScene());
			} else if (obj == btnGoBack) {
				onAboutHide();
				onStartShow();
			}
		}
	}
}
