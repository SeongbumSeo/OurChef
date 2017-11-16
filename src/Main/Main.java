package Main;

import java.awt.*;
import javax.swing.*;
import Scene.*;

public class Main
{
	private static JFrame frame;
	
	/**
	 * ���α׷��� �� ���� �ݹ��Դϴ�.
	 * @param args ���� ����
	 */
	public static void main(String[] args) {
		showWindow("�츮�� ������", 1280, 760);
		switchScene(new IntroScene());
	}
	
	/**
	 * ���� �������� �����ϰ� �����츦 ����ȭ�ϴ� �޼ҵ��Դϴ�.
	 * @param title �������� Ÿ��Ʋ
	 * @param width ���� ũ��
	 * @param height ���� ũ��
	 * @return
	 */
	private static void showWindow(String title, int width, int height) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ������ ���� �� ���μ��� ����
		frame.setResizable(false); // ������ ���� �Ұ�
		frame.setPreferredSize(new Dimension(width, height)); // ������ ũ�� ����
		frame.pack(); // ������ ����� �����ӿ� ����
	}
	
	/**
	 * ���� ��ȯ�ϴ� �޼ҵ��Դϴ�.
	 * @param scene �� ��ü
	 */
	public static void switchScene(Scene scene) {
		System.out.println(scene.getClass().getSimpleName() + "���� ���� ��ȯ���Դϴ�.");
		
		// ���� ��(��)�� onHide �ݹ� ȣ��
		for (Component comp:frame.getContentPane().getComponents())
			if (Scene.class.isInstance(comp))
				SceneAbst.class.cast(comp).onHide();
		
		frame.getContentPane().removeAll(); // ������ �� ��� ������Ʈ(�� ����) ����
		frame.getContentPane().add(JPanel.class.cast(scene)); // �����ӿ� �� �г� �߰�
		frame.setVisible(true); // ������ ����ȭ

		System.out.println(scene.getClass().getSimpleName() + "������ �� ��ȯ�� �Ϸ�Ǿ����ϴ�.");
	}
	
	public static JFrame getFrame() { return frame; }
}
