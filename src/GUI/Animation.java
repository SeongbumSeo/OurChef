package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Animation
{
	private Component comp;
	private int speed;
	private int destX, destY;
	private int startX, startY;
	private int directionX, directionY;
	private long startTime;
	private double distance;
	private double angle;
	private Timer timer;
	
	public Animation(Component comp) {
		this.comp = comp; // 애니메이션 대상 컴포넌트
	}
	
	public void move(int destX, int destY, int speed) {
		this.destX = destX; // 목적 좌표 X
		this.destY = destY; // 목적 좌표 Y
		this.speed = speed; // 속도
		
		startX = comp.getX(); // 출발 좌표 X
		startY = comp.getY(); // 출발 좌표 Y
		directionX = (destX > startX) ? 1 : -1; // X 방향의 양음 여부
		directionY = (destY > startY) ? 1 : -1; // Y 방향의 양음 여부
		startTime = System.nanoTime(); // 출발 시각
		distance = Math.sqrt(Math.pow(startX-destX, 2) + Math.pow(startY-destY, 2)); // 거리
		angle = Math.toRadians(Math.toDegrees(Math.atan2(startY-destY, startX-destX))); // 이동 각도
		
		// 타이머 실행
		timer = new Timer(1, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			double duration = (System.nanoTime() - startTime) / 1e6; // 출발 이후 경과 시간
			double distanceSoFar = Math.min(speed * duration / 1000d, distance); // 현재 거리
			int x = startX - (int)(distanceSoFar * Math.cos(angle)); // 이동 좌표 X
			int y = startY - (int)(distanceSoFar * Math.sin(angle)); // 이동 좌표 Y

			if ((directionX > 0 && x >= destX) || (directionX < 0 && x <= destX)
					|| (directionY > 0 && y >= destY) || (directionY < 0 && y <= destY)) { // 도착 시
				comp.setLocation(destX, destY);
				
				// 타이머 정지
				timer.stop();
				timer = null;
			} else {
				comp.setLocation(x, y);
			}
		}
	}
}
