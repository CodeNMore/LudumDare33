package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.assets.Assets;

public class TractorBeam {

	// Other stuff
	private Entity entity;
	private static final float SPEED = 500f, RETRACT_SPEED = SPEED, OFFSET = 3f;
	public static final float BEAMENERGYTIME = 7.5f, REFILL = BEAMENERGYTIME / 2f;
	private static TextureRegion texture = Assets.getRegion("beam");
	private float y = 0f;
	private int width = 34, height = 1;
	// Beam specifications
	private boolean emit = false, doDraw = false;
	// Energy specifications
	private float timer = 0f;
	private boolean powerOut = false;

	public TractorBeam(Entity entity) {
		this.entity = entity;
	}

	public void tick(float delta) {
		if (!powerOut && emit) {
			timer += delta;
			if(timer >= BEAMENERGYTIME){
				powerOut = true;
				return;
			}
			
			height += SPEED * delta;
			y = entity.getY() - height + OFFSET;
			if(y < Handler.getLevel().getGroundLevel()){
				y = Handler.getLevel().getGroundLevel();
				height = (int) (entity.getY() - Handler.getLevel().getGroundLevel() + OFFSET);
			}
			doDraw = true;
		}else if(doDraw){
			height -= RETRACT_SPEED * delta;
			y = entity.getY() - height + OFFSET;
			if(height <= 0){
				doDraw = false;
				height = 0;
			}
		}else if(powerOut){
			if(!emit)
				powerOut = false;
		}
		
		if(!emit){
			timer -= REFILL * delta;
			if(timer < 0)
				timer = 0;
		}
	}

	public void render(SpriteBatch batch) {
		if (doDraw) {
			batch.draw(texture, entity.getX() + entity.getWidth() / 2 - width / 2,
					y, width, height);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(entity.getX() + entity.getWidth() / 2 - width / 2, y, width, height);
	}
	
	public boolean isValid(){
		return emit || doDraw;
	}

	// GETTERS SETTERS

	public boolean isEmitting() {
		return emit;
	}

	public void setEmit(boolean emit) {
		this.emit = emit;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public float getTimer(){
		return timer;
	}

}
