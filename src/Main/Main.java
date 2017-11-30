package Main;

import java.awt.*;
import javax.swing.*;
import Scene.*;

public class Main
{
	private static JFrame frame;
	
	/**
	 * 프로그램의 주 실행 콜백입니다.
	 * @param args 실행 인자
	 */
	public static void main(String[] args) {
		showWindow("우리집 셰프님", 1600, 900);
		switchScene(new IntroScene());
	}
	
	/**
	 * 메인 프레임을 생성하고 윈도우를 가시화하는 메소드입니다.
	 * @param title 윈도우의 타이틀
	 * @param width 가로 크기
	 * @param height 세로 크기
	 * @return
	 */
	private static void showWindow(String title, int width, int height) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 윈도우 종료 시 프로세스 정리
		frame.setResizable(false); // 사이즈 조정 불가
		frame.setPreferredSize(new Dimension(width, height)); // 프레임 크기 지정
		frame.pack(); // 윈도우 사이즈를 프레임에 맞춤
	}
	
	/**
	 * 씬을 전환하는 메소드입니다.
	 * @param scene 씬 객체
	 */
	public static void switchScene(Scene scene) {
		// 기존 씬(들)의 onHide 콜백 호출
		for (Component comp: frame.getContentPane().getComponents())
			if (Scene.class.isInstance(comp))
				Scene.class.cast(comp).onHide();
		
		frame.getContentPane().removeAll(); // 프레임 내 모든 컴포넌트(씬 포함) 삭제
		frame.getContentPane().add(JPanel.class.cast(scene)); // 프레임에 씬 패널 추가
		frame.setVisible(true); // 윈도우 가시화
	}
	
	public static JFrame getFrame() { return frame; }
}
