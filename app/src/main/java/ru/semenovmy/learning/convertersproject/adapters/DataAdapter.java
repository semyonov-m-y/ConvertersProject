package ru.semenovmy.learning.convertersproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.semenovmy.learning.convertersproject.activities.LengthConverterActivity;
import ru.semenovmy.learning.convertersproject.activities.WeightConverterActivity;
import ru.semenovmy.learning.convertersproject.model.Measure;
import ru.semenovmy.learning.convertersproject.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements View.OnClickListener {

    private Resources mResources;
    private LayoutInflater inflater;
    private List<Measure> mMeasures;
    private List<Object> mAdapterItems;
    private Context context;

    public DataAdapter(Resources resources) {
        this.mResources = resources;
    }

    public DataAdapter(Context context, List<Measure> mMeasures) {
        this.mMeasures = mMeasures;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        context = inflater.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Measure measure = mMeasures.get(position);
        holder.nameView.setText(measure.getName());

        holder.imageView.getLayoutParams().width = 200;
        holder.imageView.getLayoutParams().height = 200;

        if (position == 0)
            holder.imageView.setImageResource(R.drawable.ic_length);
        if (position == 1)
            holder.imageView.setImageResource(R.drawable.ic_weight);

        holder.itemView.setOnClickListener((view) -> {
            if (position == 0) {
                Intent intent = new Intent(context, LengthConverterActivity.class);
                context.startActivity(intent);
            }
            if (position == 1) {
                Intent intent = new Intent(context, WeightConverterActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeasures.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView nameView;
        final ImageView imageView;

        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.name);
            imageView = view.findViewById(R.id.picture);
        }
    }

    public void setMeasures(List<Measure> measures) {
        if (measures == null) {
            mMeasures = new ArrayList<>();
            mAdapterItems = new ArrayList<>();
        } else {
            mMeasures = new ArrayList<>(measures);
            mAdapterItems = new ArrayList<>(measures);
        }
        notifyDataSetChanged();
    }
}
