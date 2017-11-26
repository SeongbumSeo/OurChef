package Scene;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import Main.Main;

public abstract class SceneAbst extends JPanel implements Scene
{
	/**
	 * �߻�Ŭ���� SceneAbst�� �������Դϴ�.
	 */
	public SceneAbst() {
		setPreferredSize(new Dimension(Main.getFrame().getWidth(), Main.getFrame().getHeight())); // ������ ũ�⿡ ����
		setBackground(Color.white); // ������ ����
		setLayout(null); // ��ġ������ ����

		System.out.println(this.getClass().getSimpleName() + "�� �߻�Ŭ������ �ε�Ǿ����ϴ�.");
		
		onShow();
		applyDefaultFont("JejuGothic");
		
		System.out.println(this.getClass().getSimpleName() + "�� �ε�Ǿ����ϴ�.");
	}
	
	/**
	 * ��Ʈ�� ������� ���� ������Ʈ�� �⺻ ��Ʈ�� �����ϴ� �޼ҵ��Դϴ�.
	 */
	private void applyDefaultFont(String name) {
		try {
			// ��Ʈ ���� �ε�
			File file = new File("fonts/" + name + ".ttf");
			Font fontBase = Font.createFont(Font.TRUETYPE_FONT, file);
			Font fontReal = fontBase.deriveFont(Font.PLAIN, 12);
			
			// ��Ʈ ����
			applyDefaultFont(this, fontReal);
		} catch (Exception e) { }
	}
	/**
	 * ��ü ������Ʈ�� ������ȸ�ϸ� �⺻ ��Ʈ�� �����ϴ� �޼ҵ��Դϴ�.
	 */
	private void applyDefaultFont(Component comp, Font font) {
		// ��Ʈ�� ������� ���� ���
		if (comp.getFont().getName() == "Dialog")
			comp.setFont(font);
		
		// ��ȸ
		for (Component child: ((Container)comp).getComponents())
			applyDefaultFont(child, font);
	}
}
