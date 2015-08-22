package development.codenmore.ld33.entities.components;

import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.entities.Bullet;
import development.codenmore.ld33.entities.Entity;
import development.codenmore.ld33.entities.Player;

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
				Player player = Handler.getLevel().getEntityManager().getPlayer();
				float x = e.getX() + e.getWidth() / 2;
				float y = e.getY() + e.getHeight();
				float x2t = player.getX() + player.getWidth() / 2;
				float x2 = MathUtils.random(x2t - player.getWidth() / 1.5f, x2t + player.getWidth() / 1.5f);
				float y2 = player.getY() + player.getHeight() / 2;
				float angleR = MathUtils.atan2(y2 - y, x2 - x);
				float angleX = MathUtils.cos(angleR);
				float angleY = MathUtils.sin(angleR);
				new Bullet(Handler.getLevel().getEntityManager(), x, y, angleX, angleY);
				
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
