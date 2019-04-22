package com.macdevelopers.moofwd.Subjects;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.macdevelopers.moofwd.Dashboard.DashboardActivity;
import com.macdevelopers.moofwd.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectsListAdapter extends RecyclerView.Adapter<SubjectsListAdapter.RecyclerViewHolder> {
    private List<ListItem> listItemList;
    private Context ctx;
    private ArrayList<ListItem> items;
    private String period = "";

    public SubjectsListAdapter(List<ListItem> listItemList, Context ctx) {
        this.listItemList = new ArrayList<>(listItemList);
        this.ctx = ctx;
    }

    public ArrayList<ListItem> getDataSet() {
        return items;
    }

    public void setDataSet(ArrayList<ListItem> newDataSet) {
        this.items = newDataSet;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_subjects, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

        try {

            holder.nameTV.setText(listItemList.get(position).getPeriod());
            holder.subjectNameTV.setText(listItemList.get(position).getSubjectName());
            holder.subjectCodeTV.setText(listItemList.get(position).getSubjectCode());
            holder.typeTV.setText(listItemList.get(position).getType());

            if (period.equalsIgnoreCase(listItemList.get(position).getPeriod())) {

                holder.periodHolder.setVisibility(View.GONE);

            } else {

                holder.periodHolder.setVisibility(View.VISIBLE);
            }

            period = listItemList.get(position).getPeriod();

            holder.nextHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        ListItem listItem = listItemList.get(position);
                        Intent intent = new Intent(ctx, SubjectDetailActivity.class);
                        intent.putExtra("subjectName", listItem.getSubjectName());
                        intent.putExtra("subjectCode", listItem.getSubjectCode());
                        intent.putExtra("period", listItem.getPeriod());
                        intent.putExtra("lectureName", listItem.getLectureName());
                        intent.putExtra("lectureEmail", listItem.getLectureEmail());
                        intent.putExtra("subjectOverview", listItem.getSubjectOverview());
                        ctx.startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        //return noOfValue;
        return listItemList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, subjectNameTV, subjectCodeTV, typeTV;
        LinearLayout periodHolder;
        RelativeLayout nextHolder;

        public RecyclerViewHolder(View view) {
            super(view);

            nameTV = (TextView) view.findViewById(R.id.nameTV);
            subjectNameTV = (TextView) view.findViewById(R.id.subjectNameTV);
            subjectCodeTV = (TextView) view.findViewById(R.id.subjectCodeTV);
            typeTV = (TextView) view.findViewById(R.id.typeTV);
            nextHolder = (RelativeLayout) view.findViewById(R.id.nextHolder);
            periodHolder = (LinearLayout) view.findViewById(R.id.periodHolder);
        }
    }
}