package development.codenmore.ld33.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Main;

public class InfoState extends State {
	
	private Texture texture;
	
	public InfoState(){
		texture = new Texture(Gdx.files.internal("screens/howToPlay.png"));
	}

	@Override
	public void tick(float delta) {
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			State.popState();
			State.pushState(new AlienState());
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			batch.draw(texture, 0, 0, Main.WIDTH, Main.HEIGHT);
		}
		batch.end();
	}
	
	@Override
	public void onPop(){
		texture.dispose();
	}

}
