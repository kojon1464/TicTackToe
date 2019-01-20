package enums;

public enum PlayerType {
	PLAYER("Player", null), RANDOM_AI("Random AI",	"ai.RandomAI"), BEST_ODD_AI("Best Odd AI", "ai.BestOddAI");
	
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
