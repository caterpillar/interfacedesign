package org.interfacedesign.listener;

import org.apache.commons.fileupload.ProgressListener;

public class FileUploadListener implements ProgressListener {
    private boolean complete = false;
    private long bytesRead;
    private long contentLength;
    private int items;

    public FileUploadListener() {
    }

    public void reset() {
        this.complete = false;
        this.bytesRead = 0L;
        this.contentLength = 0L;
        this.items = 0;
    }

    public void complete() {
        this.complete = true;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void update(long pBytesRead, long pContentLength, int pItems) {
        this.bytesRead = pBytesRead;
        this.contentLength = pContentLength;
        this.items = pItems;
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public int getItems() {
        return this.items;
    }
}
