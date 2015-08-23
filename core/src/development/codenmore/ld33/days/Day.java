package development.codenmore.ld33.days;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import development.codenmore.ld33.buildings.Barn;
import development.codenmore.ld33.buildings.Building;
import development.codenmore.ld33.entities.Cow;
import development.codenmore.ld33.entities.Entity;
import development.codenmore.ld33.entities.Human;
import development.codenmore.ld33.entities.HumanShooter;
import development.codenmore.ld33.entities.components.HumanShooterComponent;
import development.codenmore.ld33.level.Level;
import development.codenmore.ld33.states.AlienState;
import development.codenmore.ld33.states.GameState;
import development.codenmore.ld33.states.State;

public class Day {
	
	//Data from JSON
	private Level level;
	private int biome;
	private float nightTime;
	private int quota;
	private int maxCows;
	private int maxHumans;
	private float humanShooterChance;
	private float minCowTime;
	private float maxCowTime;
	private float minHumanTime;
	private float maxHumanTime;
	private Array<Building> buildings;
	private Array<Entity> entities;
	//Spawning
	private Array<Building> availBuildings;
	private int cowCount, humanCount;
	private float cowTimer = 0f, humanTimer = 0f;
	private float cowWaitTime;
	private float humanWaitTime;
	
	public Day(){
		availBuildings = new Array<Building>();
	}
	
	public void tick(float delta){
		if(cowCount < maxCows)
			cowTimer += delta;
		if(humanCount < maxHumans)
			humanTimer += delta;
		
		if(cowTimer > cowWaitTime){
			cowWaitTime = MathUtils.random(minCowTime, maxCowTime);
			cowCount++;
			cowTimer = 0f;
			//Spawn
			availBuildings.clear();
			for(Building b : buildings)
				if(b instanceof Barn)
					availBuildings.add(b);
			Building build = availBuildings.random();
			Cow c = new Cow(level.getEntityManager(), build.getCenterX() - 8, level.getGroundLevel());
			if(MathUtils.randomBoolean(0.02f))
				c.addComponent(new HumanShooterComponent());
		}else if(humanTimer > humanWaitTime){
			humanWaitTime = MathUtils.random(minHumanTime, maxHumanTime);
			humanCount++;
			humanTimer = 0f;
			//Spawn
			availBuildings.clear();
			for(Building b : buildings)
				if(!(b instanceof Barn))
					availBuildings.add(b);
			Building build = availBuildings.random();
			if(MathUtils.randomBoolean(humanShooterChance)){
				new HumanShooter(level.getEntityManager(), build.getCenterX() - 8, level.getGroundLevel());
			}else{
				new Human(level.getEntityManager(), build.getCenterX() - 8, level.getGroundLevel());
			}
		}
	}
	
	public void endNight(boolean quotaMet, boolean dead){
		State.popState();
		if(quotaMet && !dead){
			((AlienState) State.peekState()).reset(1);
		}else if(!quotaMet && dead){
			((AlienState) State.peekState()).reset(2);
		}else{//NO QUOTA
			((AlienState) State.peekState()).reset(0);
		}
	}
	
	public Level loadToLevel(GameState gameState){
		Level level = new Level(gameState, biome);
		this.level = level;
		//Requirements
		level.getEntityManager().getPlayer().getHud().setRequiredQuota(quota);
		//Spawners
		cowCount = 0;
		humanCount = 0;
		cowWaitTime = MathUtils.random(minCowTime, maxCowTime);
		humanWaitTime = MathUtils.random(minHumanTime, maxHumanTime);
		//Arrays
		for(Building b : buildings){
			b.setY(level.getGroundLevel());
		}
		level.setBuildings(buildings);
		for(Entity e : entities){
			e.setY(level.getGroundLevel());
		}
		level.getEntityManager().addAll(entities);
		
		return level;
	}
	
	//HELPERS
	
	public void incCowCount(int i){
		cowCount += i;
	}
	
	public void incHumanCount(int i){
		humanCount += i;
	}
	
	//LOADERS
	
	public static Day loadDay(FileHandle file){
		Json json = new Json();
		return json.fromJson(Day.class, file);
	}

	//GETTERS SETTERS
	
	public int getBiome() {
		return biome;
	}

	public void setBiome(int biome) {
		this.biome = biome;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getMaxCows() {
		return maxCows;
	}

	public void setMaxCows(int maxCows) {
		this.maxCows = maxCows;
	}

	public float getMinCowTime() {
		return minCowTime;
	}

	public void setMinCowTime(float minCowTime) {
		this.minCowTime = minCowTime;
	}

	public float getMaxCowTime() {
		return maxCowTime;
	}

	public void setMaxCowTime(float maxCowTime) {
		this.maxCowTime = maxCowTime;
	}

	public float getMinHumanTime() {
		return minHumanTime;
	}

	public void setMinHumanTime(float minHumanTime) {
		this.minHumanTime = minHumanTime;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public float getNightTime() {
		return nightTime;
	}

	public void setNightTime(float nightTime) {
		this.nightTime = nightTime;
	}

	public float getMaxHumanTime() {
		return maxHumanTime;
	}

	public void setMaxHumanTime(float maxHumanTime) {
		this.maxHumanTime = maxHumanTime;
	}

	public int getMaxHumans() {
		return maxHumans;
	}

	public void setMaxHumans(int maxHumans) {
		this.maxHumans = maxHumans;
	}

	public Array<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(Array<Building> buildings) {
		this.buildings = buildings;
	}

	public Array<Entity> getEntities() {
		return entities;
	}

	public void setEntities(Array<Entity> entities) {
		this.entities = entities;
	}

	public float getHumanShooterChance() {
		return humanShooterChance;
	}

	public void setHumanShooterChance(float humanShooterChance) {
		this.humanShooterChance = humanShooterChance;
	}

}
