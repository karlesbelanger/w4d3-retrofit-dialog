package com.example.retrofitsimple.network;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofitsimple.R;
import com.example.retrofitsimple.entities.Student;

import java.util.ArrayList;

/**
 * Created by evin on 8/10/16.
 */
public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.ViewHolder> {

    ArrayList<Student> mStudents;

    public NamesAdapter(ArrayList<Student> students) {
        mStudents = students;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = mStudents.get(position);

        TextView textView = holder.textViewName;
        textView.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return mStudents == null
                ? 0
                : mStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.l_item_txt);
        }
    }
}
