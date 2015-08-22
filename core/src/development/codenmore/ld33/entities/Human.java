package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;

import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.AnimationComponent;
import development.codenmore.ld33.entities.components.CollisionComponent;
import development.codenmore.ld33.entities.components.HumanMovementComponent;
import development.codenmore.ld33.entities.components.HumanShooterComponent;
import development.codenmore.ld33.entities.components.LiftComponent;
import development.codenmore.ld33.entities.components.MovementComponent;

public class Human extends Entity {
	
	public Human(EntityManager manager, float x, float y, boolean shooter){
		super(x, y, 12, 20, Assets.getRegion("human.stand.1"));
		addComponent(new MovementComponent(60f));
		addComponent(new CollisionComponent(this, 0, 0));
		addComponent(new HumanMovementComponent());
		if(shooter)
			addComponent(new HumanShooterComponent());
		addComponent(new LiftComponent(false));
		
		AnimationComponent a = new AnimationComponent();
		a.setLeft(new Animation(0.2f, Assets.getSeries("human.left.", 3)));
		a.setRight(new Animation(0.2f, Assets.getSeries("human.right.", 3)));
		a.setStanding(new Animation(0.3f, Assets.getSeries("human.stand.", 2)));
		a.setShooting(new Animation(0.1f, Assets.getSeries("human.shoot.", 2)));
		addComponent(a);
		
		setParticleColor(Color.RED);
		
		manager.add(this);
	}

}
