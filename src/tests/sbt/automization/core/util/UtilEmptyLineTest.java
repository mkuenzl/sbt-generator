package sbt.automization.core.util;

import org.junit.Assert;
import org.junit.Test;
import sbt.automization.core.data.DataTable;
import sbt.automization.core.data.Outcrop;
import sbt.automization.core.data.Probe;
import sbt.automization.core.data.Sample;
import sbt.automization.core.data.key.ProbeKey;
import sbt.automization.core.data.key.SampleKey;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UtilEmptyLineTest
{
	@Test
	public void explorationSiteNoEmptyValueTest()
	{
		String identifier = ProbeKey.ID.getKey();

		DataTable dataTableOne = new Probe(new HashMap<>()
		{{
			put(identifier, "FB1");
		}});
		DataTable dataTableTwo = new Probe(new HashMap<>()
		{{
			put(identifier, "FB2");
		}});
		DataTable dataTableThree = new Probe(new HashMap<>()
		{{
			put(identifier, "FB3");
		}});

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", ProbeKey.ID);

		Assert.assertTrue(exists);
	}

	@Test
	public void explorationSiteSomeEmptyValuesTest()
	{
		String identifier = ProbeKey.ID.getKey();

		DataTable dataTableOne = new Probe(new HashMap<>()
		{{
			put(identifier, "FB1");
		}});
		DataTable dataTableTwo = new Probe();
		DataTable dataTableThree = new Probe(new HashMap<>()
		{{
			put(identifier, "FB3");
		}});

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", ProbeKey.ID);

		Assert.assertTrue(exists);
	}

	@Test
	public void explorationSiteAllEmptyValuesTest()
	{
		String identifier = ProbeKey.ID.getKey();

		DataTable dataTableOne = new Probe(new HashMap<>() {});
		DataTable dataTableTwo = new Probe(new HashMap<>() {});
		DataTable dataTableThree = new Probe(new HashMap<>() {});

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", ProbeKey.ID);

		Assert.assertFalse(exists);
	}

	@Test
	public void explorationSiteAnEmptyListProvidedTest()
	{
		List<DataTable> dataTables = List.of();

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", ProbeKey.ID);

		Assert.assertFalse(exists);
	}

	@Test
	public void layerNoEmptyValueTest()
	{
		String identifier = SampleKey.TYPE.getKey();

		Sample emptyLayerSample = new Sample(new HashMap<>()
		{{
			put(identifier, "");
		}});
		Sample nonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(identifier, "value");
		}});


		Probe dataTableOne = new Probe();
		dataTableOne.addSample(emptyLayerSample);
		dataTableOne.addSample(nonEmptyLayerSample);
		dataTableOne.addSample(nonEmptyLayerSample);

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(nonEmptyLayerSample);
		dataTableTwo.addSample(nonEmptyLayerSample);

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(nonEmptyLayerSample);
		dataTableThree.addSample(emptyLayerSample);

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", SampleKey.TYPE);

		Assert.assertTrue(exists);
	}

	@Test
	public void layerSomeEmptyValuesTest()
	{
		String identifier = SampleKey.TYPE.getKey();

		Sample emptyLayerSample = new Sample(new HashMap<>()
		{{
			put(identifier, "");
		}});
		Sample nonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(identifier, "value");
		}});


		Probe dataTableOne = new Probe();
		dataTableOne.addSample(emptyLayerSample);
		dataTableOne.addSample(nonEmptyLayerSample);
		dataTableOne.addSample(nonEmptyLayerSample);

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(emptyLayerSample);
		dataTableTwo.addSample(emptyLayerSample);

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(nonEmptyLayerSample);
		dataTableThree.addSample(emptyLayerSample);

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", SampleKey.TYPE);

		Assert.assertTrue(exists);
	}

	@Test
	public void layerAllEmptyValuesTest()
	{
		String identifier = SampleKey.TYPE.getKey();

		Sample emptyLayerSample = new Sample(new HashMap<>()
		{{
			put(identifier, "");
		}});
		Sample nonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(identifier, "value");
		}});


		Probe dataTableOne = new Probe();
		dataTableOne.addSample(emptyLayerSample);
		dataTableOne.addSample(emptyLayerSample);
		dataTableOne.addSample(emptyLayerSample);

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(emptyLayerSample);
		dataTableTwo.addSample(emptyLayerSample);

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(emptyLayerSample);
		dataTableThree.addSample(emptyLayerSample);

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", SampleKey.TYPE);

		Assert.assertFalse(exists);
	}

	@Test
	public void layerAnEmptyListProvidedTest()
	{
		String identifier = SampleKey.TYPE.getKey();

		Sample emptyLayerSample = new Sample(new HashMap<>()
		{{
			put(identifier, "");
		}});
		Sample nonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(identifier, "value");
		}});


		Probe dataTableOne = new Probe();

		Probe dataTableTwo = new Probe();

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(emptyLayerSample);
		dataTableThree.addSample(emptyLayerSample);

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, "", SampleKey.TYPE);

		Assert.assertFalse(exists);
	}

	@Test
	public void layerFromOutcropNoEmptyValueTest()
	{
		String valueIdentifier = SampleKey.TYPE.getKey();
		String outcropIdentifier = SampleKey.OUTCROP.getKey();
		String outcropValue = Outcrop.GOB.toString();

		Sample outcropEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, outcropValue);
			put(valueIdentifier, "");
		}});
		Sample outcropNonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, outcropValue);
			put(valueIdentifier, "value");
		}});
		Sample wrongOutcropEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, "nonExistent");
			put(valueIdentifier, "");
		}});
		Sample wrongOutcropNonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, "nonExistent");
			put(valueIdentifier, "value");
		}});


		Probe dataTableOne = new Probe();
		dataTableOne.addSample(outcropEmptyLayerSample);
		dataTableOne.addSample(outcropNonEmptyLayerSample);
		dataTableOne.addSample(outcropNonEmptyLayerSample);

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(outcropEmptyLayerSample);
		dataTableTwo.addSample(outcropEmptyLayerSample);

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(outcropNonEmptyLayerSample);
		dataTableThree.addSample(outcropEmptyLayerSample);

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcropValue, SampleKey.TYPE);

		Assert.assertTrue(exists);
	}

	@Test
	public void layerFromOutcropSomeEmptyValuesTest()
	{
		String valueIdentifier = SampleKey.TYPE.getKey();
		String outcropIdentifier = SampleKey.OUTCROP.getKey();
		String outcropValue = Outcrop.GOB.toString();

		Sample outcropEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, outcropValue);
			put(valueIdentifier, "");
		}});
		Sample outcropNonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, outcropValue);
			put(valueIdentifier, "value");
		}});
		Sample wrongOutcropEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, "nonExistent");
			put(valueIdentifier, "");
		}});
		Sample wrongOutcropNonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, "nonExistent");
			put(valueIdentifier, "value");
		}});


		Probe dataTableOne = new Probe();
		dataTableOne.addSample(outcropEmptyLayerSample);
		dataTableOne.addSample(outcropNonEmptyLayerSample);
		dataTableOne.addSample(outcropNonEmptyLayerSample);

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(wrongOutcropEmptyLayerSample);
		dataTableTwo.addSample(wrongOutcropNonEmptyLayerSample);

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(wrongOutcropEmptyLayerSample);
		dataTableThree.addSample(wrongOutcropEmptyLayerSample);

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcropValue, SampleKey.TYPE);

		Assert.assertTrue(exists);
	}

	@Test
	public void layerFromOutcropAllEmptyValuesTest()
	{
		String valueIdentifier = SampleKey.TYPE.getKey();
		String outcropIdentifier = SampleKey.OUTCROP.getKey();
		String outcropValue = Outcrop.GOB.toString();

		Sample outcropEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, outcropValue);
			put(valueIdentifier, "");
		}});
		Sample outcropNonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, outcropValue);
			put(valueIdentifier, "value");
		}});
		Sample wrongOutcropEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, "nonExistent");
			put(valueIdentifier, "");
		}});
		Sample wrongOutcropNonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, "nonExistent");
			put(valueIdentifier, "value");
		}});


		Probe dataTableOne = new Probe();
		dataTableOne.addSample(wrongOutcropEmptyLayerSample);
		dataTableOne.addSample(wrongOutcropEmptyLayerSample);
		dataTableOne.addSample(wrongOutcropEmptyLayerSample);

		Probe dataTableTwo = new Probe();
		dataTableTwo.addSample(wrongOutcropEmptyLayerSample);
		dataTableTwo.addSample(wrongOutcropNonEmptyLayerSample);

		Probe dataTableThree = new Probe();
		dataTableThree.addSample(wrongOutcropEmptyLayerSample);
		dataTableThree.addSample(wrongOutcropEmptyLayerSample);

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcropValue, SampleKey.TYPE);

		Assert.assertFalse(exists);
	}

	@Test
	public void layerFromOutcropAnEmptyListProvidedTest()
	{
		String valueIdentifier = SampleKey.TYPE.getKey();
		String outcropIdentifier = SampleKey.OUTCROP.getKey();
		String outcropValue = Outcrop.GOB.toString();

		Sample outcropEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, outcropValue);
			put(valueIdentifier, "");
		}});
		Sample outcropNonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, outcropValue);
			put(valueIdentifier, "value");
		}});
		Sample wrongOutcropEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, "nonExistent");
			put(valueIdentifier, "");
		}});
		Sample wrongOutcropNonEmptyLayerSample = new Sample(new HashMap<>()
		{{
			put(outcropIdentifier, "nonExistent");
			put(valueIdentifier, "value");
		}});


		Probe dataTableOne = new Probe();

		Probe dataTableTwo = new Probe();

		Probe dataTableThree = new Probe();

		List<DataTable> dataTables = Arrays.asList(dataTableOne, dataTableTwo, dataTableThree);

		boolean exists = CheckDataAvailability.thereExistsAnTableWithData(dataTables, outcropValue, SampleKey.TYPE);

		Assert.assertFalse(exists);
	}

}
