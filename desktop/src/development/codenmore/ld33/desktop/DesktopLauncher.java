package development.codenmore.ld33.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import development.codenmore.ld33.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = Main.TITLE;
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		
		new LwjglApplication(new Main(), config);
	}
}
