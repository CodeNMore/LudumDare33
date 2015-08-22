package development.codenmore.ld33.states;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
	
	//MANAGER
	
	private static Stack<State> states = new Stack<State>();
	
	public static void pushState(State s){
		states.push(s);
		s.onPush();
	}
	
	public static State popState(){
		State s = states.pop();
		s.onPop();
		return s;
	}
	
	public static State peekState(){
		return states.peek();
	}
	
	public static void popAll(){
		while(!states.isEmpty())
			popState();
	}
	
	//CLASS
	
	public State(){}
	
	public abstract void tick(float delta);
	
	public abstract void render(SpriteBatch batch);
	
	public void onPush(){}
	
	public void onPop(){}
	
	public void pause(){}
	
	public void resume(){}

}
