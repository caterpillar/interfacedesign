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

    public void increaseSnapshotVersion() {
        snapshotVersion++;
    }

    public void increaseReleaseVersion() {
        releaseVersion++;
    }

    public String getVersion() {
        StringBuilder builder = new StringBuilder();
        builder.append(snapshotVersion).append(VERSION_SEPARATOR).append(releaseVersion);
        return builder.toString();
    }

    public int getSnapshotVersion() {
        return snapshotVersion;
    }

    public int getReleaseVersion() {
        return releaseVersion;
    }
}
