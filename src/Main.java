import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTabbedPane;


public class Main {
	private JFrame frmVpnkeygen;
	private Client client;
	private MailUserAgent mua;
	private ListModel<String> listModel_subject;
	private ListModel<String> listModel_accounts;
	private ArrayList<Letter> arr;
	private VersionAdministrator versionAdmin;
	private ClickRestricter clickRestricter;
	private JEditorPane editorPane_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmVpnkeygen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		client = new Client();
		clickRestricter = new ClickRestricter();
		mua = new MailUserAgent();
		listModel_subject = new DefaultListModel<String>();
		listModel_accounts = new DefaultListModel<String>();
		versionAdmin = new VersionAdministrator();
		VersionInfomation versionInfomation = versionAdmin.detectVersion();
		int version = versionInfomation.getVersion();
		String message = versionInfomation.getMsg();
		if(version > VersionAdministrator.version)
		{
			int result = JOptionPane.showConfirmDialog(frmVpnkeygen, "有新版本 version"+version+" 可下载,是否下载?\n"+message, "新版本detected!", JOptionPane.YES_NO_OPTION);
			if(result == 0)//是
			{
				String downloadSite = versionInfomation.getDownload();
				try {
					Desktop.getDesktop().browse(new URI(downloadSite));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frmVpnkeygen, e.getMessage());
					e.printStackTrace();
				}
			}
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVpnkeygen = new JFrame();
		frmVpnkeygen.setTitle("VPNKeygen - version"+VersionAdministrator.version);
		frmVpnkeygen.setBounds(100, 100, 1001, 506);
		frmVpnkeygen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String lnfName = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		try
		{
			javax.swing.UIManager.setLookAndFeel(lnfName);
			SwingUtilities.updateComponentTreeUI(frmVpnkeygen);
			frmVpnkeygen.getContentPane().setLayout(null);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 969, 461);
			frmVpnkeygen.getContentPane().add(tabbedPane);
			
			JPanel panel_2 = new JPanel();
			JPanel panel_3 = new JPanel();
			tabbedPane.addTab("插件", null, panel_3, null);
			panel_3.setLayout(null);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u63D2\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_4.setBounds(16, 13, 196, 404);
			panel_3.add(panel_4);
			panel_4.setLayout(null);
			
			final JButton btnNewButton = new JButton("\u4E00\u952E\u6CE8\u518CASODAO");
			btnNewButton.setBounds(6, 20, 176, 27);
			panel_4.add(btnNewButton);
			
			JScrollPane scrollPane_4 = new JScrollPane();
			scrollPane_4.setBounds(226, 20, 700, 305);
			panel_3.add(scrollPane_4);
			
