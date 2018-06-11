package helloworld.demo.com.godrive;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import net.gotev.uploadservice.MultipartUploadRequest;

/**
 * Created by shyamramesh on 16/05/18.
 */

public class RequestHandler {

    private static RequestHandler mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private RequestHandler(Context context)
    {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestHandler getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new RequestHandler(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if(mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public<T>void addToRequestQueue(Request<T>req)
    {
        getRequestQueue().add(req);
    }


}
