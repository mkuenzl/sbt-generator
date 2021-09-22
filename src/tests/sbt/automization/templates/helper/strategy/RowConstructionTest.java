package sbt.automization.templates.helper.strategy;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sbt.automization.data.DataTable;
import sbt.automization.data.Outcrop;
import sbt.automization.data.Probe;
import sbt.automization.data.Sample;
import sbt.automization.data.key.ProbeKey;
import sbt.automization.data.key.SampleKey;
import sbt.automization.format.text.StandardCellTextFormatter;
import sbt.automization.styles.StyleParameterBuilder;
import sbt.automization.templates.DatatableInitializer;
import sbt.automization.templates.helper.RowProvider;

import java.util.HashMap;
import java.util.List;

public class RowConstructionTest
{
	private List<DataTable> dataTables;

	@Before
	public void initializeDataTables() throws Exception
	{
		dataTables = new DatatableInitializer().initializeDatatables();
	}

	@Test
	public void combineAllSamplesTest()
	{
		Probe probe = new Probe(new HashMap<>()
		{{
			put(ProbeKey.ID.getKey(), "FB1");
		}});
		Sample sample = new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "GEBAEUDE");
		}});
		sample.setProbe(probe);

		probe.addSample(sample);
		probe.addSample(sample);
		probe.addSample(sample);

		IdRow idRow = new IdRow();
		idRow.setTables(List.of(probe));
		idRow.setOutcrop(Outcrop.BUILDING.toString());
		idRow.setStyle(new StyleParameterBuilder()
				.setRowClass("Normal")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("110")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("60")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build());

		String row = idRow.buildWithSamplesCombined();

		System.out.println(row);
	}


	@Test
	public void combineNoSamplesTest()
	{
		Probe probe = new Probe(new HashMap<>()
		{{
			put(ProbeKey.ID.getKey(), "FB1");
		}});
		Sample firstSample = new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "GEBAEUDE");
			put(SampleKey.COMPRESSIVE_STRENGTH.getKey(), "+");
		}});
		firstSample.setProbe(probe);

		Sample secondSample = new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "GEBAEUDE");
			put(SampleKey.COMPRESSIVE_STRENGTH.getKey(), "-");
		}});
		secondSample.setProbe(probe);

		probe.addSample(firstSample);
		probe.addSample(secondSample);
		probe.addSample(firstSample);

		CompressiveStrengthRow row = new CompressiveStrengthRow();
		row.setTables(List.of(probe));
		row.setOutcrop(Outcrop.BUILDING.toString());
		row.setStyle(new StyleParameterBuilder()
				.setRowClass("Normal")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("110")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("60")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build());

		String rowString = row.buildWithSamplesCombined();

		System.out.println(rowString);
	}


	@Test
	public void combineTwoSamplesTest()
	{
		Probe probe = new Probe(new HashMap<>()
		{{
			put(ProbeKey.ID.getKey(), "FB1");
		}});
		Sample firstSample = new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "GEBAEUDE");
			put(SampleKey.COMPRESSIVE_STRENGTH.getKey(), "+");
		}});
		firstSample.setProbe(probe);

		Sample secondSample = new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "GEBAEUDE");
			put(SampleKey.COMPRESSIVE_STRENGTH.getKey(), "-");
		}});
		secondSample.setProbe(probe);

		probe.addSample(firstSample);
		probe.addSample(firstSample);
		probe.addSample(secondSample);
		probe.addSample(secondSample);

		CompressiveStrengthRow row = new CompressiveStrengthRow();
		row.setTables(List.of(probe));
		row.setOutcrop(Outcrop.BUILDING.toString());
		row.setStyle(new StyleParameterBuilder()
				.setRowClass("Normal")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("110")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("60")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build());

		String rowString = row.buildWithSamplesCombined();

		System.out.println(rowString);
	}

	@Test
	public void combineOneSamplesTest()
	{
		Probe probe = new Probe(new HashMap<>()
		{{
			put(ProbeKey.ID.getKey(), "FB1");
		}});
		Sample firstSample = new Sample(new HashMap<>()
		{{
			put(SampleKey.OUTCROP.getKey(), "GEBAEUDE");
			put(SampleKey.COMPRESSIVE_STRENGTH.getKey(), "+");
		}});
		firstSample.setProbe(probe);

		probe.addSample(firstSample);

		CompressiveStrengthRow row = new CompressiveStrengthRow();
		row.setTables(List.of(probe));
		row.setOutcrop(Outcrop.BUILDING.toString());
		row.setStyle(new StyleParameterBuilder()
				.setRowClass("Normal")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("110")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("60")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build());

		String rowString = row.buildWithSamplesCombined();

		System.out.println(rowString);
	}

	@Test
	public void combineProbeWithoutSamplesTest()
	{
		Probe probe = new Probe(new HashMap<>()
		{{
			put(ProbeKey.ID.getKey(), "FB1");
		}});

		CompressiveStrengthRow row = new CompressiveStrengthRow();
		row.setTables(List.of(probe));
		row.setOutcrop(Outcrop.BUILDING.toString());
		row.setStyle(new StyleParameterBuilder()
				.setRowClass("Normal")
				.setHeaderCellClass("NormalHeader")
				.setHeaderCellWidth("110")
				.setNormalCellClass("NormalBold")
				.setNormalCellWidth("60")
				.setUnitCellClass("Normal6")
				.setLegendCellClass("NormalHeaderSmallFont")
				.setTextFormatter(new StandardCellTextFormatter())
				.build());

		String rowString = row.buildWithSamplesCombined();

		System.out.println(rowString);
	}

}
