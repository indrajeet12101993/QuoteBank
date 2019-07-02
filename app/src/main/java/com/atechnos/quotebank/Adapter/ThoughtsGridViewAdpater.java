package com.atechnos.quotebank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atechnos.quotebank.R;

public class ThoughtsGridViewAdpater extends BaseAdapter {

    private Context mContext;
    private final String[] gridViewQuotes;
    private final String[] gridViewAuthor;

    public ThoughtsGridViewAdpater(Context context, String[] gridViewQuotes, String[] gridViewAuthor) {
        mContext = context;
        this.gridViewQuotes = gridViewQuotes;
        this.gridViewAuthor = gridViewAuthor;
    }

    @Override
    public int getCount() {
        return gridViewQuotes.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.item_listview, null);
            TextView tvDescription = (TextView) gridViewAndroid.findViewById(R.id.tvDescription);
            TextView tvAuthor = (TextView) gridViewAndroid.findViewById(R.id.tvAuthor);

            tvDescription.setText(gridViewQuotes[i]);
            tvAuthor.setText("-"+gridViewAuthor[i]);
        } else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }
}