			editorPane_2 = new JEditorPane();
			scrollPane_4.setViewportView(editorPane_2);
			editorPane_2.setEditable(false);
			editorPane_2.setContentType("text/html");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AsodaoPoster.register(editorPane_2);
					clickRestricter.setTime(30, btnNewButton, btnNewButton.getText());
				}
			});
			tabbedPane.addTab("高级", null, panel_2, null);
			panel_2.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(0, 0, 245, 294);
			panel_2.add(panel_1);
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u65B0\u8D26\u53F7\u6CE8\u518C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setLayout(null);
			
			final String createNewAccountStr = "\u5EFA\u7ACB\u65B0\u8D26\u53F7";
			final JButton button = new JButton(createNewAccountStr);
			button.setBounds(14, 30, 137, 27);
			panel_1.add(button);
			
			JPanel panel = new JPanel();
			panel.setBounds(6, 70, 233, 217);
			panel_1.add(panel);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 20, 221, 190);
			panel.add(scrollPane);
			
			final JTextPane textPane = new JTextPane();
			scrollPane.setViewportView(textPane);
			textPane.setEditable(false);
			
			JButton button_1 = new JButton("\u6536\u4EF6");
			button_1.setBounds(259, 6, 128, 27);
			panel_2.add(button_1);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(398, 6, 421, 403);
			panel_2.add(scrollPane_1);
			
			final JEditorPane editorPane_1 = new JEditorPane();
			scrollPane_1.setViewportView(editorPane_1);
			editorPane_1.setContentType("text/html");
			editorPane_1.setEditable(false);
			editorPane_1.setFont(new Font(null, 0, 30));
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(256, 46, 131, 363);
			panel_2.add(scrollPane_2);
			
			JList<String> list = new JList<String>();
			scrollPane_2.setViewportView(list);
			list.setModel(listModel_subject);
			
			JLabel lblTips = new JLabel("Tips(\u7533\u8BF7VPN\u6B65\u9AA4):");
			lblTips.setBounds(0, 307, 150, 18);
			panel_2.add(lblTips);
			
			JLabel lblStep = new JLabel("Step1. \u5EFA\u7ACB\u65B0\u8D26\u53F7");
			lblStep.setBounds(37, 331, 150, 18);
			panel_2.add(lblStep);
			
			JLabel lblStepvpn = new JLabel("Step2. \u590D\u5236\u8D26\u53F7\u5230VPN\u7F51\u7AD9");
			lblStepvpn.setBounds(37, 350, 207, 18);
			panel_2.add(lblStepvpn);
			
			JLabel lblStep_1 = new JLabel("Step3. \u70B9\u51FB\u6536\u4EF6\uFF0C\u67E5\u770B\u90AE\u4EF6");
			lblStep_1.setBounds(37, 370, 191, 18);
			panel_2.add(lblStep_1);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5DF2\u6709\u8D26\u53F7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_5.setBounds(823, 6, 133, 410);
			panel_2.add(panel_5);
			panel_5.setLayout(null);
			
			JScrollPane scrollPane_3 = new JScrollPane();
			scrollPane_3.setBounds(10, 56, 117, 301);
			panel_5.add(scrollPane_3);
			
			JList<String> list_accounts = new JList<String>();
			scrollPane_3.setViewportView(list_accounts);
			
			JLabel label_1 = new JLabel("\u5DF2\u6709\u7684\u8D26\u53F7\u5217\u8868\uFF1A");
			label_1.setBounds(6, 20, 121, 18);
			panel_5.add(label_1);
			
			JButton btnNewButton_1 = new JButton("\u5237\u65B0");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DefaultListModel<String> dModel = (DefaultListModel<String>)listModel_accounts;
					ArrayList<String> listAccounts = client.getListAccounts();
					dModel.clear();
					for(String s: listAccounts)
					{
						dModel.addElement(s);
					}
				}
			});
			btnNewButton_1.setBounds(10, 370, 113, 27);
			panel_5.add(btnNewButton_1);
			list.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					if(e.getValueIsAdjusting())
					{
						String msg =  arr.get(((JList)e.getSource()).getSelectedIndex()).getHeader() + "<hr>"+"<font color='#F5A7A7' size=3 align=right>"+arr.get(((JList)e.getSource()).getSelectedIndex()).getTime()+"</font>"+arr.get(((JList)e.getSource()).getSelectedIndex()).getBody();
//						Pattern p = Pattern.compile("</?html>");
//						msg = msg.replaceAll("</?[hH][tT][mM][lL].*>", "");
//						msg = msg.replaceAll("<[sS][tT][yY][lL][eE]>.+</[sS][tT][yY][lL][eE]>", "");
//						System.out.println(msg);
						msg = HTMLConverter.convert(msg);
//						System.out.println("------------------\n"+msg);
						editorPane_1.setText(msg);
					}
				}
			});
			button_1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					arr = mua.receive();
					DefaultListModel<String> deListModel = (DefaultListModel<String>)listModel_subject;
					deListModel.clear();
					for(Letter letter : arr)
					{
						deListModel.addElement(letter.getHeader());
					}
				}
			});
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						String msg = client.createNewAccountVerbose();
						if(msg.contains(Protocal.SERVER_NEW_ALIAS_OK))
							textPane.setText(textPane.getText()+"建立账号成功，新账号："+"\n");
						textPane.setText(textPane.getText()+msg+"\n");
						clickRestricter.setTime(10, button, createNewAccountStr);
				}
			});
			frmVpnkeygen.setLocationRelativeTo(null);
			
			JLabel lblCaution = new JLabel("Caution:");
			lblCaution.setBounds(226, 338, 72, 18);
			panel_3.add(lblCaution);
			
			JLabel label = new JLabel("\u6CE8\u518C\u9891\u7387\u8FC7\u9AD8\u53EF\u80FD\u5BFC\u81F4\u57DF\u540D\u88AB\u5C01\u7981");
			label.setBounds(255, 369, 244, 18);
			panel_3.add(label);
			
			JLabel label_2 = new JLabel("\u51FA\u73B0\u6CE8\u518C\u6210\u529F\u65F6\u70B9\u51FB\u6536\u53D6\u8D26\u6237");
			label_2.setBounds(255, 399, 244, 18);
			panel_3.add(label_2);
			
			JButton button_2 = new JButton("\u6536\u53D6\u8D26\u6237");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Letter letter = mua.receiveLastLetter();
					String msg = letter.getHeader() + "<hr>"+letter.getBody();
//					Pattern p = Pattern.compile("</?html>");
//					msg = msg.replaceAll("</?[hH][tT][mM][lL].*>", "");
//					msg = msg.replaceAll("<[sS][tT][yY][lL][eE]>.+</[sS][tT][yY][lL][eE]>", "");
					msg = HTMLConverter.convert(msg);
					editorPane_2.setText(msg);
				}
			});
			button_2.setBounds(811, 365, 113, 27);
			panel_3.add(button_2);
			list_accounts.setModel(listModel_accounts);
			frmVpnkeygen.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					super.windowClosing(e);
				}
			});
			editorPane_2.setFont(new Font(null, 0, 30));
		} catch (UnsupportedLookAndFeelException ex1)
		{
			System.err.println("Unsupported LookAndFeel: " + lnfName);
		} catch (ClassNotFoundException ex2)
		{
			System.err.println("LookAndFeel class not found: " + lnfName);
		} catch (InstantiationException ex3)
		{
			System.err.println("Could not load LookAndFeel: " + lnfName);
		} catch (IllegalAccessException ex4)
		{
			System.err.println("Cannot use LookAndFeel: " + lnfName);
		}
	}
}
