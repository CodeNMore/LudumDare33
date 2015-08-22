package development.codenmore.ld33.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.level.Level;

public class GameState extends State {
	
	//Level
	private Level level;
	
	public GameState(){
		level = new Level();
		Handler.setLevel(level);
	}

	@Override
	public void tick(float delta) {
		level.tick(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			level.render(batch);
		}
		batch.end();
	}

}
