import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class MailUserAgent {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE HH:mm:ss");
	
	public Letter receiveLastLetter(){
		String host = "zyl-me.xicp.net";
		Letter letter = null;
		Properties props = new Properties();
		ArrayList<Letter> arr = new ArrayList<Letter>();
		// 存储接收邮件服务器使用的协议，这里以POP3为例
		props.setProperty("mail.store.protocol", "imap");
		// 设置接收邮件服务器的地址，这里还是以网易163为例
		props.setProperty("mail.imap.host", host);
		// 根据属性新建一个邮件会话.
		Session session = Session.getInstance(props);
		// 从会话对象中获得POP3协议的Store对象
		try {
			Store store = session.getStore("imap");
			store.connect(host, "zyl", "123123");
			// 获取邮件服务器的收件箱
			Folder folder = store.getFolder("INBOX");
			// 以只读权限打开收件箱
			folder.open(Folder.READ_ONLY);
			// 获取收件箱中的邮件，也可以使用getMessage(int 邮件的编号)来获取具体某一封邮件
			Message message = folder.getMessage(folder.getMessageCount());
			Multipart multipart = (Multipart)message.getContent();  
			for (int j = 0; j<	multipart.getCount(); j++) {  
				Part part = multipart.getBodyPart(j);
				if(part.getContentType() != null && part.getContentType().startsWith("text/html"))
				{
					letter = new Letter(message.getSubject(), part.getContent()+"",sdf.format(message.getReceivedDate()));
					break;
				}else if(part.getContentType() != null && part.getContentType().startsWith("text/plain") && j == multipart.getCount() - 1)
				{
					arr.add(new Letter(message.getSubject(), part.getContent()+"",sdf.format(message.getReceivedDate())));
					break;
				}
			 }  
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		// 如果需要查看接收邮件的详细信息，需要设置Debug标志
		session.setDebug(false);
		return letter;
	}
	public ArrayList<Letter> receive() {
		String host = "zyl-me.xicp.net";
		Properties props = new Properties();
		ArrayList<Letter> arr = new ArrayList<Letter>();
		// 存储接收邮件服务器使用的协议，这里以POP3为例
		props.setProperty("mail.store.protocol", "imap");
		// 设置接收邮件服务器的地址，这里还是以网易163为例
		props.setProperty("mail.imap.host", host);
		// 根据属性新建一个邮件会话.
		Session session = Session.getInstance(props);
		// 从会话对象中获得POP3协议的Store对象
		try {
			Store store = session.getStore("imap");
			store.connect(host, "zyl", "123123");
			// 获取邮件服务器的收件箱
			Folder folder = store.getFolder("INBOX");
			// 以只读权限打开收件箱
			folder.open(Folder.READ_ONLY);
			// 获取收件箱中的邮件，也可以使用getMessage(int 邮件的编号)来获取具体某一封邮件
			Message messages[] = folder.getMessages();
			for (int i = messages.length - 1; i >= 0; i--) {
				Multipart multipart = (Multipart)messages[i].getContent();  
				for (int j = 0; j<	multipart.getCount(); j++) {  
					Part part = multipart.getBodyPart(j);
					if(part.getContentType() != null && part.getContentType().startsWith("text/html"))
					{
						arr.add(new Letter(messages[i].getSubject(), part.getContent()+"",sdf.format(messages[i].getReceivedDate())));
						break;
					}else if(part.getContentType() != null && part.getContentType().startsWith("text/plain") && j == multipart.getCount() - 1)
					{
						arr.add(new Letter(messages[i].getSubject(), part.getContent()+"",sdf.format(messages[i].getReceivedDate())));
						break;
					}
//					else if(part.getContentType() != null && part.getContentType().startsWith("message") && j == multipart.getCount() - 1)
//					{
//						arr.add(new Letter(messages[i].getSubject(), part.getContent()+""));
//						break;
//					}
				 }  
				}  
			// 关闭连接
			folder.close(false);
			store.close();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		// 如果需要查看接收邮件的详细信息，需要设置Debug标志
		session.setDebug(false);
		return arr;
	}
}
