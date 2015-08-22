package development.codenmore.ld33.entities.components;

import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.Main;
import development.codenmore.ld33.entities.Entity;
import development.codenmore.ld33.entities.Player;
import development.codenmore.ld33.entities.TractorBeam;

public class HumanMovementComponent extends Component {
	
	//ID
	public static final int ID = 1;
	//Info
	private static final int MIN_DIST = 48, MAX_DIST = 256, THRESH = 48;
	private static final float MIN_PAUSE = 0.4f, MAX_PAUSE = 1.6f;
	//Components
	private MovementComponent mc;
	private LiftComponent lc;
	//Data
	private boolean active = true;
	private boolean wandering = false, walkRight = false;
	private float stopWalkingAt = 0f;
	private float pauseTime, timer = 0f;
	
	public HumanMovementComponent(){
		super(ID);
		pauseTime = MathUtils.random(0.2f, 1.5f);
	}

	@Override
	public void tick(Entity e, float delta) {
		//Check if lifted by beam
		TractorBeam beam = Handler.getLevel().getEntityManager().getPlayer().getBeam();
		mc = e.getComponent(MovementComponent.ID);
		if(beam.isValid()){
			CollisionComponent cc = e.getComponent(CollisionComponent.ID);
			lc = e.getComponent(LiftComponent.ID);
			if(!lc.isLifting() && lc.shouldBeginLifting(cc, beam)){// Begin to be lifted
				lc.beginLifting(new LiftCallback(){
					@Override
					public void onLiftStop() {
						resetPause();
						active = true;
					}});
				active = false;
			}
		}
		//For movements
		if(!active)
			return;
		timer += delta;
		if(!wandering && timer >= pauseTime){
			wandering = true;
			walkRight = MathUtils.randomBoolean();
			
			if(e.getX() < THRESH){
				walkRight = true;
			}else if(e.getX() + e.getWidth() > Main.WIDTH - THRESH){
				walkRight = false;
			}else if(beam.isEmitting()){
				Player p = Handler.getLevel().getEntityManager().getPlayer();
				if(e.getX() < p.getX() + p.getWidth() / 2){
					walkRight = false;
				}else{
					walkRight = true;
				}
			}
			
			if(walkRight){
				stopWalkingAt = e.getX() + MathUtils.random(MIN_DIST, MAX_DIST);
			}else{
				stopWalkingAt = e.getX() - MathUtils.random(MIN_DIST, MAX_DIST);
			}
			
			if(stopWalkingAt < 0){
				stopWalkingAt = 0;
			}else if(stopWalkingAt > Main.WIDTH - e.getWidth()){
				stopWalkingAt = Main.WIDTH - e.getWidth();
			}
		}else if(wandering){
			if(!walkRight){
				mc.setX(-mc.getSpeed());
			}else{
				mc.setX(mc.getSpeed());
			}
			
			if(walkRight){
				if(e.getX() >= stopWalkingAt)
					resetPause();
			}else{
				if(e.getX() <= stopWalkingAt)
					resetPause();
			}
		}
	}
	
	public void resetPause(){
		wandering = false;
		pauseTime = MathUtils.random(MIN_PAUSE, MAX_PAUSE);
		timer = 0f;
		mc.setX(0);
	}
	
	//GETTERS SETTERS

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
