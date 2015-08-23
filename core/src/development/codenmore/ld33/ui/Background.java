package development.codenmore.ld33.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.assets.Assets;

public class Background {
	
	//Cycle
	private enum NightCycle{
		DARK(Assets.getRegion("blackSky")),
		MEDIUM(Assets.getRegion("blueSky")),
		LIGHT(Assets.getRegion("lightSky"));
		
		private TextureRegion texture;
		
		NightCycle(TextureRegion texture){
			this.texture = texture;
		}
		
		public TextureRegion getTexture(){
			return texture;
		}
	};
	//Data
	private NightCycle nightCycle;
	private float x, y, nightTime;
	private int width, height;
	private float speed, position = 0f;
	private float timer = 0f, timePerImage;
	
	public Background(float nightTime, float x, float y, int width, int height, float speed){
		this.nightTime = nightTime;
		nightCycle = NightCycle.DARK;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		
		timePerImage = height / speed / 2;
	}
	
	public void tick(float delta){
		timer += delta;
		if(nightCycle == NightCycle.DARK){
			if(timer > nightTime * 0.6f)
				nightCycle = NightCycle.MEDIUM;
		}else if(nightCycle == NightCycle.MEDIUM){
			if(timer >= nightTime - timePerImage)
				nightCycle = NightCycle.LIGHT;
		}else{
			if(timer >= nightTime){
				Handler.getLevel().getGameState().getDay().endNight(false, false);
			}
		}
		position += speed * delta;
		if(position >= height)
			position = 0f;
	}
	
	public void render(SpriteBatch batch){
		batch.draw(nightCycle.getTexture(), x, y + position, width, height);
		batch.draw(nightCycle.getTexture(), x, y + position - height, width, height);
	}

	public float getNightTime() {
		return nightTime;
	}

	public void setNightTime(float nightTime) {
		this.nightTime = nightTime;
	}

}
