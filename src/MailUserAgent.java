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
		// �洢�����ʼ�������ʹ�õ�Э�飬������POP3Ϊ��
		props.setProperty("mail.store.protocol", "imap");
		// ���ý����ʼ��������ĵ�ַ�����ﻹ��������163Ϊ��
		props.setProperty("mail.imap.host", host);
		// ���������½�һ���ʼ��Ự.
		Session session = Session.getInstance(props);
		// �ӻỰ�����л��POP3Э���Store����
		try {
			Store store = session.getStore("imap");
			store.connect(host, "zyl", "123123");
			// ��ȡ�ʼ����������ռ���
			Folder folder = store.getFolder("INBOX");
			// ��ֻ��Ȩ�޴��ռ���
			folder.open(Folder.READ_ONLY);
			// ��ȡ�ռ����е��ʼ���Ҳ����ʹ��getMessage(int �ʼ��ı��)����ȡ����ĳһ���ʼ�
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
		// �����Ҫ�鿴�����ʼ�����ϸ��Ϣ����Ҫ����Debug��־
		session.setDebug(false);
		return letter;
	}
	public ArrayList<Letter> receive() {
		String host = "zyl-me.xicp.net";
		Properties props = new Properties();
		ArrayList<Letter> arr = new ArrayList<Letter>();
		// �洢�����ʼ�������ʹ�õ�Э�飬������POP3Ϊ��
		props.setProperty("mail.store.protocol", "imap");
		// ���ý����ʼ��������ĵ�ַ�����ﻹ��������163Ϊ��
		props.setProperty("mail.imap.host", host);
		// ���������½�һ���ʼ��Ự.
		Session session = Session.getInstance(props);
		// �ӻỰ�����л��POP3Э���Store����
		try {
			Store store = session.getStore("imap");
			store.connect(host, "zyl", "123123");
			// ��ȡ�ʼ����������ռ���
			Folder folder = store.getFolder("INBOX");
			// ��ֻ��Ȩ�޴��ռ���
			folder.open(Folder.READ_ONLY);
			// ��ȡ�ռ����е��ʼ���Ҳ����ʹ��getMessage(int �ʼ��ı��)����ȡ����ĳһ���ʼ�
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
			// �ر�����
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
		// �����Ҫ�鿴�����ʼ�����ϸ��Ϣ����Ҫ����Debug��־
		session.setDebug(false);
		return arr;
	}
}
