package edu.grinnell.appdev.grinnelldirectory;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ritikaagarwal on 2/26/17.
 */

public class SearchResultsAdapter extends
        RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {


    @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SearchResultsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView personImage;
        public TextView name;
        public TextView major;
        public TextView location;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tv_name);
            major = (TextView) itemView.findViewById(R.id.tv_major);
            location = (TextView) itemView.findViewById(R.id.tv_location);
            personImage = (ImageView) itemView.findViewById(R.id.iv_personImage);
        }
    }


}
