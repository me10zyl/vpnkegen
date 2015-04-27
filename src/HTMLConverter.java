import java.util.regex.Pattern;


public class HTMLConverter {
	public static String convert(String msg)
	{
		msg = msg.replaceAll("\\n", "");
		msg = msg.replace("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">", "");//not required
		msg = msg.replaceAll("<\\s*/?\\s*[hH][tT][mM][lL]\\s*([\\w:\\-]*=\"[\\w/\\.:\\-]*\"\\s*)*\\s*>", "");
		msg = msg.replaceAll("<\\s*[hH][eE][aA][dD]\\s*([\\w:\\-]*=\"[\\w/\\.:\\-]*\"\\s*)*\\s*>.+<\\s*/?\\s*[hH][eE][aA][dD]\\s*([\\w:\\-]*=\"[\\w/\\.:\\-]*\"\\s*)*\\s*>", "");
		msg = msg.replaceAll("<\\s*[sS][tT][yY][lL][eE]\\s*([\\w:\\-]*=\"[\\w/\\.:\\-]*\"\\s*)*\\s*>.+<\\s*/?\\s*[sS][tT][yY][lL][eE]\\s*([\\w:\\-]*=\"[\\w/\\.:\\-]*\"\\s*)*\\s*>", "");//not required
		System.out.println(msg);
		return msg;
	}
}
