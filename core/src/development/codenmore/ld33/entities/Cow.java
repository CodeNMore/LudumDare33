package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.Color;

import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.CollisionComponent;
import development.codenmore.ld33.entities.components.HumanMovementComponent;
import development.codenmore.ld33.entities.components.LiftComponent;
import development.codenmore.ld33.entities.components.MovementComponent;

public class Cow extends Entity {
	
	public Cow(EntityManager manager, float x, float y){
		super(x, y, 26, 24, Assets.getRegion("cow"));
		addComponent(new MovementComponent(50f));
		addComponent(new CollisionComponent(this, 0, 0));
		addComponent(new HumanMovementComponent());
		addComponent(new LiftComponent(false));
		setParticleColor(Color.RED);
		
		manager.add(this);
	}

}
