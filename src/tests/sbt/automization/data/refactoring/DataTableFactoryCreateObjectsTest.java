package sbt.automization.data.refactoring;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTableFactoryCreateObjectsTest
{
	@Test
	public void createEmptyMapTest()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>());
			add(new HashMap<>());
			add(new HashMap<>());
		}};

		List<DataTable> target = DataTableFactory.getProbes(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>();

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createWrongMapKeyTest()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("NOISE.ID", "FB1");
				put("NOISE.SAMPLE", "SAMPLE");
				put("NOISE.LP.ID", "LP1");
				put("NOISE.CHEMISTRY.ID", "C1");
			}});
			add(new HashMap<>()
			{{
				put("TEST.ID", "FB1");
				put("TEST.SAMPLE", "SAMPLE");
				put("TEST.LP.ID", "LP1");
				put("TEST.CHEMISTRY.ID", "C1");
			}});
		}};

		List<DataTable> target = DataTableFactory.getProbes(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>();

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createEmptyListTest()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>();

		List<DataTable> target = DataTableFactory.getProbes(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>();

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createSingleProbe()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("PROBE.SAMPLE", "SAMPLE");
				put("PROBE.LP.ID", "LP1");
				put("PARAMETER.CHEMISTRY.ID", "C1");
			}});
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("PROBE.SAMPLE", "SAMPLE");
				put("SAMPLE.ID", "2");
				put("PROBE.LP.ID", "LP1");
				put("PARAMETER.CHEMISTRY.ID", "C1");
				put("PARAMETER.RUK.ID", "A1");
			}});
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("PROBE.SAMPLE", "SAMPLE");
				put("SAMPLE.ID", "3");
				put("PROBE.LP.ID", "LP1");
			}});
		}};

		List<DataTable> target = DataTableFactory.getProbes(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(new Probe(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("PROBE.SAMPLE", "SAMPLE");
				put("PROBE.LP.ID", "LP1");
			}}));
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createMultipleProbes()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("PROBE.LP.ID", "LP1");
			}});
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB2");
			}});
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB3");
				put("PROBE.LP.ID", "LP1");
			}});
		}};

		List<DataTable> target = DataTableFactory.getProbes(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(new Probe(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("PROBE.LP.ID", "LP1");
			}}));
			add(new Probe(new HashMap<>()
			{{
				put("PROBE.ID", "FB2");
			}}));
			add(new Probe(new HashMap<>()
			{{
				put("PROBE.ID", "FB3");
				put("PROBE.LP.ID", "LP1");
			}}));
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createSingleSample()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "1");
				put("PROBE.ID", "noise");
				put("PARAMETER.RUK.ID", "noise");
			}});
		}};

		List<DataTable> target = DataTableFactory.getSamples(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(new Sample(new HashMap<>()
			{{
				put("SAMPLE.ID", "1");
				put("SAMPLE.PROBE.ID", "FB1");
			}}));
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createMultipleSample()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "1");
			}});
			add(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "2");
			}});
			add(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB2");
				put("SAMPLE.ID", "1");
			}});
			add(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB3");
				put("SAMPLE.ID", "1");
			}});
		}};

		List<DataTable> target = DataTableFactory.getSamples(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(new Sample(new HashMap<>()
			{{
				put("SAMPLE.ID", "1");
				put("SAMPLE.PROBE.ID", "FB1");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "2");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB2");
				put("SAMPLE.ID", "1");
			}}));
			add(new Sample(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB3");
				put("SAMPLE.ID", "1");
			}}));
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createSingleSampleWithParameters()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "1");
				put("SAMPLE.CHEMISTRY.ID", "C1");
				put("SAMPLE.RUK.ID", "A1");
				put("PARAMETER.CHEMISTRY.ID", "C1");
				put("PARAMETER.CHEMISTRY.MUFV", "nicht gefährlich");
				put("PARAMETER.RUK.ID", "A1");
			}});
		}};

		List<DataTable> target = DataTableFactory.getSamples(dataTable);

		Sample expectedSample = new Sample(new HashMap<>()
		{{
			put("SAMPLE.PROBE.ID", "FB1");
			put("SAMPLE.ID", "1");
			put("SAMPLE.CHEMISTRY.ID", "C1");
			put("SAMPLE.RUK.ID", "A1");
		}});

		Parameter expectedFirstParameter = new Parameter(new HashMap<>()
		{{
			put("PARAMETER.CHEMISTRY.ID", "C1");
			put("PARAMETER.CHEMISTRY.MUFV", "nicht gefährlich");
		}});
		expectedSample.addParameter(expectedFirstParameter);

		Parameter expectedSecondParameter = new Parameter(new HashMap<>()
		{{
			put("PARAMETER.RUK.ID", "A1");
		}});
		expectedSample.addParameter(expectedSecondParameter);


		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(expectedSample);
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createSingleParameter()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP1");
				put("PARAMETER.LP.WERT_1", "1");
			}});
		}};

		List<DataTable> target = DataTableFactory.getParameters(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(new Parameter(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP1");
				put("PARAMETER.LP.WERT_1", "1");
			}}));
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createMultipleParametersFromRow()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP1");
				put("PARAMETER.LP.WERT_1", "1");
				put("PARAMETER.CHEMISTRY.ID", "C1");
				put("PARAMETER.CHEMISTRY.MUFV", "mufv");
				put("PARAMETER.RUK.ID", "A1");
				put("PARAMETER.RUK.SAMPLE", "single");
			}});
		}};

		List<DataTable> target = DataTableFactory.getParameters(dataTable);

		//Watch out there may be different orderings!
		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(new Parameter(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP1");
				put("PARAMETER.LP.WERT_1", "1");
			}}));
			add(new Parameter(new HashMap<>()
			{{
				put("PARAMETER.CHEMISTRY.ID", "C1");
				put("PARAMETER.CHEMISTRY.MUFV", "mufv");
			}}));
			add(new Parameter(new HashMap<>()
			{{
				put("PARAMETER.RUK.ID", "A1");
				put("PARAMETER.RUK.SAMPLE", "single");
			}}));
		}};


		Assert.assertEquals(expected, target);
	}

	@Test
	public void createMultipleParametersFromTable()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP1");
				put("PARAMETER.LP.WERT_1", "1");
			}});
			add(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP2");
				put("PARAMETER.LP.WERT_1", "2");
			}});
			add(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP3");
				put("PARAMETER.LP.WERT_1", "3");
			}});
		}};

		List<DataTable> target = DataTableFactory.getParameters(dataTable);

		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(new Parameter(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP1");
				put("PARAMETER.LP.WERT_1", "1");
			}}));
			add(new Parameter(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP2");
				put("PARAMETER.LP.WERT_1", "2");
			}}));
			add(new Parameter(new HashMap<>()
			{{
				put("PARAMETER.LP.ID", "LP3");
				put("PARAMETER.LP.WERT_1", "3");
			}}));
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createSingleProbeWithSamplesAndParameters()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "1");
				put("PROBE.LP.ID", "LP1");
				put("PARAMETER.LP.ID", "LP1");
				put("SAMPLE.CHEMISTRY.ID", "C1");
				put("PARAMETER.CHEMISTRY.ID", "C1");
			}});
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("SAMPLE.ID", "2");
				put("PROBE.LP.ID", "LP1");
				put("PARAMETER.LP.ID", "LP1");
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.RUK.ID", "A1");
				put("PARAMETER.RUK.ID", "A1");
			}});
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("SAMPLE.ID", "3");
				put("SAMPLE.PROBE.ID", "FB1");
				put("PROBE.LP.ID", "LP1");
				put("PARAMETER.LP.ID", "LP1");
			}});
		}};

		List<DataTable> target = DataTableFactory.getProbes(dataTable);

		Probe expectedProbe = new Probe(new HashMap<>()
		{{
			put("PROBE.ID", "FB1");
			put("PROBE.LP.ID", "LP1");
		}});

		Parameter lpParameter = new Parameter(new HashMap<>()
		{{
			put("PARAMETER.LP.ID", "LP1");
		}});
		expectedProbe.addParameter(lpParameter);

		//SAMPLE 1
		Sample firstSample = new Sample(new HashMap<>()
		{{
			put("SAMPLE.ID", "1");
			put("SAMPLE.PROBE.ID", "FB1");
			put("SAMPLE.CHEMISTRY.ID", "C1");
		}});

		firstSample.addParameter(new Parameter(new HashMap<>()
		{{
			put("PARAMETER.CHEMISTRY.ID", "C1");
		}}));

		expectedProbe.addSample(firstSample);

		//SAMPLE 2
		Sample secondSample = new Sample(new HashMap<>()
		{{
			put("SAMPLE.ID", "2");
			put("SAMPLE.RUK.ID", "A1");
			put("SAMPLE.PROBE.ID", "FB1");
		}});

		secondSample.addParameter(new Parameter(new HashMap<>()
		{{
			put("PARAMETER.RUK.ID", "A1");
		}}));

		expectedProbe.addSample(secondSample);

		//SAMPLE 3
		Sample thirdSample = new Sample(new HashMap<>()
		{{
			put("SAMPLE.ID", "3");
			put("SAMPLE.PROBE.ID", "FB1");
		}});
		expectedProbe.addSample(thirdSample);


		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(expectedProbe);
		}};

		Assert.assertEquals(expected, target);
	}

	@Test
	public void createSingleProbeWithSamples()
	{
		ArrayList<Map<String, String>> dataTable = new ArrayList<>()
		{{
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "1");
			}});
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "2");
			}});
			add(new HashMap<>()
			{{
				put("PROBE.ID", "FB1");
				put("SAMPLE.PROBE.ID", "FB1");
				put("SAMPLE.ID", "3");
			}});
		}};

		List<DataTable> target = DataTableFactory.getProbes(dataTable);

		Probe expectedProbe = new Probe(new HashMap<>()
		{{
			put("PROBE.ID", "FB1");
		}});

		expectedProbe.addSample(new Sample(new HashMap<>()
		{{
			put("SAMPLE.ID", "1");
			put("SAMPLE.PROBE.ID", "FB1");
		}}));

		expectedProbe.addSample(new Sample(new HashMap<>()
		{{
			put("SAMPLE.ID", "2");
			put("SAMPLE.PROBE.ID", "FB1");
		}}));

		expectedProbe.addSample(new Sample(new HashMap<>()
		{{
			put("SAMPLE.ID", "3");
			put("SAMPLE.PROBE.ID", "FB1");
		}}));

		ArrayList<DataTable> expected = new ArrayList<>()
		{{
			add(expectedProbe);
		}};

		Assert.assertEquals(expected, target);
	}
}
