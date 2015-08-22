package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.Main;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.CollisionComponent;

public class Bullet {
	
	//Info
	private static TextureRegion texture = Assets.getRegion("color");
	private static final float SPEED = 400f;
	private float x, y, vx, vy;
	
	public Bullet(EntityManager manager, float x, float y, float vx, float vy){
		this.x = x;
		this.y = y;
		this.vx = vx * SPEED;
		this.vy = vy * SPEED;
		
		manager.addBullet(this);
	}
	
	public boolean tick(float delta){
		x += vx * delta;
		y += vy * delta;
		
		if(x < 0 || y < 0 || x > Main.WIDTH || y > Main.HEIGHT)
			return true;
		
		Player player = Handler.getLevel().getEntityManager().getPlayer();
		if(((CollisionComponent) player.getComponent(CollisionComponent.ID)).getBounds()
				.overlaps(getBounds())){
			player.damage(0.5f);
			return true;
		}
		
		return false;
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(Color.LIGHT_GRAY);
		batch.draw(texture, x, y, 3, 3);
		batch.setColor(Color.WHITE);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, 3, 3);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
