import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Client {
	public String createNewAccountVerbose()
	{
		StringBuilder sb = new StringBuilder();
		try {
			Socket s = new Socket("zyl-me.xicp.net", 10199);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println(Protocal.CLIENT_NEW_ALIAS);
			byte[] buffer = new byte[1024];
			int len = 0;
			pw.flush();
			while((len = is.read(buffer)) != -1)
			{
				System.out.println(new String(buffer, 0, len));
				sb.append(new String(buffer, 0, len));
			}
			pw.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.append(e.getMessage());
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @return newEmailAccount
	 */
	public String createNewAccount()
	{
		StringBuilder sb = new StringBuilder();
		try {
			Socket s = new Socket("zyl-me.xicp.net", 10199);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println(Protocal.CLIENT_NEW_ALIAS);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = null;
			pw.flush();
			while((str = br.readLine()) != null)
			{
				if(str.matches("^([a-zA-Z0-9_\\.\\-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$"))
					sb.append(str);
			}
			pw.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return sb.toString();
	}
	
	/**
	 * @return ArrayList<String> list accounts 
	 */
	public ArrayList<String> getListAccounts()
	{
		StringBuilder sb = new StringBuilder();
		ArrayList<String> arr = new ArrayList<String>();
		try {
			Socket s = new Socket("zyl-me.xicp.net", 10199);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.println(Protocal.CLIENT_LIST_ALIASES);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = null;
			pw.flush();
			while(!Protocal.SERVER_LIST_ALIASES_OK.equals((str = br.readLine())))
			{
				System.out.println(str);
				if(str != null && !str.equals(""))
				{
					arr.add(str + "@zyl-me.xicp.net");
				}
			}
			pw.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return arr;
	}
}
