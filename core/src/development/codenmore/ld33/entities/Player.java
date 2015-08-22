package development.codenmore.ld33.entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import development.codenmore.ld33.Main;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.AnimationComponent;
import development.codenmore.ld33.entities.components.CollisionComponent;
import development.codenmore.ld33.entities.components.MovementComponent;
import development.codenmore.ld33.entities.components.PlayerComponent;
import development.codenmore.ld33.input.InputManager;
import development.codenmore.ld33.ui.HUD;

public class Player extends Entity {

	//Components
	private MovementComponent movementComponent;
	private AnimationComponent animationComponent;
	private CollisionComponent collisionComponent;
	// Movement
	private static final float velocityIncrement = 6.0f,
			rotationIncrement = 0.5f, maxRotation = 5f,
			rotateSpeedThreshhold = 2f;
	private float rotation = 0.0f;
	private TractorBeam beam;
	//Overlay
	private Animation overlayAnimation;
	private TextureRegion overlayTexture = Assets.getRegion("shuttle.overlay.1");
	private float animTimer = 0f;
	//UI
	private HUD hud;

	public Player() {
		super(Main.WIDTH / 2 - 32, Main.HEIGHT - 64, 64, 32, Assets.getRegion("shuttle.1"));
		beam = new TractorBeam(this);
		hud = new HUD();
		setParticleColor(Color.BLACK);
		overlayAnimation = new Animation(0.07f, Assets.getSeries("shuttle.overlay.", 2));
		overlayAnimation.setPlayMode(PlayMode.LOOP);
		
		addComponent(new PlayerComponent());
		movementComponent = new MovementComponent(180f);
		addComponent(movementComponent);
		animationComponent = new AnimationComponent(new Animation(0.07f, Assets.getSeries("shuttle.", 2)));
		addComponent(animationComponent);
		collisionComponent = new CollisionComponent(this, 0, 0);
		addComponent(collisionComponent);
	}

	@Override
	public void tick(float delta) {
		getInput();
		setRotation();
		super.tick(delta);
		animTimer += delta;
		overlayTexture = overlayAnimation.getKeyFrame(animTimer);
		beam.tick(delta);
		hud.tick(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getTexture(), x, y, width / 2, height / 2, width, height, 1,
				1, -rotation);
		batch.setColor(hud.getHealthColor());
		batch.draw(overlayTexture, x + 6, y + 4, 26, 12, 52, 24, 1, 1, -rotation);
		batch.setColor(Color.WHITE);
		
		beam.render(batch);
		hud.render(batch);
	}

	private void getInput() {
		if (InputManager.isKeyDown(Keys.A) || InputManager.isKeyDown(Keys.LEFT)) {
			movementComponent.incX(-velocityIncrement);
			if (movementComponent.getX() < -movementComponent.getSpeed())
				movementComponent.setX(-movementComponent.getSpeed());
		} else if (InputManager.isKeyDown(Keys.D)
				|| InputManager.isKeyDown(Keys.RIGHT)) {
			movementComponent.incX(velocityIncrement);
			if (movementComponent.getX() > movementComponent.getSpeed())
				movementComponent.setX(movementComponent.getSpeed());
		} else {
			if (movementComponent.getX() > 0) {
				movementComponent.incX(-velocityIncrement);
				if (movementComponent.getX() < 0)
					movementComponent.setX(0);
			} else if (movementComponent.getX() < 0) {
				movementComponent.incX(velocityIncrement);
				if (movementComponent.getX() > 0)
					movementComponent.setX(0);
			}
		}
		
		if (InputManager.isKeyDown(Keys.S) || InputManager.isKeyDown(Keys.DOWN)) {
			movementComponent.incY(-velocityIncrement);
			if (movementComponent.getY() < -movementComponent.getSpeed())
				movementComponent.setY(-movementComponent.getSpeed());
		} else if (InputManager.isKeyDown(Keys.W) || InputManager.isKeyDown(Keys.UP)) {
			movementComponent.incY(velocityIncrement);
			if (movementComponent.getY() > movementComponent.getSpeed())
				movementComponent.setY(movementComponent.getSpeed());
		} else {
			if (movementComponent.getY() > 0) {
				movementComponent.incY(-velocityIncrement);
				if (movementComponent.getY() < 0)
					movementComponent.setY(0);
			} else if (movementComponent.getY() < 0) {
				movementComponent.incY(velocityIncrement);
				if (movementComponent.getY() > 0)
					movementComponent.setY(0);
			}
		}
		
		if(InputManager.isKeyDown(Keys.SPACE)){
			beam.setEmit(true);
		}else{
			beam.setEmit(false);
		}
	}

	private void setRotation() {
		if (movementComponent.getX() < -rotateSpeedThreshhold) {
			rotation -= rotationIncrement;
			if (rotation < -maxRotation)
				rotation = -maxRotation;
		} else if (movementComponent.getX() > rotateSpeedThreshhold) {
			rotation += rotationIncrement;
			if (rotation > maxRotation)
				rotation = maxRotation;
		} else {
			if (rotation < 0) {
				rotation += rotationIncrement;
				if (rotation > 0)
					rotation = 0;
			} else if (rotation > 0) {
				rotation -= rotationIncrement;
				if (rotation < 0)
					rotation = 0;
			}
		}
	}
	
	public void damage(float i){
		hud.damage(i);
	}
	
	@Override//TODO: NOT WORKING
	public void setRemove(boolean remove){
		this.setRemove(remove);
		if(remove == false){
			System.out.println("REMOVE PLAYER");
		}
	}
	
	@Override
	public void checkBounds(){
		if(x < 0){
			x = 0;
			movementComponent.setX(0);
		}else if(x + width > Main.WIDTH){
			x = Main.WIDTH - width;
			movementComponent.setX(0);
		}else if(y + height > Main.HEIGHT){
			y = Main.HEIGHT - height;
			movementComponent.setY(0);
		}else if(y < 330){
			y = 330;
			movementComponent.setY(0);
		}
	}

	public TractorBeam getBeam() {
		return beam;
	}

	public void setBeam(TractorBeam beam) {
		this.beam = beam;
	}

}
