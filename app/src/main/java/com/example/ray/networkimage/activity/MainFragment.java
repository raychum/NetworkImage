package com.example.ray.networkimage.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ray.networkimage.R;
import com.example.ray.networkimage.app.NetworkImageApp;
import com.example.ray.networkimage.recyclerview.RecyclerViewAdapter;
import com.example.ray.networkimage.recyclerview.RecyclerViewLoadMoreItem;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Ray on 16/8/15.
 */
public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getSimpleName();
    private final ArrayList<ImageModel.Result> results = new ArrayList<>();
    private int pageCount;
    private int count = 0;
    private boolean isLoading;
    private RecyclerViewAdapter adapter;
    private View rootView;

    public static MainFragment newInstance(int pageCount) {
        final MainFragment mainFragment = new MainFragment();
        mainFragment.pageCount = pageCount;
        return mainFragment;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = (TextView) rootView.findViewById(R.id.page);
        textView.setText("Page " + pageCount);
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adapter = new RecyclerViewAdapter();
        adapter.setLoadingViewItem(new RecyclerViewLoadMoreItem(new RecyclerViewLoadMoreItem.OnLoadingListener() {
            @Override
            public void loadMore() {
                if (!isLoading) {
                    sendRequest();
                }
            }
        }));
        recyclerView.setAdapter(adapter);
        if (results.size() == 0) {
            sendRequest();
        } else {
            for (ImageModel.Result result : results) {
                adapter.add(new ImageItem(result));
            }
        }
        return rootView;
    }

    private void sendRequest() {
        isLoading = true;
        final String tag_json_obj = "image_obj_req";
        final String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=cat" + pageCount + "&rsz=8&imgsz=medium&start=" + count;
        Log.d(TAG, "url=" + url);
        final StringRequest jsonObjReq = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ImageModel imageModel = gson.fromJson(response, ImageModel.class);
                        Log.d(TAG, "imageModel=" + imageModel);
                        if (imageModel != null && imageModel.responseData != null && imageModel.responseData.results != null){
                            results.addAll(imageModel.responseData.results);
                            count += imageModel.responseData.results.size();
                            if (imageModel.responseData.results.size() == 0) {
                                adapter.setShowLoadMore(false);
                            } else {
                                for (ImageModel.Result result : imageModel.responseData.results) {
                                    adapter.add(new ImageItem(result));
                                }
                            }
                        } else {
                            adapter.setShowLoadMore(false);
                        }
                        adapter.notifyDataSetChanged();
                        isLoading = false;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error: " + error.getMessage());
                        adapter.setShowLoadMore(false);
                        adapter.notifyDataSetChanged();
                        isLoading = false;
                    }
                });
        NetworkImageApp.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
