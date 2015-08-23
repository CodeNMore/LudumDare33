package development.codenmore.ld33.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import development.codenmore.ld33.assets.Assets;

public class InputManager implements InputProcessor {
	
	private static boolean[] keysDown = new boolean[256];
	
	public InputManager(){}
	
	//METHODS
	
	public static boolean isKeyDown(int code){
		if(code >= keysDown.length)
			return false;
		return keysDown[code];
	}
	
	//PROCESSOR

	@Override
	public boolean keyDown(int keycode) {
		if(keycode >= keysDown.length)
			return false;
		keysDown[keycode] = true;
		if(keycode == Keys.ESCAPE)
			Gdx.app.exit();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode >= keysDown.length)
			return false;
		keysDown[keycode] = false;
		if(keycode == Keys.M){
			if(Assets.getMusic().isPlaying())
				Assets.getMusic().stop();
			else
				Assets.getMusic().play();
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
