package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.particles.ParticleEmitter;

public class TractorBeam {

	// Other stuff
	private Entity entity;
	private static final float SPEED = 500f, RETRACT_SPEED = SPEED, OFFSET = 3f;
	public static final float BEAMENERGYTIME = 7.5f, REFILL = BEAMENERGYTIME / 2f;
	private static final Color brownColor = new Color(.64f, .18f, .18f, 1.0f);
	private static TextureRegion texture = Assets.getRegion("beam");
	private float y = 0f;
	private int width = 34, height = 1;
	// Beam specifications
	private boolean emit = false, doDraw = false;
	private boolean hitFloor = false;
	private float floorTimer = 0f, floorTime = 0.2f;
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
				if(!hitFloor){
					hitFloor = true;
					floorTimer = 0f;
					Handler.getLevel().getEntityManager().addEmitter(new ParticleEmitter(
							20, 0f, brownColor,
							Handler.getLevel().getEntityManager().getPlayer().getX() + 32,
							y + 16, 3, 3, 20, 25, 190, 350, 1, 1.5f));
				}
				y = Handler.getLevel().getGroundLevel();
				height = (int) (entity.getY() - Handler.getLevel().getGroundLevel() + OFFSET);
			}else{
				floorTimer += delta;
				if(floorTimer > floorTime){					
					hitFloor = false;
				}
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
	
	public void setTimer(float timer){
		this.timer = timer;
	}

}
