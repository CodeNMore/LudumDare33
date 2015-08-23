package development.codenmore.ld33.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Handler;
import development.codenmore.ld33.days.Day;
import development.codenmore.ld33.level.Level;

public class GameState extends State {
	
	//Level
	private Day day;
	private Level level;
	
	public GameState(int dayNumber){
		day = Day.loadDay(Gdx.files.internal("levels/" + dayNumber + ".json"));
		level = day.loadToLevel(this);
		Handler.setLevel(level);
	}

	@Override
	public void tick(float delta) {
		day.tick(delta);
		level.tick(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			level.render(batch);
		}
		batch.end();
	}
	
	//GETTERS SETTERS

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
