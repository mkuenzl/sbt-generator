package sbt.automization.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class DataTableOldComparableTest
{
	DataTableOld dataTableFB1;
	DataTableOld dataTableFB2;
	DataTableOld dataTableFB3;
	DataTableOld dataTableFB4;
	DataTableOld dataTableFB17;
	DataTableOld dataTableFB22;

	DataTableOld dataTable1;
	DataTableOld dataTable2;
	DataTableOld dataTable3;

	@Before
	public void createExplorationSites()
	{
		Map<String, String> site1 = new HashMap<>(){{
			put("ERK_ID", "FB1");
			put("ERK_NUMMER", "1");
		}};

		dataTableFB1 = new DataTableOld(site1);

		Map<String, String> site2 = new HashMap<>(){{
			put("ERK_ID", "FB2");
			put("ERK_NUMMER", "2");
		}};

		dataTableFB2 = new DataTableOld(site2);

		Map<String, String> site3 = new HashMap<>(){{
			put("ERK_ID", "FB3");
			put("ERK_NUMMER", "3");
		}};

		dataTableFB3 = new DataTableOld(site3);

		Map<String, String> site4 = new HashMap<>(){{
			put("ERK_ID", "FB4");
			put("ERK_NUMMER", "4");
		}};

		dataTableFB4 = new DataTableOld(site4);

		Map<String, String> site17 = new HashMap<>(){{
			put("ERK_ID", "FB17");
			put("ERK_NUMMER", "17");
		}};

		dataTableFB17 = new DataTableOld(site17);

		Map<String, String> site22 = new HashMap<>(){{
			put("ERK_ID", "FB22");
			put("ERK_NUMMER", "22");
		}};

		dataTableFB22 = new DataTableOld(site22);

		Map<String, String> site5 = new HashMap<>(){{
			put("ERK_ID", "5");
			put("ERK_NUMMER", "5");
		}};

		dataTable1 = new DataTableOld(site5);

		Map<String, String> site6 = new HashMap<>(){{
			put("ERK_ID", "6");
			put("ERK_NUMMER", "6");
		}};

		dataTable2 = new DataTableOld(site6);

		Map<String, String> site7 = new HashMap<>(){{
			put("ERK_ID", "7");
			put("ERK_NUMMER", "7");
		}};

		dataTable3 = new DataTableOld(site7);


	}

	@Test
	public void sortWithText()
	{
		List<DataTableOld> dataTables = new ArrayList<>();

		dataTables.add(dataTableFB1);
		dataTables.add(dataTableFB2);
		dataTables.add(dataTableFB17);
		dataTables.add(dataTableFB22);
		dataTables.add(dataTableFB3);
		dataTables.add(dataTableFB4);


		List<DataTableOld> explorationSitesToCompare = new ArrayList<>();

		explorationSitesToCompare.add(dataTableFB1);
		explorationSitesToCompare.add(dataTableFB2);
		explorationSitesToCompare.add(dataTableFB3);
		explorationSitesToCompare.add(dataTableFB4);
		explorationSitesToCompare.add(dataTableFB17);
		explorationSitesToCompare.add(dataTableFB22);

		Collections.sort(dataTables);

		Assert.assertEquals(explorationSitesToCompare, dataTables);
	}

	@Deprecated
	@Test
	public void sortWithOutText()
	{
		List<DataTableOld> dataTables = new ArrayList<>();

		dataTables.add(dataTable3);
		dataTables.add(dataTable2);
		dataTables.add(dataTable1);



		List<DataTableOld> explorationSitesToCompare = new ArrayList<>();

		explorationSitesToCompare.add(dataTable1);
		explorationSitesToCompare.add(dataTable2);
		explorationSitesToCompare.add(dataTable3);


		Collections.sort(dataTables);

		Assert.assertEquals(explorationSitesToCompare, dataTables);
	}
}
