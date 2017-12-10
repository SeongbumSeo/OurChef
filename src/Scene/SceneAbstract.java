package Scene;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import Main.*;

public abstract class SceneAbstract extends JPanel implements Scene
{
	Font defaultFont;
	
	/**
	 * 추상클래스 SceneAbst의 생성자입니다.
	 */
	public SceneAbstract() {
		setPreferredSize(new Dimension(SceneManager.getFrame().getWidth(), SceneManager.getFrame().getHeight())); // 프레임 크기에 맞춤
		setBackground(Color.white); // 바탕색 설정
		setLayout(null); // 배치관리자 제거
		
		onShow();
		applyDefaultFont("JejuGothic"); // 기본 폰트 적용
	}
	/**
	 * 폰트 파일을 로드합니다.
	 * @param name
	 * @param size
	 * @return 폰트
	 */
	public Font loadFont(String name, int size) {
		try {
			// 폰트 파일 로드
			File file = new File("fonts/" + name + ".ttf");
			Font fontBase = Font.createFont(Font.TRUETYPE_FONT, file);
			return fontBase.deriveFont(Font.PLAIN, size);
		} catch (Exception e) { }
		return new Font(Font.SANS_SERIF, Font.PLAIN, size);
	}
	/**
	 * 현재 기본 폰트를 반환합니다.
	 * @return 기본 폰트
	 */
	public Font getDefaultFont() {
		return defaultFont;
	}
	/**
	 * 폰트가 적용되지 않은 컴포넌트에 기본 폰트를 적용하는 메소드입니다.
	 */
	private void applyDefaultFont(String name) {
		defaultFont = loadFont(name, 40);
		// 폰트 적용
		applyDefaultFont(this, defaultFont);
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
