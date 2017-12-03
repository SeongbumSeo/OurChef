package Scene;

import java.io.*;
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
		
		onShow();
		applyDefaultFont("JejuGothic");
	}
	
	/**
	 * 폰트가 적용되지 않은 컴포넌트에 기본 폰트를 적용하는 메소드입니다.
	 */
	private void applyDefaultFont(String name) {
		try {
			// 폰트 파일 로드
			File file = new File("fonts/" + name + ".ttf");
			Font fontBase = Font.createFont(Font.TRUETYPE_FONT, file);
			Font fontReal = fontBase.deriveFont(Font.PLAIN, 40);
			
			// 폰트 적용
			applyDefaultFont(this, fontReal);
		} catch (Exception e) { }
	}
	/**
	 * 전체 컴포넌트를 전위순회하며 기본 폰트를 적용하는 메소드입니다.
	 */
	private void applyDefaultFont(Component comp, Font font) {
		// 폰트가 적용되지 않은 경우
		if (comp.getFont().getName() == "Dialog")
			comp.setFont(font);
		
		// 순회
		for (Component child: ((Container)comp).getComponents())
			applyDefaultFont(child, font);
	}
}
