package com.siddharth.autocompletetextview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<String> {
    private ArrayList<String> arrayList;
    private ArrayList<String> mainArrayList;
    private CustomSearchFilter customSearchFilter;

    public MyCustomAdapter(@NonNull Context context, int resource, ArrayList<String> arrayList) {
        super(context, resource);
        this.arrayList = arrayList;
        mainArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return arrayList.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if (customSearchFilter == null)
            customSearchFilter = new CustomSearchFilter();
        return customSearchFilter;
    }

    class CustomSearchFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            if (charSequence != null && charSequence.length() > 0) {
                ArrayList<String> tempList = new ArrayList<>();
                for (String user : mainArrayList) {
                    if (user.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        tempList.add(user);
                    }
                }
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = arrayList.size();
                filterResults.values = arrayList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }

}
