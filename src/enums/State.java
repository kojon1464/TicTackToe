package enums;

public enum State {
	EMPTY("image/empty.png"), CROSS("image/cross.png"), CIRCLE("image/circle.png");
	
	private String path;
	
	private State(String path) {
		
		this.path = path;
	}
	
	public String getPath() {
		
		return path;
	}
}
