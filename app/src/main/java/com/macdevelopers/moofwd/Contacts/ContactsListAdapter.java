package com.macdevelopers.moofwd.Contacts;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.macdevelopers.moofwd.R;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.RecyclerViewHolder> {
    private List<ListItem> listItemList;
    private Context ctx;
    private ArrayList<ListItem> items;
    private Intent callIntent;
    private Dialog dialog;
    private FancyButton cancelBTN, callBTN;
    private TextView phoneNoTV;

    public ContactsListAdapter(List<ListItem> listItemList, Context ctx) {
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


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_contacts, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

        try {

            holder.nameTV.setText(listItemList.get(position).getName());
            holder.phoneTV.setText(listItemList.get(position).getPhone());
            holder.emailTV.setText(listItemList.get(position).getEmail());

            holder.phoneHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        String phoneNo = listItemList.get(position).getPhone().toString();

                        showCallAlertDialog(ctx, phoneNo);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.emailHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        String email = listItemList.get(position).getEmail().toString();

                        if (!email.isEmpty()) {

                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                            emailIntent.setData(Uri.parse("mailto:" + email));
                            //emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Query from ");
                            //emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Add Message here");
                            ctx.startActivity(Intent.createChooser(emailIntent, "Moofwd"));

                        } else {

                            Toast.makeText(ctx, ctx.getString(R.string.email_not_available), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCallAlertDialog(Context context, final String phoneNo) {

        try {

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_call_alert);
            dialog.setCancelable(false);

            phoneNoTV = (TextView) dialog.findViewById(R.id.phoneNumber);
            cancelBTN = (FancyButton) dialog.findViewById(R.id.cancelBTN);
            callBTN = (FancyButton) dialog.findViewById(R.id.callBTN);

            phoneNoTV.setText(phoneNo);

            cancelBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });

            callBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!phoneNo.equalsIgnoreCase("")) {

                        callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + phoneNo));
                        ctx.startActivity(callIntent);

                    } else {

                        Toast.makeText(ctx, ctx.getString(R.string.mobile_no_not_available), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dialog.getWindow();
            lp.copyFrom(window.getAttributes());

            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();

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
        TextView nameTV, phoneTV, emailTV;
        LinearLayout phoneHolder, emailHolder;

        public RecyclerViewHolder(View view) {
            super(view);

            nameTV = (TextView) view.findViewById(R.id.nameTV);
            phoneTV = (TextView) view.findViewById(R.id.phoneTV);
            emailTV = (TextView) view.findViewById(R.id.emailTV);
            phoneHolder = (LinearLayout) view.findViewById(R.id.phoneHolder);
            emailHolder = (LinearLayout) view.findViewById(R.id.emailHolder);
        }
    }
}