package development.codenmore.ld33.particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class ParticleEmitter {

	// Emitter
	private float x, y;
	private Color color;
	private Array<Particle> particles;
	// Calculations
	private int amount, generated;
	private float rate, timer = 0f;
	private int particleWidth, particleHeight;
	private int minSpeed, maxSpeed, minAngle, maxAngle, minLife, maxLife;

	public ParticleEmitter(int amount, float rate, Color color, float x, float y,
			int particleWidth, int particleHeight, int minSpeed, int maxSpeed,
			int minAngle, int maxAngle, int minLife, int maxLife) {
		this.amount = amount;
		this.rate = rate;
		this.color = color;
		this.x = x;
		this.y = y;
		this.particleWidth = particleWidth;
		this.particleHeight = particleHeight;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.minAngle = minAngle;
		this.maxAngle = maxAngle;
		this.minLife = minLife;
		this.maxLife = maxLife;
		particles = new Array<Particle>();
		emit();
	}

	public boolean tick(float delta) {
		timer += delta;
		if (amount == -1 || generated < amount){
			if(timer >= rate){
				timer = 0f;
				emit();
			}
		}

		for (Particle p : particles) {
			if (p.tick(delta)) {
				particles.removeValue(p, true);
			}
		}
		
		if(amount != -1 && particles.size <= 0)
			return true;
		return false;
	}

	public void render(SpriteBatch batch) {
		batch.setColor(color);
		for (Particle p : particles) {
			p.render(batch);
		}
		batch.setColor(Color.WHITE);
	}

	private void emit() {
		float angleR = MathUtils.random(minAngle, maxAngle) * (MathUtils.PI / 180);
		int speed = MathUtils.random(minSpeed, maxSpeed);
		float angleX = speed * MathUtils.cos(angleR);
		float angleY = -speed * MathUtils.sin(angleR);
		int particleMaxLife = MathUtils.random(minLife, maxLife);
		particles.add(new Particle(x, y, particleWidth, particleHeight, angleX, angleY, particleMaxLife));
		generated++;
	}

}
