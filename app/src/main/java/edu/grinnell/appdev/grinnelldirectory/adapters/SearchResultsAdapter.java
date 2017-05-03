package edu.grinnell.appdev.grinnelldirectory.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.activities.DetailActivity;
import edu.grinnell.appdev.grinnelldirectory.models.Person;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    private List<Person> mPersons;
    private Context mContext;

    public SearchResultsAdapter(Context context, List<Person> persons) {
        mContext = context;
    }

    public void updateData(List<Person> persons) {
        mPersons = persons;
        notifyDataSetChanged();
    }

    @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View personView = inflater.inflate(R.layout.item_searchresult, parent, false);
        return new ViewHolder(personView);
    }

    @Override
    public void onBindViewHolder(SearchResultsAdapter.ViewHolder viewHolder, int position) {
        Person person = mPersons.get(position);

        ImageView personImageView = viewHolder.personImage;
        TextView nameTextView = viewHolder.name;
        TextView majorTextView = viewHolder.major;
        TextView locationTextView = viewHolder.location;

        String imgPath = person.getImgPath();
        if (imgPath != null && !imgPath.isEmpty()) {
            Picasso.with(mContext).load(person.getImgPath()).into(personImageView);
        } else {
            Picasso.with(mContext).load(R.mipmap.ic_launcher).into(personImageView);
        }
        nameTextView.setText(person.getFirstName() + " " + person.getLastName());
        majorTextView.setText(person.getMajor());
        locationTextView.setText(person.getAddress());
    }

    @Override
    public int getItemCount() {
        return mPersons == null ? 0 : mPersons.size();
    }

    /**
     * ViewHolder class to hold the views from the inflated layout for each list item
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView personImage;
        public TextView name;
        public TextView major;
        public TextView location;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.tv_name);
            major = (TextView) itemView.findViewById(R.id.tv_major);
            location = (TextView) itemView.findViewById(R.id.tv_location);
            personImage = (ImageView) itemView.findViewById(R.id.iv_personImage);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Person.PERSON_KEY, mPersons.get(getAdapterPosition()));
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
    }
}
