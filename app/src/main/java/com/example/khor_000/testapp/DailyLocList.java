package com.example.khor_000.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khor_000 on 30/5/2016.
 */
public class DailyLocList extends Activity {

    private ListView listView;
    private ArrayAdapter<LocationItem> arrayAdapter;
    private List<LocationItem> myLocations;

    // method to set object to listview
    private class MyLocAdapter extends ArrayAdapter<LocationItem> {
        public MyLocAdapter() {
            super(DailyLocList.this, R.layout.single_location, myLocations);
        }

        public void add(LocationItem newLoc) {
            super.add(newLoc);
            super.notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //make sure we have view if null
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.single_location, parent, false);
            }

            //find the location to work with
            LocationItem currLoc = myLocations.get(position);

            //fill the view and location name
            TextView makeText = (TextView) itemView.findViewById(R.id.locName);
            makeText.setText(currLoc.getName());

            //latitude
            TextView latText = (TextView) itemView.findViewById(R.id.latTxt);
            Double latVal = currLoc.getLatitude();
            latVal = Double.parseDouble(new DecimalFormat("##.###").format(latVal));
            latText.setText("LAT: " + latVal + "\t");

            //longitude
            TextView lngText = (TextView) itemView.findViewById(R.id.lngTxt);
            Double lngVal = currLoc.getLatitude();
            lngVal = Double.parseDouble(new DecimalFormat("##.###").format(lngVal));
            lngText.setText("LON: " + lngVal);

            return itemView;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_daily_loc_list);
        listView = (ListView) findViewById(R.id.histlist);
        arrayAdapter = new MyLocAdapter();
        myLocations =  new ArrayList<LocationItem>();

     }


}
