package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Main;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.MovementComponent;

public class Player extends Entity {

	public Player() {
		super(Main.WIDTH / 2 - 32, Main.HEIGHT - 64, 64, 32, Assets.getRegion("shuttle"));
		addComponent(new MovementComponent());
	}
	
	@Override
	public void tick(float delta){
		super.tick(delta);
	}
	
	@Override
	public void render(SpriteBatch batch){
		super.render(batch);
	}

}
