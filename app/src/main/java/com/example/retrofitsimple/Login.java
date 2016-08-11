package com.example.retrofitsimple;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends DialogFragment {
    private static final String TAG = Login.class.getSimpleName();
    private TextView mName;

    public interface DialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String pass);
    }

    public Login(){

    }

    public static Login newInstance(String name, int pos) {
        Login frag = new Login();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("position", pos);
        frag.setArguments(args);
        return frag;
    }

    DialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String name = getArguments().getString("name");

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.fragment_login, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText pass = (EditText) view.findViewById(R.id.login_password);
                        mListener.onDialogPositiveClick(Login.this, pass.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

       // mName = (TextView) view.findViewById(R.id.l_item_txt);
        //mName.setText(name);

        return builder.create();

    }
}
