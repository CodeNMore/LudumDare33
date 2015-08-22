package development.codenmore.ld33.entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Main;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.AnimationComponent;
import development.codenmore.ld33.entities.components.CollisionComponent;
import development.codenmore.ld33.entities.components.MovementComponent;
import development.codenmore.ld33.input.InputManager;

public class Player extends Entity {

	//Components
	private MovementComponent movementComponent;
	private AnimationComponent animationComponent;
	private CollisionComponent collisionComponent;
	// Movement
	private static final float velocityIncrement = 4.0f,
			rotationIncrement = 1f, maxRotation = 15f,
			rotateSpeedThreshhold = 40f;
	private float rotation = 0.0f;
	private TractorBeam beam;

	public Player() {
		super(Main.WIDTH / 2 - 32, Main.HEIGHT - 64, 64, 32, Assets.getRegion("shuttle.1"));
		beam = new TractorBeam(this);
		
		movementComponent = new MovementComponent(180f);
		addComponent(movementComponent);
		animationComponent = new AnimationComponent(new Animation(0.1f, Assets.getSeries("shuttle.", 4)));
		addComponent(animationComponent);
		collisionComponent = new CollisionComponent(this, 0, 0);
		addComponent(collisionComponent);
	}

	@Override
	public void tick(float delta) {
		getInput();
		setRotation();
		super.tick(delta);
		beam.tick(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(getTexture(), x, y, width / 2, height / 2, width, height, 1,
				1, -rotation);
		beam.render(batch);
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

	public TractorBeam getBeam() {
		return beam;
	}

	public void setBeam(TractorBeam beam) {
		this.beam = beam;
	}

}