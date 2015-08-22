package development.codenmore.ld33.entities.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import development.codenmore.ld33.entities.Entity;

public class AnimationComponent extends Component {
	
	//ID
	public static final int ID = 2;
	//Animations
	private MovementComponent mc;
	private float timer = 0f;
	private Animation left, right, standing;
	
	public AnimationComponent(Animation left, Animation right, Animation standing){
		super(ID);
		setLeft(left);
		setRight(right);
		setStanding(standing);
	}
	
	public AnimationComponent(Animation standing){
		this(null, null, standing);
	}
	
	public AnimationComponent(){
		this(null, null, null);
	}

	@Override
	public void tick(Entity e, float delta) {
		timer += delta;
		mc = e.getComponent(MovementComponent.ID);
		if(mc == null || left == null || right == null){
			e.setTexture(standing.getKeyFrame(timer));
			return;
		}
		if(mc.getX() < 0){
			e.setTexture(left.getKeyFrame(timer));
		}else if(mc.getX() > 0){
			e.setTexture(right.getKeyFrame(timer));
		}else{
			e.setTexture(standing.getKeyFrame(timer));
		}
	}
	
	//GETTERS SETTERS

	public Animation getLeft() {
		return left;
	}

	public void setLeft(Animation left) {
		this.left = left;
		if(left != null)
			left.setPlayMode(PlayMode.LOOP);
	}

	public Animation getRight() {
		return right;
	}

	public void setRight(Animation right) {
		this.right = right;
		if(right != null)
			right.setPlayMode(PlayMode.LOOP);
	}

	public Animation getStanding() {
		return standing;
	}

	public void setStanding(Animation standing) {
		this.standing = standing;
		if(standing != null)
			standing.setPlayMode(PlayMode.LOOP);
	}

}
