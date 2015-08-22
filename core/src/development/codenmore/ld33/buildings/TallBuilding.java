package development.codenmore.ld33.buildings;

import development.codenmore.ld33.assets.Assets;

public class TallBuilding extends Building {

	public TallBuilding(float x, float y) {
		super(x, y, 64, 128, Assets.getRegion("tallBuilding"));
	}

}
