package development.codenmore.ld33.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld33.assets.Assets;

public class Particle {
	
	private static TextureRegion texture = Assets.getRegion("color");
	private float x, y;
	private int width, height;
	private float vx, vy;
	private float life, maxLife;
	
	public Particle(float x, float y, int width, int height, float vx, float vy, float maxLife){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vx = vx;
		this.vy = vy;
		this.maxLife = maxLife;
		life = 0;
	}
	
	public boolean tick(float delta){
		x += vx * delta;
		y += vy * delta;
		life += delta;
		if(life > maxLife)
			return true;
		return false;
	}
	
	public void render(SpriteBatch batch){
		batch.draw(texture, x, y, width, height);
	}

}
