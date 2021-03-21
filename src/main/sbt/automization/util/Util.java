package sbt.automization.util;

import sbt.automization.data.Erkundungsstelle;

import java.io.*;

public final class Util
{
	public static void serializeErkundungsstelleToFile(Erkundungsstelle erkundungsstelle, String fileName)
	{
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream outputStream = null;

		try
		{
			fileOutputStream = new FileOutputStream(fileName);
			outputStream = new ObjectOutputStream(fileOutputStream);

			outputStream.writeObject(erkundungsstelle);

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fileOutputStream != null)
			{
				try
				{
					assert outputStream != null;
					outputStream.flush();
					outputStream.close();
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

	public static Erkundungsstelle readSerializedErkundungsstelle(String filePath)
	{
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		Erkundungsstelle erkundungsstelle = null;

		try
		{
			fileInputStream = new FileInputStream(filePath);
			objectInputStream = new ObjectInputStream(fileInputStream);
			erkundungsstelle = (Erkundungsstelle) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		} finally
		{
			if (fileInputStream != null)
			{
				try
				{
					//noinspection ConstantConditions
					objectInputStream.close();
					fileInputStream.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return erkundungsstelle;
	}
}
