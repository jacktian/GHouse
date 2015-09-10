package co.lujun.ghouse;

import android.content.Context;

import org.litepal.LitePalApplication;

import co.lujun.ghouse.api.Api;
import co.lujun.ghouse.api.ApiService;
import retrofit.RestAdapter;

/**
 * Created by lujun on 2015/3/1.
 */
public class GlApplication extends LitePalApplication {

    private static Context sContext;

    private static RestAdapter mRestAdapter;
    private static ApiService mApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext(){
        return sContext;
    }

    public static RestAdapter getRestAdapter(){
        if (mRestAdapter == null){
            synchronized (GlApplication.class){
                if (mRestAdapter == null){
                    mRestAdapter = new RestAdapter.Builder()
                            .setEndpoint(Api.API_HOST + Api.API_VERSION)
                            .build();
                }
            }
        }
        return mRestAdapter;
    }

    public static ApiService getApiService(){
        if (mApiService == null){
            synchronized (GlApplication.class){
                if (mApiService == null){
                    mApiService = getRestAdapter().create(ApiService.class);
                }
            }
        }
        return mApiService;
    }
}
