package com.fruit.mvvm.ui.plantbase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fruit.mvvm.R;
import com.fruit.mvvm.data.FarmerDummyContent.DummyItem;

import org.w3c.dom.Text;

import java.util.List;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFarmerRecyclerViewAdapter extends RecyclerView.Adapter<MyFarmerRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public MyFarmerRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //for View Holder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_farmer, parent, false);
        return new ViewHolder(view);
    }

    //ViewHolder to contain FarmFragment
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        /*
        holder.mImageView.setImageDrawable(getDrawable(mValues.get(position).imageInt));
        */
        holder.mIdView.setText(mValues.get(position).id);

        holder.mAreaView.setText(mValues.get(position).area);
        holder.mDetailsView.setText(mValues.get(position).details[0]);
        for (int i = 1; i < 4; i++)
            holder.mPlantViews[i-1].setText(mValues.get(position).details[i]);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final ImageView mImageView;
        public final TextView mIdView;
        public final TextView mAreaView;
        public final TextView mDetailsView;
        public final TextView[] mPlantViews;

        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mImageView = view.findViewById(R.id.imageView);
            mIdView = view.findViewById(R.id.item_number);
            mAreaView = view.findViewById(R.id.area);
            mDetailsView = view.findViewById(R.id.details);
            mPlantViews = new TextView[]{
                    view.findViewById(R.id.plant1),
                    view.findViewById(R.id.plant2),
                    view.findViewById(R.id.plant3),
            };

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mIdView.getText() + "'";
        }
    }
}