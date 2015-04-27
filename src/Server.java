import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

import javax.imageio.stream.FileImageInputStream;

public class Server {
	private static Random ran = new Random();

	public static void main(String args[]) {
		ServerSocket ss = null;
		File f = new File("/etc/postfix/aliases");
		File log = new File("err.log");
		PrintWriter pw = null;
		PrintWriter pw_log = null;
		try {
			ss = new ServerSocket(10199);
			pw_log = new PrintWriter(new FileOutputStream(log, true));
			Socket s;
			while (true) {
				try {
					StringBuilder sb = new StringBuilder();
					boolean newAlias = false;
					boolean listAlias = false;
					s = ss.accept();
					InputStream is = s.getInputStream();
					OutputStream os = s.getOutputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					PrintWriter pw_socket = new PrintWriter(os, true);
					String str;
					pw = new PrintWriter(new FileOutputStream(f, true), true);
					while ((str = br.readLine()) != null) {
						System.out.println(str);
						if (Protocal.CLIENT_NEW_ALIAS.equals(str)) {
							newAlias = true;
							break;
						} else if (Protocal.CLIENT_LIST_ALIASES.equals(str)) {
							listAlias = true;
							break;
						}
					}
					if (newAlias) {
						char ranLetter = getRandomLetter();
						sb.append(ranLetter);
						pw.print(ranLetter);
						for (int i = 0; i < ran.nextInt(10) + 2; i++) {
							char ranLetterOrDigtal = ran.nextFloat() >= 0.3 ? getRandomLetter() : getRandomDigital();
							pw.print(ranLetterOrDigtal);
							sb.append(ranLetterOrDigtal);
						}
						Process process = Runtime.getRuntime().exec(new String[] { "postalias", "hash:/etc/postfix/aliases" });
						pw.println(": zylpostfix");
						pw_socket.println(sb.toString() + "@zyl-me.xicp.net");
						pw_socket.println(Protocal.SERVER_NEW_ALIAS_OK);
					} else if (listAlias) {
						FileInputStream fileinputStream = new FileInputStream(f);
						BufferedReader br_read_file = new BufferedReader(new InputStreamReader(fileinputStream));
						String alias_row = null;
						while ((alias_row = br_read_file.readLine()) != null) {
							String account = alias_row.split(":")[0];
							if (account != null && !account.equals("")) {
								pw_socket.println(account);
							}
						}
						pw_socket.println(Protocal.SERVER_LIST_ALIASES_OK);
						br_read_file.close();
					}
					pw.flush();
					pw_socket.flush();
					pw_socket.close();
					s.close();
				} catch (SocketException e) {
					// TODO: handle exception
					e.printStackTrace();
					if (pw_log != null)
						e.printStackTrace(pw_log);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (pw_log != null)
				e.printStackTrace(pw_log);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (pw_log != null)
				e.printStackTrace(pw_log);
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (pw_log != null) {
				pw_log.close();
			}
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if (pw_log != null)
						e.printStackTrace(pw_log);
				}
			}
		}
	}

	private static char getRandomLetter() {
		char a = 'a';
		a += ran.nextInt(26);
		return a;
	}

	private static char getRandomDigital() {
		char b = '0';
		b += ran.nextInt(10);
		return b;
	}
}
