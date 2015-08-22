package development.codenmore.ld33.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Main;
import development.codenmore.ld33.assets.Assets;

public class LoadingState extends State {
	
	//Variables
	private static final float MINTIME = 0.0f;
	private boolean done = false;
	private float timer = 0f;
	//Rendering
	private Texture loadingScreen = null;
	private BitmapFont font = null;
	
	public LoadingState(){
		//Init assets for loading
		Assets.init();
	}

	@Override
	public void tick(float delta) {
		if(Assets.step()){
			done = true;
			Assets.postInit();
		}
		
		timer += delta;
		if(done && timer >= MINTIME){
			State.popState();
			State.pushState(new GameState());
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		{
			if(loadingScreen != null && font != null){
				batch.draw(loadingScreen, 0, 0, Main.WIDTH, Main.HEIGHT);
				font.draw(batch, Assets.getProgress() + "%", 110, 360);
			}
		}
		batch.end();
	}
	
	@Override
	public void onPush(){
		loadingScreen = new Texture(Gdx.files.internal("loading/loadingScreen.png"));
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
		font.getData().setScale(21.0f);
		font.setColor(Color.WHITE);
	}
	
	@Override
	public void onPop(){
		loadingScreen.dispose();
		font.dispose();
		loadingScreen = null;
		font = null;
	}

}
