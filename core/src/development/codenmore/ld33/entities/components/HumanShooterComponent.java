package development.codenmore.ld33.entities.components;

import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld33.entities.Entity;

public class HumanShooterComponent extends Component {
	
	//ID
	public static final int ID = 5;
	//Data
	private boolean shooting = false;
	private float waitTimer = 0f, fireTimer = 0f, waitTime, timeToShoot,
				shootTimer = 0f, shootTime = 0.3f;
	private int bulletsToShoot;
	
	public HumanShooterComponent(){
		super(ID);
		timeToShoot = 0.4f;
		reset();
	}

	@Override
	public void tick(Entity e, float delta) {
		LiftComponent lc = e.getComponent(LiftComponent.ID);
		HumanMovementComponent hmc = e.getComponent(HumanMovementComponent.ID);
		if(lc.isLifting())
			return;
		waitTimer += delta;
		if(waitTimer > waitTime){
			//Firing sequence
			fireTimer += delta;
			if(fireTimer > timeToShoot){
				//Fire a bullet
				System.out.println("FIRING BULLET!");
				
				shooting = true;
				bulletsToShoot--;
				timeToShoot = MathUtils.random(0.4f, 1.0f);
				fireTimer = 0f;
				if(bulletsToShoot <= 0)
					reset();
			}
		}
		
		if(shooting){
			shootTimer += delta;
			if(hmc.isActive()){
				hmc.setActive(false);
				hmc.resetPause();
			}
			if(shootTimer > shootTime){
				shooting = false;
				shootTimer = 0f;
				if(!hmc.isActive()){
					hmc.setActive(true);
				}
			}
		}
	}
	
	private void reset(){
		waitTimer = 0f;
		waitTime = MathUtils.random(2.5f, 7.5f);
		bulletsToShoot = MathUtils.random(1, 3);
	}
	
	//GETTERS SETTERS

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

}
