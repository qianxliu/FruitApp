package com.fruit.mvvm.ui.plantbase;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fruit.mvvm.R;
import com.fruit.mvvm.data.FarmerDummyContent;
import com.fruit.mvvm.ui.minefruit.MineFruitActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A fragment representing a list of Items.
 */

public class PlantBaseFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private final String[] ListImage = {
            "https://gitee.com/Neterace/fruit-app-sync/raw/master/mipmap-hdpi/v_view.jpg",
            "https://gitee.com/Neterace/fruit-app-sync/raw/master/mipmap-hdpi/v_green_land.jpg",
            "https://gitee.com/Neterace/fruit-app-sync/raw/master/mipmap-hdpi/v_cab.jpg",
            "https://gitee.com/Neterace/fruit-app-sync/raw/master/mipmap-hdpi/v_land_1.jpg",
            "https://gitee.com/Neterace/fruit-app-sync/raw/master/mipmap-hdpi/v_land_2.jpg",
    };

    OkHttpClient client = new OkHttpClient();

    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlantBaseFragment() {

    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PlantBaseFragment newInstance(int columnCount) {
        PlantBaseFragment fragment = new PlantBaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    ImageView getImageView(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ImageView imageView = new ImageView(requireActivity());
            Bitmap bitmap = BitmapFactory.decodeStream(Objects.requireNonNull(response.body()).byteStream());
            imageView.post(new Runnable() {
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
            return imageView;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plant_base_fragment, container, false);

        GestureDetector gestureDetector;
        ViewFlipper viewFlipper = view.findViewById(R.id.view_flipper);

        // 线程安全
        // addView等操作全部在UIThread中执行
        for (String uri : ListImage) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ImageView imageView = getImageView(uri);
                        viewFlipper.post(new Runnable() {
                            public void run() {
                                viewFlipper.addView(imageView);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() > e2.getX()) {
                    viewFlipper.showNext();
                } else if (e1.getX() < e2.getX()) {
                    viewFlipper.showPrevious();
                } else {
                    return false;
                }
                return true;
            }
        });

        viewFlipper.setOnTouchListener((v, event) -> !gestureDetector.onTouchEvent(event));

        View plant_list = view.findViewById(R.id.list);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MineFruitActivity.class);
                startActivity(intent);
            }
        });


        // Set the adapter
        if (plant_list instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) plant_list;


            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount, VERTICAL));
            recyclerView.setAdapter(new MyFarmerRecyclerViewAdapter(FarmerDummyContent.ITEMS));
        }

        return view;
    }

}
