package development.codenmore.ld33.entities;

import com.badlogic.gdx.graphics.g2d.Animation;

import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.components.AnimationComponent;
import development.codenmore.ld33.entities.components.HumanShooterComponent;

public class HumanShooter extends Human {
	
	public HumanShooter(EntityManager manager, float x, float y){
		super(manager, x, y);
		addComponent(new HumanShooterComponent());
		AnimationComponent a = getComponent(AnimationComponent.ID);
		a.setLeft(new Animation(0.2f, Assets.getSeries("human.left.g.", 3)));
		a.setRight(new Animation(0.2f, Assets.getSeries("human.right.g.", 3)));
		a.setStanding(new Animation(0.3f, Assets.getSeries("human.stand.g.", 2)));
		a.setShooting(new Animation(0.1f, Assets.getSeries("human.shoot.g.", 2)));
	}
	
	public HumanShooter(){
		this(null, 0, 0);
	}

}
