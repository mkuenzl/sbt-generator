package sbt.automization.core.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileExport
{
	private FileExport() {}

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

		URL inputUrl = FileExport.class.getResource(path + file);

		File destinationFile = new File(pathname);

		if (! destinationFile.exists() && ! destinationFile.isDirectory())
		{
			Path pathToDirectory = Paths.get(destinationFile.getPath()).getParent();
			if (! Files.exists(pathToDirectory))
			{
				Files.createDirectories(pathToDirectory);
			}
			if (inputUrl != null && destinationFile != null)
			{
				FileUtils.copyURLToFile(inputUrl, destinationFile);
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

		URL inputUrl = FileExport.class.getResource(file);

		File destinationFile = new File(pathname);

		if (! destinationFile.exists() && ! destinationFile.isDirectory())
		{
			Path path = Paths.get(destinationFile.getPath()).getParent();
			if (! Files.exists(path))
			{
				Files.createDirectories(path);
			}
			if (inputUrl != null && destinationFile != null)
			{
				FileUtils.copyURLToFile(inputUrl, destinationFile);
			}
		}
	}
}
