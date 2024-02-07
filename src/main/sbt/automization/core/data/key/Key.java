package sbt.automization.core.data.key;

/**
 * Gibt die Struktur für Spaltenbezeichnung an, mittels derer in der {@link sbt.automization.core.data.DataTable}
 * Zellwerte abgelegt und gezogen werden können.
 */
public interface Key
{
    /**
     * Gibt den vollständigen Schlüssel zurück, um auf einen Zellwert zuzugreifen.
     * @apiNote <h1>DO NOT OVERRIDE!!!</h1>
     * @return
     */
    default String getKey(){
        return String.format("%s.%s",getKeyPrefix(),getKeySuffix());
    }

    /**
     * @apiNote <h1>DARF NICHT AUF EINEN PUNKT ENDEN!!!</h1>
     * @return Der Spalten Prefix, der die Gruppierung der Spalten angibt.
     */
    String getKeyPrefix();
    /**
     * @return Den Spaltensuffix, der die konkrete Spalte angibt.
     */
    String getKeySuffix();
}
