package Scene;

import java.awt.*;
import javax.swing.*;
import Main.Main;

public abstract class SceneAbst extends JPanel implements Scene
{
	/**
	 * 추상클래스 SceneAbst의 생성자입니다.
	 */
	public SceneAbst() {
		setPreferredSize(new Dimension(Main.getFrame().getWidth(), Main.getFrame().getHeight())); // 프레임 크기에 맞춤
		setBackground(Color.white); // 바탕색 설정
		setLayout(null); // 배치관리자 제거

		System.out.println(this.getClass().getSimpleName() + "의 추상클래스가 로드되었습니다.");
		
		onShow();
		
		System.out.println(this.getClass().getSimpleName() + "이 로드되었습니다.");
	}
}
