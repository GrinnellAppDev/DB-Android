package edu.grinnell.appdev.grinnelldirectory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.Model.Person;

/**
 * Created by ritikaagarwal on 2/26/17.
 */

public class SearchResultsAdapter extends
        RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    // Store a member variable for the Persons list
    private List<Person> mPersons;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public SearchResultsAdapter(Context context, List<Person> persons) {
        mPersons = persons;
        mContext = context;
    }
    private Context getContext() {
        return mContext;
    }

    @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View personView = inflater.inflate(R.layout.item_searchresult, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(personView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchResultsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Person person = mPersons.get(position);

        // Set item views based on your views and data model
        ImageView imageView = viewHolder.personImage;
        Picasso.with(mContext).load(person.getImgPath()).into(imageView);
        TextView textView1 = viewHolder.name;
        textView1.setText(person.getFirstName() + " " + person.getLastName());
        TextView textView2 = viewHolder.major;
        textView2.setText(person.getMajor());
        TextView textView3 = viewHolder.location;
        textView3.setText(person.getAddress());
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
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
