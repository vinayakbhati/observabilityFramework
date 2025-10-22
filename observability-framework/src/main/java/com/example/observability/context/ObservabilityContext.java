
package com.example.observability.context;

import java.util.ArrayList;
import java.util.List;

public class ObservabilityContext {
    private long startTime = System.currentTimeMillis();
    private final List<String> tracePath = new ArrayList<>();

    public void addTrace(String info) {
        tracePath.add(info);
    }

    public List<String> getTracePath() {
        return tracePath;
    }

    public long getDuration() {
        return System.currentTimeMillis() - startTime;
    }
}
