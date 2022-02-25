import java.io.Serializable;

public class Player implements Serializable
{
	
	/**
	 * Default serialVersionUID added automatically by Eclipse.
	 * Thanks!
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int games, wins, settlements, roads, sheep, brick, ore, wood, wheat, cities, devs, regPorts, sheepPorts, brickPorts, orePorts, woodPorts, wheatPorts = 0;
	
	
	public int getCities() {
		return cities;
	}
	public void increaseCities(int cities) {
		this.cities = this.cities + cities;
	}
	public Player ( String x ){
		name = x;
	}
	public String getName() {
		return name;
	}
	public int getSheep() {
		return sheep;
	}
	public void increaseSheep(int sheep) {
		this.sheep = this.sheep + sheep;
	}
	public int getBrick() {
		return brick;
	}
	public void increaseBrick(int brick) {
		this.brick = this.brick + brick;
	}
	public int getOre() {
		return ore;
	}
	public void increaseOre(int ore) {
		this.ore = this.ore + ore;
	}
	public int getWood() {
		return wood;
	}
	public void increaseWood(int wood) {
		this.wood = this.wood + wood;
	}
	public int getWheat() {
		return wheat;
	}
	public void increaseWheat(int wheat) {
		this.wheat = this.wheat + wheat;
	}
	public int getGames() {
		return games;
	}
	public void increaseGames(int games) {
		this.games = this.games + games;
	}
	public int getWins() {
		return wins;
	}
	public void increaseWins(int wins) {
		this.wins = this.wins + wins;
	}
	public int getSettlements() {
		return settlements;
	}
	public void increaseSettlements(int settlements) {
		this.settlements = this.settlements + settlements;
	}
	public int getRoads() {
		return roads;
	}
	public void increaseRoads(int roads) {
		this.roads = this.roads + roads;
	}
	public int getDevs() {
		return devs;
	}
	public void increaseDevs(int devs) {
		this.devs = this.devs + devs;
	}
	public int getRegPorts() {
		return regPorts;
	}
	public void increaseRegPorts(int regPorts) {
		this.regPorts = this.regPorts + regPorts;
	}
	public int getSheepPorts() {
		return sheepPorts;
	}
	public void increaseSheepPorts(int sheepPorts) {
		this.sheepPorts = this.sheepPorts + sheepPorts;
	}
	public int getBrickPorts() {
		return brickPorts;
	}
	public void increaseBrickPorts(int brickPorts) {
		this.brickPorts = this.brickPorts + brickPorts;
	}
	public int getOrePorts() {
		return orePorts;
	}
	public void increaseOrePorts(int orePorts) {
		this.orePorts = this. orePorts + orePorts;
	}
	public int getWheatPorts() {
		return wheatPorts;
	}
	public void increaseWheatPorts(int wheatPorts) {
		this.wheatPorts = this. wheatPorts + wheatPorts;
	}
	public int getWoodPorts() {
		return woodPorts;
	}
	public void increaseWoodPorts(int woodPorts) {
		this.woodPorts = this.woodPorts + woodPorts;
	}
	
}
