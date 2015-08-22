package development.codenmore.ld33.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.TractorBeam;

public class HUD {
	
	private float health = 3f;
	private FillBar energyBar;
	
	public HUD(){
		energyBar = new FillBar(90, 5, 200, 20, TractorBeam.BEAMENERGYTIME, TractorBeam.BEAMENERGYTIME, Color.DARK_GRAY, Color.YELLOW);
	}
	
	public void tick(float delta){
		energyBar.setFill(energyBar.getMaxFill() 
				-Handler.getLevel().getEntityManager().getPlayer().getBeam().getTimer());
		if(energyBar.getFill() < 0)
			energyBar.setFill(0);
	}
	
	public void render(SpriteBatch batch){
		energyBar.render(batch);
		Assets.setFontSmall();
		Assets.setFontColor(Color.WHITE);
		Assets.drawString(batch, "Energy:", 5, 25);
	}
	
	public Color getHealthColor(){
		if(health < 2){
			return Color.RED;
		}else if(health < 3){
			return Color.ORANGE;
		}else{
			return Color.GREEN;
		}
	}
	
	public void damage(float i){
		health -= i;
		//TODO: CHECK IF HEALTH BELOW 0
		System.out.println("DAMAGE");
	}

}
