package org.interfacedesign.core.domain.model.design.value;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lishaohua on 16-6-22.
 */
@Embeddable
public class InterfaceVersion {
    private static final String VERSION_SEPARATOR = ".";
    @Transient
    private int snapshotVersion = 0;
    @Transient
    private int releaseVersion = 0;
    private String version;

    public void increaseSnapshotVersion() {
        snapshotVersion++;
        generateVersion();
    }

    public void increaseReleaseVersion() {
        releaseVersion++;
        generateVersion();
    }

    public String getVersion() {
        return this.version;
    }

    private void generateVersion() {
        StringBuilder builder = new StringBuilder();
        builder.append(snapshotVersion).append(VERSION_SEPARATOR).append(releaseVersion);
        this.version = builder.toString();
    }

    public int getSnapshotVersion() {
        return snapshotVersion;
    }

    public int getReleaseVersion() {
        return releaseVersion;
    }
}
