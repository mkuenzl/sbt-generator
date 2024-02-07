package sbt.automization.core.data.key;

/**
 * Spaltenschlüssensammlung für jede Spalte die mit <b>"PARAMETER.LP."</b> beginnt, um auf den Zellwert zuzugreifen.
 */
public enum LpKey implements Key
{
	ID("ID"),

	NUMBER("NUMMER"),
	VALUE_1("WERT_1"),
	VALUE_2("WERT_2"),
	VALUE_3("WERT_3"),
	MEAN("MITTELWERT"),
	EV("EV"),
	EV85("EV85"),
	EV2("EV2"),
	EV2_TARGET("EV2_SOLL");

	private static final String KEY_PREFIX = "PARAMETER.LP";
	private final String keySuffix;

	LpKey(String keySuffix)
	{
		this.keySuffix = keySuffix;
	}

	private static String addLPTag(String parameter)
	{
		return "PARAMETER.LP." + parameter;
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
