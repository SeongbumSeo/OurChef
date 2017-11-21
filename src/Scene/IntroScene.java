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
		
		// ��� ��� �г� ����
		createAboutPanel();
	}
	
	public void onHide() {
		
	}
	
	/**
	 * ��� ��� �г��� �����ϴ� �޼ҵ��Դϴ�.
	 */
	private void createAboutPanel() {
		// �г� ����
		pnlAbout = new JPanel();
		pnlAbout.setBounds(Main.getFrame().getWidth()/4, Main.getFrame().getHeight()/4, Main.getFrame().getWidth()/2, Main.getFrame().getHeight()/2);
		pnlAbout.setBackground(Color.gray);
		pnlAbout.setLayout(null);
		pnlAbout.setVisible(false);
		add(pnlAbout);
		
		// ��� �� �߰�
		lblHeader = new JLabel("��� ���");
		lblHeader.setBounds(0, 0, pnlAbout.getWidth(), 50);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(Color.white);
		lblHeader.setBackground(Color.black);
		lblHeader.setOpaque(true);
		pnlAbout.add(lblHeader);
		
		// �ݱ� ��ư �߰�
		btnClose = new JButton("x");
		btnClose.setBounds(pnlAbout.getWidth()-50, 0, 50, 50);
		btnClose.addActionListener(introL);
		lblHeader.add(btnClose);
		
		// ���� �߰�
		lblAbout = new JLabel("<html>������Դϴ�~~������Դϴ�~~������Դϴ�~~������Դϴ�~~������Դϴ�~~������Դϴ�~~������Դϴ�~~<br>������Դϴ�~~������Դϴ�~~</html>");
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
			
			if (obj == btnAbout) { // ��� ��� ��ư Ŭ��
				pnlAbout.setVisible(true);
			} else if (obj == btnStart) // ���� ��ư Ŭ��
				Main.switchScene(new RefrigeratorScene());
			else if (obj == btnClose) // �ݱ� ��ư Ŭ��
				pnlAbout.setVisible(false);
		}
	}
}
