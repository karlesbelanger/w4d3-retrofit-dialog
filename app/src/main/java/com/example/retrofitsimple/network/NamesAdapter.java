package com.example.retrofitsimple.network;

//import android.app.AlertDialog;
//import android.content.Context;
//import android.support.v4.app.DialogFragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.retrofitsimple.Login;
//import com.example.retrofitsimple.MainActivity;
//import com.example.retrofitsimple.R;
//import com.example.retrofitsimple.entities.Student;

//import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.retrofitsimple.MainActivity;
import com.example.retrofitsimple.Login;
import com.example.retrofitsimple.R;
import com.example.retrofitsimple.entities.Student;

import java.util.ArrayList;

/**
 * Created by evin on 8/10/16.
 */
public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.ViewHolder> {

    private static final String TAG = "NamesAdapterTAG_";

    private ArrayList<Student> mStudents;
    private Context mContext;
    private ArrayList<String> mNames;

    public NamesAdapter(ArrayList<Student> students, Context context) {
        mStudents = students;
        mContext = context;
        mNames = new ArrayList<String>();
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
        mNames.add(student.getName());

        TextView textViewAge = holder.textViewAge;
        textViewAge.setText(student.getAge() + "");

        TextView textViewGrade = holder.textViewGrade;
        textViewGrade.setText(student.getGrade() + "");

        ImageView imageView = holder.imageViewName;
        Glide.with(mContext)
//                .load("https://lh4.ggpht.com/wKrDLLmmxjfRG2-E-k5L5BUuHWpCOe4lWRF7oVs1Gzdn5e5yvr8fj-ORTlBF43U47yI=w300")
                .load(student.getImageName())
                .into(imageView);


       // Log.d(TAG, "onBindViewHolder: " + student.getImageName());
    }

    @Override
    public int getItemCount() {
        return mStudents == null
                ? 0
                : mStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewAge;
        private TextView textViewGrade;
        private Button btnLogin;
        private ImageView imageViewName;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.l_item_txt);
            textViewAge = (TextView) itemView.findViewById(R.id.l_item_age);
            textViewGrade = (TextView) itemView.findViewById(R.id.l_item_grade);
            imageViewName = (ImageView) itemView.findViewById(R.id.l_item_img);
            btnLogin = (Button) itemView.findViewById(R.id.l_item_login);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = mNames.indexOf(textViewName.getText().toString());
                    //Toast.makeText(mContext, textViewName.getText().toString() + " " + student.getPassword(), Toast.LENGTH_SHORT).show();
                    DialogFragment newFragment = new Login().newInstance(textViewName.getText().toString(), pos);
                    newFragment.show(((FragmentActivity) mContext).getSupportFragmentManager(), "Sign in");
                }
            });
        }
    }
}
