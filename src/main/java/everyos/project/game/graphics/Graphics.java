package everyos.project.game.graphics;

public class Graphics {
	public static int SCREEN_WIDTH = 55;
	public static int SCREEN_HEIGHT = 20;
	public static void setSize(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;
	}
	public static int getHeight() {return SCREEN_HEIGHT;}
	public static int getWidth() {return SCREEN_WIDTH;}
	
	public void clear() {
		
	}
}
