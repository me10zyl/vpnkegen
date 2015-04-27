import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;


public class AsodaoPoster {
	
	public static void register(JEditorPane editorPanel){
		HttpURLConnection connection;
		try {
			Client client = new Client();
			String newAccount = client.createNewAccount();
			connection = (HttpURLConnection) new URL("http://www.asodao.net/register").openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			OutputStream os = connection.getOutputStream();
			PrintWriter pw = new PrintWriter(os,true);
			pw.println("username="+newAccount+"&commit=µÇ Â½");
			pw.flush();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			StringBuilder sb = new StringBuilder();
			String str = null;
			while((str = br.readLine()) != null)
			{
				sb.append(str+"\n");
			}
			String msg = HTMLConverter.convert(sb.toString());
			editorPanel.setText(msg);
			br.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	};
	
}
