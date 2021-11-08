package sbt.automization.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class FileUtils
{
	private FileUtils()
	{
	}
	
	/**
	 * Method used to export files from with the program resource folder
	 *
	 * @param file specifies which file should be exported
	 * @throws IOException if anything happens while reading oder writing the file
	 */
	public static void copyFileTo(final String file, final String path) throws IOException
	{
		String pathname = System.getProperty("user.dir").concat(File.separator).concat(file);
		
		if (new File(pathname).exists()) return;
		
		URL inputUrl = FileUtils.class.getResource(path + file);
		
		File destinationFile = new File(pathname);
		
		if (!destinationFile.exists() && !destinationFile.isDirectory())
		{
			Path pathToDirectory = Paths.get(destinationFile.getPath()).getParent();
			if (!Files.exists(pathToDirectory))
			{
				Files.createDirectories(pathToDirectory);
			}
			if (inputUrl != null && destinationFile != null)
			{
				org.apache.commons.io.FileUtils.copyURLToFile(inputUrl, destinationFile);
			}
		}
	}
	
	/**
	 * Method used to export files from with the program resource folder
	 *
	 * @param file specifies which file should be exported
	 * @throws IOException if anything happens while reading oder writing the file
	 */
	public static void copyFileToUserDirectory(final String file) throws IOException
	{
		String pathname = System.getProperty("user.dir").concat(File.separator).concat(file);
		
		if (new File(pathname).exists()) return;
		
		URL inputUrl = FileUtils.class.getResource(file);
		
		File destinationFile = new File(pathname);
		
		if (!destinationFile.exists() && !destinationFile.isDirectory())
		{
			Path path = Paths.get(destinationFile.getPath()).getParent();
			if (!Files.exists(path))
			{
				Files.createDirectories(path);
			}
			if (inputUrl != null && destinationFile != null)
			{
				org.apache.commons.io.FileUtils.copyURLToFile(inputUrl, destinationFile);
			}
		}
	}
	
	public static String parseFileToString(String filePath)
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		try (InputStreamReader inputStreamReader =
				     new InputStreamReader(Objects.requireNonNull(FileUtils.class.getResourceAsStream(filePath)), StandardCharsets.UTF_8);
		     BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
		{
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(line);
				stringBuilder.append("\n");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return stringBuilder.toString();
	}
}
