package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;

import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.AnimationComponent;
import development.codenmore.ld33.entities.components.CollisionComponent;
import development.codenmore.ld33.entities.components.HumanMovementComponent;
import development.codenmore.ld33.entities.components.LiftComponent;
import development.codenmore.ld33.entities.components.MovementComponent;

public class Cow extends Entity {
	
	public Cow(EntityManager manager, float x, float y){
		super(x, y, 32, 29, Assets.getRegion("cow"));
		addComponent(new MovementComponent(50f));
		addComponent(new CollisionComponent(this, 0, 0));
		addComponent(new HumanMovementComponent());
		addComponent(new LiftComponent(false));
		
		AnimationComponent a = new AnimationComponent();
		a.setLeft(new Animation(0.2f, Assets.getSeries("cow.left.", 3)));
		a.setRight(new Animation(0.2f, Assets.getSeries("cow.right.", 3)));
		a.setStanding(new Animation(0.3f, Assets.getSeries("cow.stand.", 2)));
		addComponent(a);
		
		setParticleColor(Color.RED);
		
		if(manager != null)
			manager.add(this);
	}
	
	public Cow(){
		this(null, 0, 0);
	}

}
