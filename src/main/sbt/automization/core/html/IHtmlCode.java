package sbt.automization.core.html;

public interface IHtmlCode
{
	/**
	 * Method is used as toString
	 *
	 * @return a String like <htmlObject attributes> content </htmlObject>
	 */
	String appendTag();

	void appendAttribute(String attribute, String content);

	void appendContent(String content);
}
