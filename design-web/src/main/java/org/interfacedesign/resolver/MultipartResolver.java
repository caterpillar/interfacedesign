package org.interfacedesign.resolver;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.interfacedesign.listener.FileUploadListener;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MultipartResolver extends CommonsMultipartResolver {
    public static final String UPLOAD_KEY = "uploadId";
    public static final String DEFAULT_UPLOAD_ID = "DEFAULT_UPLOAD_ID";
    public static final String IS_NATIVE_SUFFIX = "IS_NATIVE";
    public static final String IS_UPLOADING_SUFFIX = "IS_NATIVE";
    private static final Map<String, FileUpload> FILE_UPLOAD_MAP = Maps.newConcurrentMap();

    public MultipartResolver() {
    }

    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = this.determineEncoding(request);
        FileUpload fileUpload = this.prepareFileUpload(encoding);

        MultipartParsingResult var5;
        try {
            this.beforeParse(request, fileUpload);
            List ex = ((ServletFileUpload)fileUpload).parseRequest(request);
            var5 = this.parseFileItems(ex, encoding);
        } catch (SizeLimitExceededException var10) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), var10);
        } catch (FileUploadException var11) {
            throw new MultipartException("Could not parse multipart servlet request", var11);
        } finally {
            this.afterParse(request, fileUpload);
        }

        return var5;
    }

    private void beforeParse(HttpServletRequest request, FileUpload fileUpload) {
        String id = getUploadId(request);
        FILE_UPLOAD_MAP.put(id, fileUpload);
        request.getSession().setAttribute(id + "IS_NATIVE", Boolean.valueOf(true));
        if(fileUpload.getProgressListener() instanceof FileUploadListener) {
            ((FileUploadListener)fileUpload.getProgressListener()).reset();
        }

    }

    public static boolean isUploading(HttpServletRequest request) {
        return null != request.getSession().getAttribute(getUploadId(request) + "IS_NATIVE");
    }

    private void afterParse(HttpServletRequest request, FileUpload fileUpload) {
        String id = getUploadId(request);
        request.getSession().removeAttribute(id + "IS_NATIVE");
        request.getSession().removeAttribute(id + "IS_NATIVE");
        FILE_UPLOAD_MAP.remove(id);
        ProgressListener progressListener = fileUpload.getProgressListener();
        if(progressListener instanceof FileUploadListener) {
            ((FileUploadListener)progressListener).complete();
        }

    }

    private static String getUploadId(HttpServletRequest request) {
        String uploadId = request.getParameter("uploadId");
        return Strings.isNullOrEmpty(uploadId)?"DEFAULT_UPLOAD_ID":uploadId;
    }

    private static FileUpload getFileUpload(HttpServletRequest request) {
        return (FileUpload)FILE_UPLOAD_MAP.get(getUploadId(request));
    }

    public static FileUploadListener findFileUploadProgressListener(HttpServletRequest request, FileUploadListener defaultListener) {
        FileUpload fileUpload = getFileUpload(request);
        if(null == fileUpload) {
            return null;
        } else {
            Object progressListener = fileUpload.getProgressListener();
            if(null == progressListener || !(progressListener instanceof FileUploadListener)) {
                fileUpload.setProgressListener(defaultListener);
                progressListener = defaultListener;
            }

            return (FileUploadListener)progressListener;
        }
    }

    public static void register(HttpServletRequest request, FileUploadListener listener) {
        FileUpload fileUpload = getFileUpload(request);
        if(null != fileUpload) {
            fileUpload.setProgressListener(listener);
        }

    }

    public static boolean isNative(HttpServletRequest request) {
        return null != request.getSession().getAttribute(getUploadId(request) + "IS_NATIVE");
    }
}