package com.bohra.covid19tracker.ui.statistics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.bohra.covid19tracker.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class StateCovidDetailActivity extends AppCompatActivity {

    TextView stateTotalConfirmed,stateTotalDeceased,stateRecovered,stateActive;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    String stateCode, state, active, confirmed, deaths, recovered;
    Toolbar stateDetailToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_covid_detail);

        stateTotalConfirmed = findViewById(R.id.stateTotalConfirmed);
        stateTotalDeceased = findViewById(R.id.stateTotalDeceased);
        stateRecovered = findViewById(R.id.stateRecovered);
        stateActive = findViewById(R.id.stateActive);
        simpleArcLoader = findViewById(R.id.simpleArcLoader);
        pieChart = findViewById(R.id.pieChart);
        stateDetailToolbar = findViewById(R.id.stateDetailToolbar);

        Bundle bundle = getIntent().getExtras();
        stateCode = bundle.getString("stateCode");
        state = bundle.getString("state");
        active = bundle.getString("active");
        confirmed = bundle.getString("confirmed");
        deaths = bundle.getString("deaths");
        recovered = bundle.getString("recovered");

        stateDetailToolbar.setTitle(state);

        stateTotalConfirmed.setText(confirmed);
        stateTotalDeceased.setText(deaths);
        stateRecovered.setText(recovered);
        stateActive.setText(active);

        pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(confirmed), Color.parseColor("#ffa726")));
        pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(active), Color.parseColor("#29b6f6")));
        pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovered), Color.parseColor("#66bb6a")));
        pieChart.addPieSlice(new PieModel("Death",Integer.parseInt(deaths), Color.parseColor("#F51403")));
        pieChart.startAnimation();



    }
}