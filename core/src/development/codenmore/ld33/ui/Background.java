package development.codenmore.ld33.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
	
	//Data
	private TextureRegion texture;
	private float x, y;
	private int width, height;
	private float speed, position = 0f;
	
	public Background(TextureRegion texture, float x, float y, int width, int height, float speed){
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}
	
	public void tick(float delta){
		position += speed * delta;
		if(position >= height)
			position = 0f;
	}
	
	public void render(SpriteBatch batch){
		batch.draw(texture, x, y + position, width, height);
		batch.draw(texture, x, y + position - height, width, height);
	}

}
