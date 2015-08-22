package development.codenmore.ld33.entities.components;

import com.badlogic.gdx.math.Rectangle;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.entities.Entity;
import development.codenmore.ld33.entities.Player;
import development.codenmore.ld33.entities.TractorBeam;

public class LiftComponent extends Component {

	// ID
	public static final int ID = 4;
	// Data
	private static final float SPEED = 1.2f;
	private LiftCallback callback;
	private boolean lift = false, centered = false, isObject = false;

	public LiftComponent(boolean isObject) {
		super(ID);
		this.isObject = isObject;
	}

	@Override
	public void tick(Entity e, float delta) {
		//OBJECT STUFF
		if(isObject){
			if(!lift && shouldBeginLifting((CollisionComponent) e.getComponent(CollisionComponent.ID),
					Handler.getLevel().getEntityManager().getPlayer().getBeam())){
				beginLifting(null);
			}
		}
		//Other
		MovementComponent mc = e.getComponent(MovementComponent.ID);
		// If not on ground, fall down
		if (!lift && e.getY() > Handler.getLevel().getGroundLevel()) {
			mc.incY(-9.8f);
			if(isObject){
				ObjectComponent oc = e.getComponent(ObjectComponent.ID);
				oc.setFalling(true);
			}
		} else if (!lift && e.getY() <= Handler.getLevel().getGroundLevel()) {
			mc.setY(0);
			e.setY(Handler.getLevel().getGroundLevel());
			if(isObject){
				ObjectComponent oc = e.getComponent(ObjectComponent.ID);
				oc.setFalling(false);
			}
		}
		// Lift stuff
		if (!lift)
			return;
		CollisionComponent cc = e.getComponent(CollisionComponent.ID);
		Player player = Handler.getLevel().getEntityManager().getPlayer();
		
		mc.incY(SPEED);
		mc.setX(0);

		if (centered) {
			e.setX(player.getX() + player.getWidth() / 2 - e.getWidth() / 2);
		} else {
			if (e.getX() + e.getWidth() / 2 < player.getX() + player.getWidth() / 2) {
				e.incX(50f * delta);
				if (e.getX() + e.getWidth() / 2 >= player.getX() + player.getWidth() / 2) {
					e.setX(player.getX() + player.getWidth() / 2 - e.getWidth() / 2);
					centered = true;
				}
			} else if (e.getX() + e.getWidth() / 2 > player.getX() + player.getWidth() / 2) {
				e.incX(-50f * delta);
				if (e.getX() + e.getWidth() / 2 <= player.getX() + player.getWidth() / 2) {
					e.setX(player.getX() + player.getWidth() / 2 - e.getWidth() / 2);
					centered = true;
				}
			}
		}

		Rectangle playerObtainBounds = ((CollisionComponent) player.getComponent(CollisionComponent.ID)).getBounds();
		playerObtainBounds.y += e.getHeight();
		if(!isObject && playerObtainBounds.overlaps(cc.getBounds())){
			e.setRemove(true);
			return;
		}

		if (!player.getBeam().isValid())
			stopLifting();
		else if (!player.getBeam().getBounds().overlaps(cc.getBounds())) {
			stopLifting();
		}

		if (e.getY() < Handler.getLevel().getGroundLevel())
			e.setY(Handler.getLevel().getGroundLevel());
	}
	
	public boolean shouldBeginLifting(CollisionComponent cc, TractorBeam beam){
		if(beam.getBounds().overlaps(cc.getBounds())){
			return true;
		}
		return false;
	}

	public void beginLifting(LiftCallback callback) {
		lift = true;
		this.callback = callback;
	}

	public void stopLifting() {
		lift = false;
		centered = false;
		if (callback != null)
			callback.onLiftStop();
	}

	public boolean isLifting() {
		return lift;
	}

}
