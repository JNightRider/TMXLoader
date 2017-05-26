package com.jme3.tmx.core;

/**
 * This element defines an array of terrain types, which can be referenced from
 * the terrain attribute of the tile element.
 * 
 * @author yanmaoyuan
 * 
 */
public class Terrain extends Base {

	/**
	 * the tileset this terrain type belongs to.
	 */
	private Tileset tileset;
	
	/**
	 * ID of this terrain type.
	 */
	private int id;

	/**
	 * The name of the terrain type.
	 */
	private String name;
	
	/**
	 * The local tile-id of the tile that represents the terrain visually.
	 * 
	 * if tile == -1 then no image display
	 */
	private int tile;

	/**
	 * Default constructor
	 * 
	 * @param name The name of this terrain type.
	 */
	public Terrain(String name) {
		this.name = name;
		this.tile = -1;
	}
	
	public Tileset getTileset() {
		return tileset;
	}

	public void setTileset(Tileset tileset) {
		this.tileset = tileset;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTile() {
		return tile;
	}

	public void setTile(int tile) {
		this.tile = tile;
	}

}
