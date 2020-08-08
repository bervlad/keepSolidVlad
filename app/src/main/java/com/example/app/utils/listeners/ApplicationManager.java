package com.example.app.utils.listeners;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.app.api.RestClient;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {

    private Context context;
    public ApplicationManager(Context ctx) {
        this.context = ctx;
    }

    private SharedPreferences getPrefs() {
        return context.getSharedPreferences(Constants.HISTORY_DATA, Context.MODE_PRIVATE);
    }

    public void cacheLoadedItems(Context context, ArrayList<String> items) {
        getPrefs().edit().putString(Constants.STRING_LIST, RestClient.getInstance().getGson().toJson(items)).apply();
    }

    public void updateCachedItems(Context context, String item) {
        ArrayList<String> listStrings = new ArrayList<String>();
        if (getCachedItems(context) != null) {
            listStrings = getCachedItems(context);
        }

        listStrings.add(item);
        cacheLoadedItems(context, listStrings);
    }

    public ArrayList<String> getCachedItems(Context context) {

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        String jsonList = getPrefs().getString(Constants.STRING_LIST, null);
        if(TextUtils.isEmpty(jsonList)) {
            return null;
        }
        return RestClient.getInstance().getGson().fromJson(jsonList, listType);
    }


}
