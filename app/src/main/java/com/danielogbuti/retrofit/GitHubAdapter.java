package com.danielogbuti.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GitHubAdapter extends BaseAdapter {

    Context context;
    List<GitHubRepo> repos;

    public GitHubAdapter(Context context, List<GitHubRepo> repos) {
        this.context = context;
        this.repos = repos;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return repos.size();
    }

    @Override
    public Object getItem(int position) {
        return repos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.github_list_view, null, true);

            holder.textName = (TextView)convertView.findViewById(R.id.textView);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.textName.setText(repos.get(position).getName());

        return convertView;
    }

    private class ViewHolder {

        protected TextView textName;

    }
}
