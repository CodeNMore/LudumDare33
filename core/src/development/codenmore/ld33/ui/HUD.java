package development.codenmore.ld33.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.TractorBeam;

public class HUD {
	
	public static final float STARTHEALTH = 3f;
	private float health = 3f;
	private int quotaFill = 0, requiredQuota = 7;
	private boolean endless = false;
	private FillBar energyBar;
	private FillBar healthBar;
	private FillBar quotaBar;
	
	public HUD(){
		energyBar = new FillBar(5, 5, 200, 20, TractorBeam.BEAMENERGYTIME, TractorBeam.BEAMENERGYTIME, Color.GRAY, Color.YELLOW);
		healthBar = new FillBar(208, 5, 200, 20, health, STARTHEALTH, Color.GRAY, Color.RED);
		quotaBar = new FillBar(414, 5, 200, 20, quotaFill, requiredQuota, Color.GRAY, Color.GREEN);
	}
	
	public void tick(float delta){
		if(endless){
			Handler.getLevel().getEntityManager().getPlayer().getBeam().setTimer(0);
		}
		energyBar.setFill(energyBar.getMaxFill() 
				-Handler.getLevel().getEntityManager().getPlayer().getBeam().getTimer());
		if(energyBar.getFill() < 0)
			energyBar.setFill(0);
		
		healthBar.setFill(health);
		if(healthBar.getFill() < 0)
			healthBar.setFill(0);
		
		quotaBar.setFill(quotaFill);
		if(quotaBar.getFill() > requiredQuota)
			quotaBar.setFill(requiredQuota);
	}
	
	public void render(SpriteBatch batch){
		Assets.setFontSmall();
		Assets.setFontColor(Color.BLACK);
		
		energyBar.render(batch);
		Assets.drawString(batch, "Energy", 8, 26);
		
		healthBar.render(batch);
		healthBar.setX(220);
		Assets.drawString(batch, "Health", 224, 26);
		
		quotaBar.render(batch);
		quotaBar.setX(434);
		Assets.drawString(batch, "Nightly  Quota", 438, 26);
	}
	
	public Color getHealthColor(){
		if(health < 1){
			return Color.RED;
		}else if(health < 2){
			return Color.ORANGE;
		}else{
			return Color.GREEN;
		}
	}
	
	public void damage(float i){
		Assets.getSound("hurt").play();
		if(endless)
			return;
		health -= i;
		if(health <= 0){
			health = 0;
			Handler.getLevel().getGameState().getDay().endNight(false, true);
		}
	}
	
	public void incQuota(int i){
		Assets.getSound("collect").play();
		quotaFill += i;
		if(quotaFill < 0)
			quotaFill = 0;
		else if(quotaFill >= requiredQuota){
			Handler.getLevel().getGameState().getDay().endNight(true, false);
		}
	}
	
	public void setEndless(){
		endless = true;
	}
	
	//GETTERS SETTERS
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getQuotaFill() {
		return quotaFill;
	}

	public void setQuotaFill(int quotaFill) {
		this.quotaFill = quotaFill;
	}

	public int getRequiredQuota() {
		return requiredQuota;
	}

	public void setRequiredQuota(int requiredQuota) {
		this.requiredQuota = requiredQuota;
		quotaBar.setMaxFill(requiredQuota);
		setQuotaFill(0);
	}

	public boolean isEndless() {
		return endless;
	}

	public void setEndless(boolean endless) {
		this.endless = endless;
	}

}
