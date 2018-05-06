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

        String imgPath = person.getImgPath();
        if (imgPath != null && !imgPath.isEmpty()) {
            Picasso.with(mContext).load(person.getImgPath()).into(viewHolder.personImage);
        } else {
            Picasso.with(mContext).load(R.drawable.person_grey).into(viewHolder.personImage);
        }

        String name = person.getFirstName() + " " + person.getLastName();
        viewHolder.name.setText(name);

        String major = person.getMajor();
        if (major != null && !major.isEmpty()) {
            viewHolder.major.setVisibility(View.VISIBLE);
            viewHolder.major.setText(major);
        } else {
            viewHolder.major.setVisibility(View.GONE);
        }

        String un = person.getUserName();
        if (un == null || un.isEmpty()) {
            String email = person.getEmail();
            viewHolder.username.setText("[" + email.substring(0, email.indexOf('@')) + "]");
        } else {
            viewHolder.username.setText("[" + un + "]");
        }

        String classYear = String.valueOf(person.getClassYear());
        if (classYear != null && !classYear.isEmpty()) {
            viewHolder.classYear.setVisibility(View.VISIBLE);
            viewHolder.classYear.setText(classYear);
        } else {
            viewHolder.classYear.setVisibility(View.GONE);
        }
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
        public TextView username;
        public TextView classYear;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.tv_name);
            major = (TextView) itemView.findViewById(R.id.tv_major);
            username = (TextView) itemView.findViewById(R.id.tv_username);
            personImage = (ImageView) itemView.findViewById(R.id.iv_personImage);
            classYear = (TextView) itemView.findViewById(R.id.tv_classYear);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(Person.PERSON_KEY, mPersons.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }
}
