package ashish1593.space.fapico;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vlk.multimager.utils.Image;

import java.util.ArrayList;

/**
 * Created by ashish on 26/12/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Image> imagesList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, ArrayList<Image> imagesList) {
        this.mInflater = LayoutInflater.from(context);
        this.imagesList = imagesList;
        this.context = context;
    }

    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myImage;

        ViewHolder(View itemView) {
            super(itemView);
            myImage = itemView.findViewById(R.id.image);
        }
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the ImageView in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = imagesList.get(position);
        Glide.with(context)
                .load(image.uri)
                .centerCrop()
                .into(holder.myImage);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    // convenience method for getting data at click position
    Image getItem(int id) {
        return imagesList.get(id);
    }

}