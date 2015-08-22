package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.Color;

import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.CollisionComponent;
import development.codenmore.ld33.entities.components.LiftComponent;
import development.codenmore.ld33.entities.components.MovementComponent;
import development.codenmore.ld33.entities.components.ObjectComponent;

public class Rock extends Entity {
	
	public Rock(EntityManager manager, float x, float y){
		super(x, y, 26, 26, Assets.getRegion("rock"));
		addComponent(new CollisionComponent(this, 0, 0));
		addComponent(new MovementComponent());
		addComponent(new LiftComponent(true));
		addComponent(new ObjectComponent(3));
		setParticleColor(Color.OLIVE);
		
		manager.add(this);
	}

}
