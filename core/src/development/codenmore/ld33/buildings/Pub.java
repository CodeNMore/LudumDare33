package development.codenmore.ld33.buildings;

import development.codenmore.ld33.assets.Assets;

public class Pub extends Building {

	public Pub(float x, float y) {
		super(x, y, 80, 80, Assets.getRegion("pubHouse"));
	}
	
	
	public Pub(){
		this(0, 0);
	}

}
