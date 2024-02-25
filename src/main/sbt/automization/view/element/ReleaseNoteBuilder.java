package sbt.automization.view.element;

public final class ReleaseNoteBuilder
{
    public static final String RELEASE_NOTES_VERSION = "Release Notes Version";
    private final StringBuilder stringBuilder = new StringBuilder();

    public ReleaseNoteBuilder(String version)
    {
        stringBuilder.append(RELEASE_NOTES_VERSION)
                     .append(" ")
                     .append(version)
                     .append(":")
                     .append("\n\n");
    }

    public ReleaseNoteBuilder addReleaseNote(String releaseNote)
    {
        stringBuilder.append("- ")
                     .append(releaseNote)
                     .append("\n");
        return this;
    }

    public String buildReleaseNote()
    {
        return stringBuilder.toString();
    }
}
