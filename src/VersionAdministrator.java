import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;


public class VersionAdministrator {
	public static int version = 1;
	public VersionInfomation detectVersion()
	{
		String download = "http://zyl-me.xicp.net:1234/vpnkeygen/vpnkeygen.jar";
		String message = "";
		int version = 0;
		try {
			URL url = new URL("http://zyl-me.xicp.net:1234/vpnkeygen/version");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5000);
			InputStream is = connection.getInputStream();
			Properties p = new Properties();
			p.load(is);
			String sVersion = (String) p.get("version");
			if(!"".equals(sVersion) && sVersion != null)
			{
				version = Integer.parseInt(sVersion);
			}
			download = (String) p.get("download");
			message = (String) p.get("message");
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new VersionInfomation(version,download,message);
	}
}
