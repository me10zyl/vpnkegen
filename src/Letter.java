public class Letter {
	private String header;
	private String body;
	private String time;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Letter(String header, String body, String time) {
		super();
		this.header = header;
		this.body = body;
		this.time = time;
	}
}
