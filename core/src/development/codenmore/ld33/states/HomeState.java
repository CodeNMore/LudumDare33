package development.codenmore.ld33.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import development.codenmore.ld33.Main;

public class HomeState extends State {
	
	//Texture
	private Texture texture;
	
	public HomeState(){
		texture = new Texture(Gdx.files.internal("screens/home.png"));
	}
	
	public void tick(float delta){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
			State.popState();
			State.pushState(new InfoState());
		}
	}
	
	public void render(SpriteBatch batch){
		batch.begin();
		{
			batch.draw(texture, 0, 0, Main.WIDTH, Main.HEIGHT);
		}
		batch.end();
	}
	
	@Override
	public void onPop(){
		texture.dispose();
	}

}
