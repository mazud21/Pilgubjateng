package com.pilgubjateng.battistrada.pilgubjateng;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab1 extends android.support.v4.app.Fragment{

    Button button, button2;
    private Context context;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_calon1, container, false);

        button = (Button)rootView.findViewById(R.id.btnDetailGanjar);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_details_ganjar);
                dialog.setTitle("Biografi");
                dialog.show();
            }
        });

        button2 = (Button)rootView.findViewById(R.id.btnDetailTaj);
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.dialog_details_taj);
                dialog1.setTitle("Biografi");
                dialog1.show();
            }
        });

        return rootView;
    }
}