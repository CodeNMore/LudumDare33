package development.codenmore.ld33;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import development.codenmore.ld33.assets.Assets;
import development.codenmore.ld33.input.InputManager;
import development.codenmore.ld33.states.LoadingState;
import development.codenmore.ld33.states.State;

public class Main extends ApplicationAdapter {
	
	//Globals
	public static final String TITLE = "Saucer";
	public static final int WIDTH = 640, HEIGHT = 480;
	//Rendering stuff
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Viewport viewport;
	private FPSLogger fpsLogger;
	
	public Main(){}
	
	@Override
	public void create(){
		//Rendering stuff
		batch = new SpriteBatch();
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.setToOrtho(false);
		viewport = new FitViewport(WIDTH, HEIGHT, cam);
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		fpsLogger = new FPSLogger();
		//Input
		Gdx.input.setInputProcessor(new InputManager());
		//Begin states
		State.pushState(new LoadingState());//Will create GameState after
	}
	
	@Override
	public void render(){
		//Reset
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fpsLogger.log();
		//Render
		if(State.peekState() != null){
			State.peekState().tick(Gdx.graphics.getDeltaTime());
			State.peekState().render(batch);
		}
	}
	
	@Override
	public void dispose(){
		//Rendering
		batch.dispose();
		State.popAll();
		Assets.dispose();
	}
	
	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
	@Override
	public void pause(){
		if(State.peekState() != null)
			State.peekState().pause();
	}
	
	@Override
	public void resume(){
		if(State.peekState() != null)
			State.peekState().resume();
	}
	
}
