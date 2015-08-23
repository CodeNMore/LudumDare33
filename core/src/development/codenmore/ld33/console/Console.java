package development.codenmore.ld33.console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.Main;
import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.entities.Cow;
import development.codenmore.ld33.entities.Human;
import development.codenmore.ld33.entities.HumanShooter;
import development.codenmore.ld33.entities.Rock;
import development.codenmore.ld33.level.Level;
import development.codenmore.ld33.particles.ParticleEmitter;

public class Console {
	
	//Text box
	private static String text = "";
	private static Color consoleColor = new Color(0.3f, 0.3f, 0.3f, 0.95f);
	private static float x = 10, y = Main.HEIGHT - 30, width = 350, height = 20;
	//Rendering
	public static boolean show = false;
	
	private Console(){}
	
	public static void tick(float delta){
		if(show){
			if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE)){
				if(text.length() > 1)
					text = text.substring(0, text.length() - 2);
			}else if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				submitCommand(text);
				toggle();
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.GRAVE))
			toggle();
	}
	
	public static void render(SpriteBatch batch){
		if(!show)
			return;
		
		batch.begin();
		{
			batch.setColor(consoleColor);
			batch.draw(Assets.getRegion("color"), x, y, width, height);
			batch.setColor(Color.WHITE);
			
			Assets.setFontColor(Color.WHITE);
			Assets.setFontMicro();
			Assets.drawString(batch, text, x + 5, y + 20);
		}
		batch.end();
	}
	
	//COMMANDS
	
	public static void submitCommand(String cmd){
		String[] tokens = cmd.split(" ");
		Level level = Handler.getLevel();
		if(level == null || tokens.length < 1)
			return;
		
		if(tokens.length >= 3 && tokens[0].equals("DREWDROP")){
			int times = Integer.parseInt(tokens[2]);
			
			if(tokens[1].equals("COW")){
				for(int i = 0;i < times;++i)
					new Cow(level.getEntityManager(), MathUtils.random(48, Main.WIDTH - 48), level.getGroundLevel() + 50);
			}else if(tokens[1].equals("HUMAN")){
				for(int i = 0;i < times;++i)
					new Human(level.getEntityManager(), MathUtils.random(48, Main.WIDTH - 48), level.getGroundLevel() + 50);
			}else if(tokens[1].equals("SOLDIER")){
				for(int i = 0;i < times;++i)
					new HumanShooter(level.getEntityManager(), MathUtils.random(48, Main.WIDTH - 48), level.getGroundLevel() + 50);
			}else if(tokens[1].equals("ROCK")){
				for(int i = 0;i < times;++i)
					new Rock(level.getEntityManager(), MathUtils.random(48, Main.WIDTH - 48), level.getGroundLevel());
			}
		}else if(tokens[0].equals("HEAL")){
			level.getEntityManager().getPlayer().fillHealth();
		}else if(tokens[0].equals("SUICIDE")){
			level.getEntityManager().getPlayer().damage(10);
		}else if(tokens[0].equals("CLEAR")){
			level.getEntityManager().getEntities().clear();
			level.getEntityManager().add(level.getEntityManager().getPlayer());
		}else if(tokens[0].equals("ENDLESS")){
			if(level.getEntityManager().getPlayer().getHud().isEndless()){
				level.getEntityManager().getPlayer().getHud().setRequiredQuota(25);
				level.getBackground().setNightTime(30);
				level.getEntityManager().getPlayer().getHud().setEndless(false);
			}else{
				level.getEntityManager().getPlayer().getHud().setRequiredQuota(Integer.MAX_VALUE);
				level.getBackground().setNightTime(Float.MAX_VALUE);
				level.getEntityManager().getPlayer().getHud().setEndless(true);
			}
		}else if(tokens.length > 1 && tokens[0].equals("PARTY")){
			int times = Integer.parseInt(tokens[1]);
			for(int i = 0;i < times;++i){
				level.getEntityManager().addEmitter(new ParticleEmitter(
						1000, 0f,
						randomColor(), MathUtils.random(0, Main.WIDTH),
						MathUtils.random(200, Main.HEIGHT - 200),
						3, 3, 30, 50, 0, 360, 1, 3));
			}
		}
	}
	
	public static Color randomColor(){
		return Color.GREEN;
	}
	
	public static void toggle(){
		if(!show){
			show = true;
			text = "";
		}else{
			show = false;
		}
	}
	
	public static void typed(char c){
		if(!show)
			return;
		c = Character.toUpperCase(c);
		if(c != ' ' && !Character.isDigit(c) && !Character.isAlphabetic(c))
			return;
		text += c;
	}

}
