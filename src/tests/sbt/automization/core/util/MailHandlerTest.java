package sbt.automization.core.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class MailHandlerTest
{
	@Ignore
	@Test
	public void mailHandlerUseTest() throws URISyntaxException, IOException
	{
		MailHandler mailHandler = new MailHandler();
		mailHandler.setMailTo("xy@company.com");
		mailHandler.setHeader("Mail Header");
		mailHandler.setBody("Mail Body, 2.0, Fügen");
		mailHandler.openMail();
	}
	
	@Ignore
	@Test
	public void mailHandlerUseTemplateTest() throws URISyntaxException, IOException
	{
		MailHandler mailHandler = new MailHandler();
		mailHandler.setMailTo("xy@company.com");
		mailHandler.setHeader("Mail Header");
		String template = FileUtils.parseFileToString("/report-mail-template.txt");
		mailHandler.setBody(template);
		mailHandler.openMail();
	}
	
	@Test
	public void uriEncodeSpecialCharacterSpaceTest()
	{
		String space = MailHandler.uriEncode(" ");
		Assert.assertEquals("Space should be %20", "%20", space);
	}
	
	@Test
	public void uriEncodeSpecialCharacterUmlautsTest()
	{
		String umlaut = MailHandler.uriEncode("ü");
		Assert.assertEquals( "ü", umlaut);
	}
	
	@Test
	public void uriEncodeSpecialCharacterNewLineTest()
	{
		String newLine = MailHandler.uriEncode("\n");
		Assert.assertEquals("Line break should be %0A", "%0A", newLine);
	}
	
	@Test
	public void uriEncodeSpecialCharacterNewLineBreakTest()
	{
		String newLine = MailHandler.uriEncode("\r");
		Assert.assertEquals("Line break should be %0D", "%0D", newLine);
	}
	
	@Test
	public void uriEncodeWordTest()
	{
		String word = MailHandler.uriEncode("TestString");
		Assert.assertEquals("TestString", word);
	}
	
	@Test
	public void uriEncodeIntegerTest()
	{
		String word = MailHandler.uriEncode("3");
		Assert.assertEquals("3", word);
	}
	
	@Test
	public void uriEncodeDoubleTest()
	{
		String word = MailHandler.uriEncode("3.0");
		Assert.assertEquals("3%2E0", word);
	}
	
	@Test
	public void uriEncodeEmptyTest()
	{
		String word = MailHandler.uriEncode("");
		Assert.assertEquals("", word);
	}
}
