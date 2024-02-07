package sbt.automization.core.data.key;

/**
 * Spaltenschlüssensammlung für jede Spalte die mit <b>"PARAMETER.CHEMISTRY."</b> beginnt, um auf den Zellwert zuzugreifen.
 */
public enum ChemistryKey implements Key
{
	ASBESTOS("ASBEST"),
	BTEX("BTEX"),
	DECISION_SUPPORT("ENTSCHEIDUNGSHILFE"),
	DEPV("DEPV"),
	EBV_CONSTRUCTION_WASTE("EBV_BAUSCHUTT"),
	EBV_MONITORING_VALUE("EBV_UEBERWACHUNGSWERT"),
	EBV_SOIL("EBV_BODEN"),
	EOX("EOX"),
	FCKW("FCKW"),
	HBCD("HBCD"),
	ICP_SCREENING("ICP_SCREENING"),
	ID("ID"),
	KMF("KMF"),
	LAGA_BO("LAGA_BO"),
	LAGA_RC("LAGA_RC"),
	LAGA_RC_ORIENTATION("LAGA_RC_ORIENTIERUNGSWERT"),
	LFS("LFS"),
	MKUEM("MKUEM"),
	MKW_C22("MKW_C10_C22"),
	MKW_C40("MKW_C10_C40"),
	MUFV("MUFV"),
	MUFV_PARAMETER("MUFV_PARAMETER"),
	PAK("PAK"),
	PCB("PCB"),
	PHENOL("PHENOLE"),
	REKU("REKU"),
	RUVA("RUVA"),
	SULFATE("SULFAT"),
	TL_ROCK_STRATUM("TL_GESTEIN"),
	WASTE_KEY("ABFALLSCHLUESSEL"),
	WASTE_WOOD_ORDINANCE("ALTHOLZ_VERORDNUNG");

	private static final String KEY_PREFIX = "PARAMETER.CHEMISTRY";
	private final String keySuffix;

	ChemistryKey(String keySuffix)
	{
		this.keySuffix = keySuffix;
	}

	@Override
	public String getKeyPrefix()
	{
		return KEY_PREFIX;
	}

	@Override
	public String getKeySuffix()
	{
		return keySuffix;
	}
}
