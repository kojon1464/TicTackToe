package enums;

public enum PlayerType {
	PLAYER("Player", null), RANDOM_AI("Random AI",	"ai.RandomAI"), BLOCKING_AI("Blocking AI", "ai.BlockingAI");
	
	private String name;
	private String className;
	
	private PlayerType(String name, String className) {
		
		this.name = name;
		this.className = className;
	}
	
	public String getClassName() {
		
		return className;
	}
	
	@Override
	public String toString() {
		
		return name;
	}
}
