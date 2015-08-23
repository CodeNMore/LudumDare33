package development.codenmore.ld33.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Main;
import development.codenmore.ld33.assets.Assets;

public class AlienState extends State {

	private Texture background;
	private int dayNumber;
	private String what = "";
	private int remaining = 1;

	public AlienState() {
		background = new Texture(Gdx.files.internal("screens/alienScreen.png"));
		dayNumber = 0;
	}

	@Override
	public void tick(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			if(dayNumber == 8){
				State.popAll();
				State.pushState(new HomeState());
			}
			if (remaining > 0)
				remaining--;
			else if(remaining == 0){
				dayNumber = 1;
				remaining = -1;
				State.pushState(new GameState(dayNumber));
			}else{
				State.pushState(new GameState(dayNumber));
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
			Assets.setFontColor(Color.BLACK);
			Assets.setFontMed();
			if (dayNumber == 0) {
				if (remaining == 1) {
					Assets.drawString(batch, spacify("welcome. you are\n"
							+ "chosen to be one\n" + "of the pilots in\n"
							+ "our fleet to help\n" + "destroy all cows\n"
							+ "and humans on\n" + "earth!\n"
							+ "              [ENTER]"), 190, 466);
				} else {
					Assets.drawString(batch, spacify("you must abduct\n"
							+ "enough cows and\n"
							+ "humans to meet\n"
							+ "the nightly quota\n"
							+ "if you dont meet\n"
							+ "it in time, then\n"
							+ "you will be fired\n"
							+ "             [ENTER]"), 190, 466);
				}
			}else{
				Assets.drawString(batch, spacify(what), 190, 466);
			}
		}
		batch.end();
	}
	
	public void reset(int type){
		if(type == 1){//COMPLETED
			dayNumber++;
			if(dayNumber == 8){//end of game
				what = "Congratulations!\n"
						+ "we eliminated\n"
						+ "all humans and\n"
						+ "cows! Thanks for\n"
						+ "your help!";
			}else{
				what = "Alright, now day\n"
						+ "number " + dayNumber + " because\n"
						+ "you met your\n"
						+ "quota!\n\n\n\n"
						+ "             [ENTER]";
			}
		}else if(type == 2){//DEAD
			what = "How could you\n"
					+ "die that easy?\n"
					+ "You had one job!\n"
					+ "I'll go back in\n"
					+ "time and let you\n"
					+ "do it again!\n\n"
					+ "             [ENTER]";
		}else{//NO QUOTA
			what = "How could you\n"
					+ "not meet your\n"
					+ "quota?!?!?!?!\n"
					+ "I'll go back in\n"
					+ "time and let you\n"
					+ "do it again!\n\n"
					+ "             [ENTER]";
		}
	}

	public String spacify(String str) {
		return str.replaceAll(" ", "  ");
	}

}
