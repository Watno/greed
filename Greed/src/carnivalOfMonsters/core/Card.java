package carnivalOfMonsters.core;

import com.google.gson.annotations.Expose;

public abstract class Card implements ICard{
	@Expose
	private String name;
	@Expose
	private String type;

	protected Card(String type, String name) {
		super();
		this.name = name;
		this.type = type;
	}
	
}
