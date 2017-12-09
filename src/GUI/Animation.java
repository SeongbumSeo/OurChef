package GUI;

import java.awt.*;
import javax.swing.*;
import Main.*;

public class Animation
{
	// 상수
	public static final long DELAY_MOVE = 10; // 이동 애니메이션 딜레이
	public static final long DELAY_FADE = 25; // 페이드인/아웃 애니메이션 딜레이
	
	// 공통으로 사용되는 인스턴스 데이터
	private Component comp; // 애니메이션 대상 컴포넌트
	private AnimationListener listener; // 애니메이션의 이벤트 리스너
	private boolean wait; // 애니메이션 완료까지 메인 쓰레드 대기 여부
	private int speed; // 애니메이션 속도
	private long startTime; // 애니메이션 출발 시각
	private long delay; // 애니메이션 딜레이
	private Thread thread; // 애니메이션 쓰레드
	
	// 이동 애니메이션에 사용
	private int destX, destY; // 목적 좌표
	private int startX, startY; // 출발 좌표
	private int directionX, directionY; // 이동 경로의 양음 여부
	private double distance; // 거리
	private double angle; // 이동 각도
	
	// 페이드인/아웃 애니메이션에 사용
	private JPanel screenFader; // 페이더 오브젝트
	private int fadeMode; // 페이드인/아웃 모드
	private Color color; // 페이더 색상
	private int opacity; // 페이더 투명도
	
	/**
	 * 애니메이션의 생성자입니다.
	 * @param comp 애니메이션 대상 컴포넌트
	 * @param wait 애니메이션 완료까지 메인 쓰레드 대기 여부
	 */
	public Animation(Component comp, boolean wait) {
		this(comp, wait, null);
	}
	/**
	 * 애니메이션의 생성자입니다.
	 * @param comp 애니메이션 대상 컴포넌트
	 * @param wait 애니메이션 완료까지 메인 쓰레드 대기 여부
	 * @param listener 애니메이션 리스너 객체
	 */
	public Animation(Component comp, boolean wait, AnimationListener listener) {
		this.comp = comp; // 애니메이션 대상 컴포넌트
		this.wait = wait; // 애니메이션 완료까지 메인 쓰레드 대기 여부
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

		fadeMode = 1; // 페이드인 모드
		opacity = 0; // 페이더 투명도 0(최소)에서 시작
		screenFader = new JPanel(); // 페이더 오브젝트 생성
		screenFader.setBounds(0, 0, SceneManager.getFrame().getWidth(), SceneManager.getFrame().getHeight()); // 페이더 사이즈 설정
		screenFader.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity)); // 페이더 컬러 및 투명도 설정
		SceneManager.getFrame().getContentPane().add(screenFader); // 페이더 추가
		
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

		fadeMode = -1; // 페이드아웃 모드
		opacity = 64; // 페이더 투명도 64(최대)에서 시작
		screenFader = new JPanel(); // 페이더 오브젝트 생성
		screenFader.setBounds(0, 0, SceneManager.getFrame().getWidth(), SceneManager.getFrame().getHeight()); // 페이더 사이즈 설정
		screenFader.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity)); // 페이더 컬러 및 투명도 설정
		SceneManager.getFrame().getContentPane().add(screenFader); // 페이더 추가
		
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
			double duration; // 출발 이후 경과 시간
			double distanceSoFar; // 현재 거리
			int x; // 이동 좌표 X
			int y; // 이동 좌표 Y
			boolean conditionX = false; // X 도착 조건
			boolean conditionY = false; // Y 도착 조건
			
			// 애니메이션 작업
			while (!this.isInterrupted() && !conditionX && !conditionY) {
				duration = (System.nanoTime() - startTime) / 1e6; // 출발 이후 경과 시간
				distanceSoFar = Math.min(speed * duration / 1000d, distance); // 현재 거리
				x = startX - (int)(distanceSoFar * Math.cos(angle)); // 이동 좌표 X
				y = startY - (int)(distanceSoFar * Math.sin(angle)); // 이동 좌표 Y
				conditionX = (directionX > 0 && x >= destX) || (directionX < 0 && x <= destX); // X 도착 조건
				conditionY = (directionY > 0 && y >= destY) || (directionY < 0 && y <= destY); // Y 도착 조건
				
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
				opacity += speed * fadeMode; // 페이더의 새 투명도
				if ((fadeMode == 1 && opacity >= lastOpacity) || (fadeMode == -1 && opacity <= lastOpacity)) // 종료 조건
					break;
				
				screenFader.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity)); // 투명도 설정
				SceneManager.getFrame().repaint(); // 프레임 리페인트
				
				try { // 딜레이 적용
					Thread.sleep(delay);
				} catch (Exception e) {
					e.printStackTrace();
					this.interrupt(); // 예외 발생 시 쓰레드 중지
				}
			}
			
			// 완료 시
			SceneManager.getFrame().getContentPane().remove(screenFader); // 페이더 제거
			if (listener != null) // 리스너의 완료 콜백 호출
				listener.onCompleted();
		}
	}
}
