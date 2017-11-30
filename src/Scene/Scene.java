package Scene;

public interface Scene
{
	/**
	 * 씬이 보여질 때 호출되는 콜백입니다.
	 */
	public void onShow();
	
	/**
	 * 씬이 숨겨질 때 호출되는 콜백입니다.
	 */
	public void onHide();
}
