package com.example.rainbow;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
//Dialog Fragment if output does not make sense


public class AndroidDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Error Found").setMessage("Error generated.Try again later").
                setPositiveButton("Ok",null);

        return builder.create();
    }
}
