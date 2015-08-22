package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.CollisionComponent;

public class Turret extends Entity {
	
	//Timers
	private float timer = 0f, shootTime;
	
	
	public Turret(EntityManager manager, float x, float y, float shootTime){
		super(x, y, 26, 26, Assets.getRegion("turret"));
		this.shootTime = shootTime;
		addComponent(new CollisionComponent(this, 0, 0));
		setParticleColor(Color.OLIVE);
		
		manager.add(this);
	}
	
	@Override
	public void tick(float delta){
		super.tick(delta);
		
		timer += delta;
		if(timer > shootTime){
			timer = 0f;
			Player player = Handler.getLevel().getEntityManager().getPlayer();
			float x = this.x + width / 2;
			float y = this.y + height;
			float x2t = player.getX() + player.getWidth() / 2;
			float x2 = MathUtils.random(x2t - player.getWidth() / 1.5f, x2t + player.getWidth() / 1.5f);
			float y2 = player.getY() + player.getHeight() / 2;
			float angleR = MathUtils.atan2(y2 - y, x2 - x);
			float angleX = MathUtils.cos(angleR);
			float angleY = MathUtils.sin(angleR);
			Handler.getLevel().getEntityManager().addBullet(new Bullet(Handler.getLevel().getEntityManager(), 
					x, y, angleX, angleY));
		}
	}

}
