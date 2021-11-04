package sbt.automization.core.util;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MailHandler
{
	private String mailTo;
	private String header;
	private String body;
	
	public MailHandler()
	{
	}
	
	public void openMail() throws URISyntaxException, IOException
	{
		Desktop desktop;
		if (Desktop.isDesktopSupported()
				&& (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL))
		{
			URI mailto = new URI("mailto:" + mailTo + "?subject=" + uriEncode(header) + "&body=" + uriEncode(body));
			desktop.mail(mailto);
		} else
		{
			// TODO fallback to some Runtime.exec(..) voodoo?
			throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
		}
	}
	
	String getMailTo()
	{
		return mailTo;
	}
	
	public void setMailTo(String mailTo)
	{
		this.mailTo = mailTo;
	}
	
	String getHeader()
	{
		return header;
	}
	
	public void setHeader(String header)
	{
		this.header = header;
	}
	
	String getBody()
	{
		return body;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	public static String uriEncode(String in)
	{
		String out = new String();
		for (char ch : in.toCharArray())
		{
			// If we want to print % we need to escape it with %%, X stands for a hexadecimal/integer number, 02
			// means at least two digits and if less prepend it with a 0
			// space = %20, linebreak = %0A
			out += Character.isLetterOrDigit(ch) ? ch : String.format("%%%02X", (int) ch);
		}
		return out;
	}
}
