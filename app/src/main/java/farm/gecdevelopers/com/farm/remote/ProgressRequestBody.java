package farm.gecdevelopers.com.farm.remote;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class ProgressRequestBody extends RequestBody {


    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private File file;
    private UploadCallBack listner;


    public ProgressRequestBody(File file, UploadCallBack listner) {
        this.file = file;
        this.listner = listner;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse("image/*");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

        long fileLenght = file.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        FileInputStream in = new FileInputStream(file);
        long uploaded = 0;

        try {
            int read;

            Handler handler = new Handler(Looper.getMainLooper());

            while ((read = in.read(buffer)) != -1) {
                handler.post(new ProgressUpdater(uploaded, fileLenght));
                uploaded += read;
                sink.write(buffer, 0, read);

            }
        } finally {
            in.close();
        }


    }


    @Override
    public long contentLength() throws IOException {
        return file.length();
    }

    private class ProgressUpdater implements Runnable {

        private long uploaded;
        private long fileLength;

        public ProgressUpdater(long uploaded, long fileLenght) {

            this.uploaded = uploaded;
            this.fileLength = fileLenght;

        }

        @Override
        public void run() {

            listner.onProgress((int) (100 * uploaded / fileLength));
        }
    }
}
