package com.pilgubjateng.battistrada.pilgubjateng;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab2 extends android.support.v4.app.Fragment{

    Button button, button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_calon2, container, false);

        button = (Button)rootView.findViewById(R.id.btnDetailSudirman);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_details_sudirman);
                dialog.setTitle("Biografi");
                dialog.show();
            }
        });

        button2 = (Button)rootView.findViewById(R.id.btnDetailIda);
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.dialog_details_ida);
                dialog1.setTitle("Biografi");
                dialog1.show();
            }
        });

        return rootView;
    }
}