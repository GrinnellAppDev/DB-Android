package edu.grinnell.appdev.grinnelldirectory.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
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
    private Activity parentActivity;

    public SearchResultsAdapter(Activity parent, List<Person> persons) {
        mPersons = persons;
        parentActivity = parent;
    }

    public void updateData(List<Person> persons) {
        mPersons = persons;
        notifyDataSetChanged();
    }

    @NonNull @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parentActivity);
        View personView = inflater.inflate(R.layout.item_searchresult, parent, false);
        return new ViewHolder(personView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultsAdapter.ViewHolder viewHolder, int position) {
        Person person = mPersons.get(position);

        String imgPath = person.getImgPath();
        if (imgPath != null && !imgPath.isEmpty()) {
            Picasso.with(parentActivity).load(person.getImgPath()).into(viewHolder.personImage);
        } else {
            Picasso.with(parentActivity).load(R.drawable.person_grey).into(viewHolder.personImage);
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

        viewHolder.username.setText(person.formattedEmail(parentActivity));

        int classYear = person.getClassYear();
        if (classYear > 0) {
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

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.tv_name);
            major = itemView.findViewById(R.id.tv_major);
            username = itemView.findViewById(R.id.tv_username);
            personImage = itemView.findViewById(R.id.iv_personImage);
            classYear = itemView.findViewById(R.id.tv_classYear);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(parentActivity, DetailActivity.class);
            intent.putExtra(Person.PERSON_KEY, mPersons.get(getAdapterPosition()));
            if (Build.VERSION.SDK_INT < 16) {
                parentActivity.startActivity(intent);
            } else {
                startDetailActivity(intent);
            }
        }

        @TargetApi(16) private void startDetailActivity(Intent detailIntent) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(parentActivity, personImage, "person_image");
            parentActivity.startActivity(detailIntent, options.toBundle());
        }
    }
}
