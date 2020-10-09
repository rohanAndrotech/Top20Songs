package com.example.top20songs.service;

import android.util.Log;

import com.example.top20songs.model.Feed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class ApiClient {
    private static Retrofit retrofit = null;
    private static String TAG = "ApiClient";
    private static ApiClient INSTANCE = null;
    public static String BASE_URL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/";

    public interface ServerResponseListener {
        void onSuccessResponseReceived(Feed response);
        void onErrorResponseReceived();
    }

    /**
     * Api Client instance.
     *
     * @return api instance.
     */
    public static ApiClient getAPIClientInstance() {
        if (INSTANCE == null) {
            return new ApiClient();
        }
        return INSTANCE;
    }

    /**
     * Retrofit Builder implementation.
     *
     * @return retrofit object.
     */
    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Get Artist data.
     *
     */
    public void getTopSongsArtist( ServerResponseListener serverResponseListener) {
        FeedAPI apiService = ApiClient.getClient().create(FeedAPI.class);
        Call<Feed> call = apiService.getFeedList();
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                serverResponseListener.onSuccessResponseReceived(response.body());
            }
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                serverResponseListener.onErrorResponseReceived();
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
