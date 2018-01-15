package ru.kizup.wotblitzhelper.utils.network;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;

/**
 * Created by: dpuzikov on 12.01.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class MockInterceptor implements Interceptor {

    private static final String TAG = MockInterceptor.class.getSimpleName();
    private static final String FILE_EXTENSION = ".json";
    private Context mContext;
    private boolean isSuccess;

    private String mContentType = "application/json";

    public MockInterceptor(Context context) {
        mContext = context;
    }

    /**
     * Set content type for header
     * @param contentType Content type
     * @return FakeInterceptor
     */
    public MockInterceptor setContentType(String contentType) {
        mContentType = contentType;
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        List<String> listSuggestionFileName = new ArrayList<>();
        String method = chain.request().method().toLowerCase();

        Response response = null;
        Response.Builder builder = new Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .addHeader("content-type", mContentType);
        // Get Request URI.
        final URI uri = chain.request().url().uri();
        Log.d(TAG, "--> Request url: [" + method.toUpperCase() + "]" + uri.toString());

        String defaultFileName = getFileName(chain);

        //create file name with http method
        //eg: getLogin.json
        listSuggestionFileName.add(method + upCaseFirstLetter(defaultFileName));

        //eg: login.json
        listSuggestionFileName.add(defaultFileName);

        String responseFileName = getFirstFileNameExist(listSuggestionFileName, uri);

//        isSuccess = new Random().nextBoolean();

//        if (isSuccess) {
            if (responseFileName != null) {
                String fileName = getFilePath(uri, responseFileName);
                Log.d(TAG, "Read data from file: " + fileName);
                try {
                    InputStream is = mContext.getAssets().open(fileName);
                    BufferedReader r = new BufferedReader(new InputStreamReader(is));
                    StringBuilder responseStringBuilder = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        responseStringBuilder.append(line).append('\n');
                    }
                    Log.d(TAG, "Response: " + responseStringBuilder.toString());
                    response = builder
                            .code(200)
                            .message(responseStringBuilder.toString())
                            .body(ResponseBody.create(MediaType.parse(mContentType), responseStringBuilder.toString().getBytes()))
                            .build();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            } else {
                for (String file : listSuggestionFileName) {
                    Log.e(TAG, "File not exist: " + getFilePath(uri, file));
                }
                response = chain.proceed(chain.request());
            }
//        } else {
//            response = builder.code(400)
//                    .message("")
//                    .build();
//        }

        Log.d(TAG, "<-- END [" + method.toUpperCase() + "]" + uri.toString());
        return response;
    }

    private String upCaseFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String getFirstFileNameExist(List<String> inputFileNames, URI uri) throws IOException {
        String mockDataPath = uri.getHost() + uri.getPath();
        mockDataPath = mockDataPath.substring(0, mockDataPath.lastIndexOf('/'));
        Log.d(TAG, "Scan files in: " + mockDataPath);
        //List all files in folder
        String[] files = mContext.getAssets().list(mockDataPath);
        for (String fileName : inputFileNames) {
            if (fileName != null) {
                for (String file : files) {
                    if (fileName.equals(file)) {
                        return fileName;
                    }
                }
            }
        }
        return null;
    }

    private String getFileName(Chain chain) {
        String fileName = chain.request().url().pathSegments().get(chain.request().url().pathSegments().size() - 1);
        return fileName.isEmpty() ? "index" + FILE_EXTENSION : fileName + FILE_EXTENSION;
    }

    private String getFilePath(URI uri, String fileName) {
        String path;
        if (uri.getPath().lastIndexOf('/') != uri.getPath().length() - 1) {
            path = uri.getPath().substring(0, uri.getPath().lastIndexOf('/') + 1);
        } else {
            path = uri.getPath();
        }
        return uri.getHost() + path + fileName;
    }

}
