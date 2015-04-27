import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.SliderUI;


public class ClickRestricter {
//	private Properties p = new Properties();
//	private FileInputStream fis;
//	private FileOutputStream fos;
//	private PrintWriter pw;
//	private File file;
//	public ClickRestricter() {
//		// TODO Auto-generated constructor stub
//		try {
//			file = new File("vpnkeygen-restricter.properties");
//			if(!file.exists())
//			{
//				file.createNewFile();
//			}
//			fis = new FileInputStream(file);
//			fos = new FileOutputStream(file);
//			pw = new PrintWriter(fos, true);
//			p.load(fis);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public boolean canClickNow()
//	{
//		
//		return false;
//	}
	public void setTime(final int time,final JButton btn,final String btn_str)
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int t = time;
				try {
//					fos = new FileOutputStream(file);
					btn.setEnabled(false);
					while(t >= 0)
					{
						btn.setText(btn_str+"("+t--+"s)");
//						p.setProperty("time", encypt(t--+""));
						Thread.sleep(1000);
					}
					btn.setText(btn_str);
					btn.setEnabled(true);
//					p.store(fos, "nocoments");
//					fos.close();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
				} 
			}
		}).start();
	}
//	public String getTime()
//	{
//		String time_encypted = p.getProperty("time","4");
//		String time = encypt(time_encypted);
//		return time;
//	}
	private String encypt(String s)
	{
		char[] charArray = s.toCharArray();
		for(int i =0;i < charArray.length;i++)
		{
			charArray[i] = (char) (charArray[i] ^ 4); 
		}
		return new String(charArray);
	}
	
}
