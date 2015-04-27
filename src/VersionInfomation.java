
public class VersionInfomation {
	private int version;
	private String download;
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public VersionInfomation(int version, String download,String msg) {
		super();
		this.version = version;
		this.download = download;
		this.msg = msg;
	}
	public String getDownload() {
		return download;
	}
	public int getVersion() {
		return version;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
