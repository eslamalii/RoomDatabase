package com.example.contactmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanager.Model.Contact;
import com.example.contactmanager.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private OnContactClickListener onContactClickListener;
    private final List<Contact> data;
    private final Context context;

    public RecyclerViewAdapter(List<Contact> data, Context context, OnContactClickListener onContactClickListener) {
        this.data = data;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view, onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = data.get(position);

        holder.name.setText(contact.getName());
        holder.email.setText(contact.getEmail());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnContactClickListener onContactClickListener;
        public TextView name;
        public TextView email;

        public ViewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener) {
            super(itemView);

            name = itemView.findViewById(R.id.nameTextView);
            email = itemView.findViewById(R.id.emailTextView);
            this.onContactClickListener = onContactClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactClickListener{
        void onContactClick(int position);
    }
}
