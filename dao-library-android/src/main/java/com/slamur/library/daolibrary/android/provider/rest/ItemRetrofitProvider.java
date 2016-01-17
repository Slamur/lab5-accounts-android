package com.slamur.library.daolibrary.android.provider.rest;

import android.content.Context;

import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.activity.ItemActivity;
import com.slamur.library.daolibrary.android.provider.ItemActivityProvider;
import com.slamur.library.daolibrary.android.rest.BasicAuthRequestInterceptor;
import com.slamur.library.daolibrary.base.Item;
import com.slamur.library.daolibrary.base.provider.ItemProviderEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemLoadEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemParameterLoadEvent;
import com.slamur.library.daolibrary.base.provider.event.ItemsLoadEvent;
import com.slamur.library.daolibrary.base.provider.rest.ItemGsonProvider;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public abstract class ItemRetrofitProvider<
        ItemType extends Item
        >
        extends ItemGsonProvider<ItemType>
implements ItemActivityProvider<ItemType> {

    protected void showToast(String message) {
        AndroidUtils.showToast(context, message);
    }

    protected abstract class ItemRetrofitProviderCallback <ResponseType> implements Callback<ResponseType> {

        @Override
        public void failure(RetrofitError error) {
            // -<====|
//            showToast(error.getMessage());
        }
    }

    protected class StringCallback extends ItemRetrofitProviderCallback<String> {

        public StringCallback() {
            super();
        }

        @Override
        public void success(String message, Response response) {
            showToast(message);
        }
    }

    protected abstract class ItemEventCallback <ResponseType> extends ItemRetrofitProviderCallback<ResponseType> {

        protected abstract ItemProviderEvent<ItemType, ?> createEvent(ResponseType response);

        @Override
        public void success(ResponseType responseObject, Response response) {
            notifyListeners(
                    createEvent(responseObject)
            );
        }
    }

    protected class ItemCallback extends ItemEventCallback<ItemType> {

        public ItemCallback() {
            super();
        }

        @Override
        protected ItemLoadEvent<ItemType> createEvent(ItemType response) {
            return new ItemLoadEvent<>(ItemRetrofitProvider.this, response);
        }
    }

    protected class ItemsCallback extends ItemEventCallback<List<ItemType>> {

        public ItemsCallback() {
            super();
        }

        @Override
        protected ItemsLoadEvent<ItemType> createEvent(List<ItemType> response) {
            return new ItemsLoadEvent<>(ItemRetrofitProvider.this, response);
        }
    }

    protected class ItemParameterCallback <ParameterType> extends ItemEventCallback<ParameterType> {

        private final String parameterName;

        public ItemParameterCallback(String parameterName) {
            this.parameterName = parameterName;
        }

        @Override
        protected ItemParameterLoadEvent<ItemType> createEvent(Object response) {
            return new ItemParameterLoadEvent<>(ItemRetrofitProvider.this, response, parameterName);
        }
    }

    protected final Context context;

    protected ItemRetrofitProvider(Context context) {
        super();
        this.context = context;
    }

    /**
     * Should be context.getString(R.string.baseURL)
     */
    protected abstract String getApplicationUrlPath();

    protected abstract String getItemUrlPath();

    private RestAdapter createRestAdapter() {
        //create an adapter for retrofit with base url
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getApplicationUrlPath() + getItemUrlPath())
                .setRequestInterceptor(new BasicAuthRequestInterceptor(context))
                .setClient(new OkClient())
                .build();

        return restAdapter;
    }

    protected <RetrofitServiceType> RetrofitServiceType createRestService(Class<RetrofitServiceType> retrofitServiceClass) {
        return createRestAdapter().create(retrofitServiceClass);
    }
}
