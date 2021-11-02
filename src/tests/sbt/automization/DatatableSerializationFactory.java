package sbt.automization;

import sbt.automization.core.data.DataTable;

import java.io.*;

/**
 * Class with static utility methods
 */
public final class DatatableSerializationFactory
{
	private DatatableSerializationFactory() {}

	/**
	 * Method is used for creating and saving test exploration site objects
	 *
	 * @param dataTable the object to serialize
	 * @param fileName  the name of the created file
	 */
	public static void serializeDatatableToFile(DataTable dataTable, String fileName)
	{
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName) ;
		     ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream))
		{
			outputStream.writeObject(dataTable);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method is used for reading test exploration site objects
	 *
	 * @param filePath the location of the serialized object file
	 * @return a ExplorationSite object
	 */
	public static DataTable readSerializedDatatable(String filePath)
	{
		DataTable table = null;

		try (FileInputStream fileInputStream = new FileInputStream(filePath) ;
		     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
		{
			table = (DataTable) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return table;
	}
}
