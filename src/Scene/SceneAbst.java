package Scene;

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
		
		System.out.println(this.getClass().getSimpleName() + "�� �ε�Ǿ����ϴ�.");
	}
}
