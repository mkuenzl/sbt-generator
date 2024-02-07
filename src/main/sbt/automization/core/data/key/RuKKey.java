package sbt.automization.core.data.key;

/**
 * Spaltenschlüssensammlung für jede Spalte die mit <b>"PARAMETER.RUK."</b> beginnt, um auf den Zellwert zuzugreifen.
 */
public enum RuKKey implements Key
{
	ID("ID"),
	NUMBER("NUMMER"),
	SAMPLE("PROBENART"),
	VALUE("WERT");

	private static final String KEY_PREFIX = "PARAMETER.RUK";
	private final String keySuffix;

	RuKKey(String keySuffix)
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
