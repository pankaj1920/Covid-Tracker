package com.bohra.covid19tracker.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bohra.covid19tracker.R;
import com.bohra.covid19tracker.data.CovidApi;
import com.bohra.covid19tracker.data.CovidBaseClient;
import com.bohra.covid19tracker.data.covid.CovidResponse;
import com.bohra.covid19tracker.ui.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    Button callNowBtn,sendSmsBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callNowBtn = view.findViewById(R.id.callNowBtn);
        sendSmsBtn = view.findViewById(R.id.sendSmsBtn);

        callNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:" + "108"));
//                startActivity(intent);
                makePhoneCall();
            }
        });

        sendSmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + "108"));

                //      intent.putExtra("sms_body", "remember to buy bread and milk");
                startActivity(intent);
            }
        });

    }

    public void makePhoneCall() {

        //if there is already number we have to call and if the is no number we have to ask permmision

            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                //ask for permission
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);

            } else {
                String dial = "tel:" + "108";
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(getActivity(), permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 1) {
                makePhoneCall();
            }


        } else {
            Toast.makeText(getActivity(), "need Permission", Toast.LENGTH_SHORT).show();
        }
    }


}