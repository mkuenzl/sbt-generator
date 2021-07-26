package sbt.automization.data;

import java.util.Map;

public interface ISample {
    void addInformation(String key, String value);
    String getInformation(ReferenceKey tag);
    String getInformation(String key);
    Map<String, String> getInformationMap();
}
