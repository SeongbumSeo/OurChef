package GUI;

import java.awt.*;
import javax.swing.*;
import Main.*;

public class Animation
{
	// 상수
	public static final long DELAY_MOVE = 10;
	public static final long DELAY_FADE = 25;
	
	// 공통으로 사용되는 인스턴스 데이터
	private Component comp;
	private AnimationListener listener;
	private boolean wait;
	private int speed;
	private long startTime;
	private long delay;
	private Thread thread;
	
	// 이동 애니메이션에 사용
	private int destX, destY;
	private int startX, startY;
	private int directionX, directionY;
	private double distance;
	private double angle;
	
	// 페이드인/아웃 애니메이션에 사용
	private JPanel screenFader;
	private int fadeMode;
	private Color color;
	private int opacity;
	
	/**
	 * 애니메이션의 생성자입니다.
	 * @param comp 애니메이션 대상 컴포넌트
	 * @param wait 애니메이션 완료까지 대기 여부
	 */
	public Animation(Component comp, boolean wait) {
		this(comp, wait, null);
	}
	/**
	 * 애니메이션의 생성자입니다.
	 * @param comp 애니메이션 대상 컴포넌트
	 * @param wait 애니메이션 완료까지 대기 여부
	 * @param listener 애니메이션 리스너 객체
	 */
	public Animation(Component comp, boolean wait, AnimationListener listener) {
		this.comp = comp; // 애니메이션 대상 컴포넌트
		this.wait = wait; // 애니메이션 완료까지 대기 여부
		this.listener = listener; // 애니메이션 리스너 객체
		this.thread = null; // 애니메이션 쓰레드 초기화
	}
	
	/**
	 * 이동 애니메이션을 실행하는 메소드입니다.
	 * @param destX 목적 좌표 X
	 * @param destY 목적 좌표 Y
	 * @param speed 이동 속도
	 */
	public void move(int destX, int destY, int speed) {
		this.destX = destX; // 목적 좌표 X
		this.destY = destY; // 목적 좌표 Y
		this.speed = speed; // 이동 속도
		
		startX = comp.getX(); // 출발 좌표 X
		startY = comp.getY(); // 출발 좌표 Y
		directionX = (destX > startX) ? 1 : -1; // X 방향의 양음 여부
		directionY = (destY > startY) ? 1 : -1; // Y 방향의 양음 여부
		startTime = System.nanoTime(); // 출발 시각
		distance = Math.sqrt(Math.pow(startX-destX, 2) + Math.pow(startY-destY, 2)); // 거리
		angle = Math.toRadians(Math.toDegrees(Math.atan2(startY-destY, startX-destX))); // 이동 각도
		
		// 애니메이션 쓰레드 실행
		startThread(DELAY_MOVE, new MoveThread());
	}
	
	/**
	 * 페이드인 애니메이션을 실행하는 메소드입니다.
	 * @param speed 페이드 속도
	 */
	public void fadeIn(Color color, int speed) {
		this.color = color;
		this.speed = speed;

		fadeMode = 1;
		opacity = 0;
		screenFader = new JPanel();
		screenFader.setBounds(0, 0, SceneManager.getFrame().getWidth(), SceneManager.getFrame().getHeight());
		screenFader.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity));
		SceneManager.getFrame().getContentPane().add(screenFader);
		
		// 애니메이션 쓰레드 실행
		startThread(DELAY_FADE, new FadeThread());
	}
	
	/**
	 * 페이드아웃 애니메이션을 실행하는 메소드입니다.
	 * @param speed 페이드 속도
	 */
	public void fadeOut(Color color, int speed) {
		this.color = color;
		this.speed = speed;

		fadeMode = -1;
		opacity = 64;
		screenFader = new JPanel();
		screenFader.setBounds(0, 0, SceneManager.getFrame().getWidth(), SceneManager.getFrame().getHeight());
		screenFader.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity));
		SceneManager.getFrame().getContentPane().add(screenFader);
		
		// 애니메이션 쓰레드 실행
		startThread(DELAY_FADE, new FadeThread());
	}
	
	/**
	 * 애니메이션 쓰레드를 실행하는 메소드입니다.
	 * @param delay 애니메이션 작업의 실행 주기
	 * @param thread 애니메이션 쓰레드 객체
	 */
	private void startThread(long delay, Thread thread) {
		this.delay = delay;
		this.thread = thread;
		
		stopThread(); // 실행중인 애니메이션 중단
		thread.start(); // 애니메이션 쓰레드 실행
		
		if (wait) { // 애니메이션 완료까지 메인 쓰레드 대기
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 현재 실행중인 애니메이션 쓰레드를 중단하는 메소드입니다.
	 */
	private void stopThread() {
		if (thread != null)
			thread.interrupt();
		thread = null;
	}
	
	/**
	 * 이동 애니메이션 타이머의 이벤트 리스너입니다.
	 */
	private class MoveThread extends Thread
	{
		public void run() {
			double duration;
			double distanceSoFar;
			int x;
			int y;
			boolean conditionX = false;
			boolean conditionY = false;
			
			// 애니메이션 작업
			while (!this.isInterrupted() && !conditionX && !conditionY) {
				duration = (System.nanoTime() - startTime) / 1e6; // 출발 이후 경과 시간
				distanceSoFar = Math.min(speed * duration / 1000d, distance); // 현재 거리
				x = startX - (int)(distanceSoFar * Math.cos(angle)); // 이동 좌표 X
				y = startY - (int)(distanceSoFar * Math.sin(angle)); // 이동 좌표 Y
				conditionX = (directionX > 0 && x >= destX) || (directionX < 0 && x <= destX); // X 완료 조건
				conditionY = (directionY > 0 && y >= destY) || (directionY < 0 && y <= destY); // Y 완료 조건
				
				comp.setLocation(x, y); // 이동
				
				try { // 딜레이 적용
					Thread.sleep(delay);
				} catch (Exception e) {
					e.printStackTrace();
					this.interrupt();
				}
			}
			
			// 도착 시
			comp.setLocation(destX, destY);
			if (listener != null) // 리스너의 완료 콜백 호출
				listener.onCompleted();
		}
	}
	
	/**
	 * 페이드 애니메이션 타이머의 이벤트 리스너입니다.
	 */
	private class FadeThread extends Thread
	{
		public void run() {
			int lastOpacity = (fadeMode == 1) ? 64 : 0;
			
			// 애니메이션 작업
			while (!this.isInterrupted()) {
				opacity += speed * fadeMode;
				System.out.println(opacity);
				if ((fadeMode == 1 && opacity >= lastOpacity) || (fadeMode == -1 && opacity <= lastOpacity)) // 종료 조건
					break;
				
				screenFader.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity)); // 투명도 설정
				SceneManager.getFrame().setVisible(true);
				
				try { // 딜레이 적용
					Thread.sleep(delay);
				} catch (Exception e) {
					e.printStackTrace();
					this.interrupt();
				}
			}
			
			// 완료 시
			SceneManager.getFrame().getContentPane().remove(screenFader);
			if (listener != null) // 리스너의 완료 콜백 호출
				listener.onCompleted();
		}
	}
}
