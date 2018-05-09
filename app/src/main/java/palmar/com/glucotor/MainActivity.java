package palmar.com.glucotor;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import palmar.com.glucotor.vo.PersonDateInsulinDosage;
import palmar.com.glucotor.vo.PersonDateRecord;
import palmar.com.glucotor.vo.InsulinDosage;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    TableLayout stk;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    Button button5;
    Button button6;
    Button button7;
    Button button8;

    List<PersonDateRecord> periodLog;
    //TextView statusView;
    String dosageCode;
    String dosageValue;
    Double glucoseReading;

    private ProgressBar spinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

      //  Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
      //  setSupportActionBar(myToolbar);

        //spinner = (ProgressBar)findViewById(R.id.progressBar);
        //spinner.setVisibility(View.GONE);

        init();
    }

    public void init() {

        TableRow tbrow = null;
        TextView t1v = null;

        stk = (TableLayout) findViewById(R.id.table_main);

        // Add header
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText("  ");
        tv0.setTextColor(Color.WHITE);
        tv0.setHeight(50);
        tbrow0.addView(tv0);

        tv0 = new TextView(this);
        tv0.setText(" Date ");
        tv0.setTextColor(Color.WHITE);
        tv0.setHeight(50);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Before Breakfast ");
        tv1.setTextColor(Color.WHITE);

        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Insulin ");
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Before Lunch ");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" Insulin ");
        tv4.setTextColor(Color.WHITE);
        tbrow0.addView(tv4);

        TextView tv5 = new TextView(this);
        tv5.setText(" Before Dinner ");
        tv5.setTextColor(Color.WHITE);
        tbrow0.addView(tv5);
        TextView tv6 = new TextView(this);
        tv6.setText(" Insulin ");
        tv6.setTextColor(Color.WHITE);
        tbrow0.addView(tv6);

        TextView tv7 = new TextView(this);
        tv7.setText(" Before Dinner ");
        tv7.setTextColor(Color.WHITE);
        tbrow0.addView(tv7);
        TextView tv8 = new TextView(this);
        tv8.setText(" Insulin ");
        tv8.setTextColor(Color.WHITE);
        tbrow0.addView(tv8);

        tbrow0.setBackgroundColor(Color.BLUE);
        stk.addView(tbrow0);



        // Add all other rows

/*
        tbrow = new TableRow(this);

        statusView = new TextView(this);

            statusView.setText("Step 1 ........................................................... xxx");


        statusView.setTextColor(Color.WHITE);
        statusView.setGravity(Gravity.CENTER);
        statusView.setWidth(500);

        tbrow.addView(statusView);


        stk.addView(tbrow);
        //
        */

        new RetrieveLogTask().execute();


    }

    public void drawTodayRecord(TableLayout stk) {
        // Add today's record
        TableRow tbrow = null;
        TextView t1v = null;
        PersonDateRecord todayRecord = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        if (periodLog != null && periodLog.size() > 0) {
            todayRecord = periodLog.get(0);
            if (todayRecord == null || todayRecord.getRecordDate() == null || !sdf.format(new Date()).equals(sdf.format(todayRecord.getRecordDate()))) {
                todayRecord = null;
            }
        }


        tbrow = new TableRow(this);

        t1v = new TextView(this);
        sdf = new SimpleDateFormat("EEE");
        t1v.setText(sdf.format(new Date()));
        t1v.setTextColor(Color.WHITE);
        t1v.setGravity(Gravity.CENTER);
        tbrow.addView(t1v);

        t1v = new TextView(this);
        sdf = new SimpleDateFormat("yyyy.MM.dd");
        t1v.setText(sdf.format(new Date()));
        t1v.setTextColor(Color.WHITE);
        t1v.setGravity(Gravity.CENTER);
        tbrow.addView(t1v);



        button5 = new Button(this);
        if (todayRecord == null || todayRecord.getBeforeBreakfastInsulin() == null || todayRecord.getBeforeBreakfastInsulin().getGlucoseReading() == 0) {
            button5.setText("");
        } else {
            button5.setText(Double.toString(todayRecord.getBeforeBreakfastInsulin().getGlucoseReading()));
        }
        button5.setTextSize(11.0f);
        button5.setHeight(8);
        button5.setWidth(40);

        button5.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View arg0) {
                                           final Dialog dialog = new Dialog(context);

                                           dialog.setContentView(R.layout.activity_pop_up_glucose);
                                           dialog.setTitle("Glucose reading");

                                           Button applyButton = (Button) dialog.findViewById(R.id.applyGlucoseBtn);

                                           applyButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   TextView reading = dialog.findViewById(R.id.glucoseReading);
                                                   button5.setText(reading.getText());
                                                   dosageCode = "BK";
                                                   glucoseReading = Double.parseDouble(reading.getText().toString());
                                                   new SaveGlucoseTask().execute();
                                                   dialog.dismiss();
                                               }
                                           });

                                           Button closeButton = (Button) dialog.findViewById(R.id.cancelGlucoseBtn);

                                           closeButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dialog.dismiss();
                                               }
                                           });

                                           dialog.show();
                                       }
                                   }
        );
        tbrow.addView(button5);

        button1 = new Button(this);
        if (todayRecord == null || todayRecord.getBeforeBreakfastInsulin() == null) {
            button1.setText("");
        } else {
            button1.setText(getFormattedInsulinDosage(todayRecord.getBeforeBreakfastInsulin()));
        }
        button1.setTextSize(11.0f);
        button1.setHeight(8);
        button1.setWidth(80);

        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View arg0) {
                                           final Dialog dialog = new Dialog(context);

                                           dialog.setContentView(R.layout.activity_pop_up);
                                           dialog.setTitle("Insulin");

                                           Button applyButton = (Button) dialog.findViewById(R.id.applyBtn);

                                           applyButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dosageValue = getDosage(dialog,periodLog,1, new Date());
                                                   dosageCode = "BK";
                                                   button1.setText(dosageValue);
                                                   new SaveInsulinTask().execute();
                                                   dialog.dismiss();
                                               }
                                           });

                                           Button closeButton = (Button) dialog.findViewById(R.id.closBtn);

                                           closeButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dialog.dismiss();
                                               }
                                           });

                                           dialog.show();
                                       }
                                   }
        );
        tbrow.addView(button1);

        button6 = new Button(this);
        if (todayRecord == null || todayRecord.getBeforeLunchInsulin() == null  || todayRecord.getBeforeLunchInsulin().getGlucoseReading() == 0) {
            button6.setText("");
        } else {
            button6.setText(Double.toString(todayRecord.getBeforeLunchInsulin().getGlucoseReading()));
        }
        button6.setTextSize(11.0f);
        button6.setHeight(8);
        button6.setWidth(40);

        button6.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View arg0) {
                                           final Dialog dialog = new Dialog(context);

                                           dialog.setContentView(R.layout.activity_pop_up_glucose);
                                           dialog.setTitle("Glucose reading");

                                           Button applyButton = (Button) dialog.findViewById(R.id.applyGlucoseBtn);

                                           applyButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   TextView reading = dialog.findViewById(R.id.glucoseReading);
                                                   button6.setText(reading.getText());
                                                   dosageCode = "LN";
                                                   glucoseReading = Double.parseDouble(reading.getText().toString());
                                                   new SaveGlucoseTask().execute();
                                                   dialog.dismiss();
                                               }
                                           });

                                           Button closeButton = (Button) dialog.findViewById(R.id.cancelGlucoseBtn);

                                           closeButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dialog.dismiss();
                                               }
                                           });

                                           dialog.show();
                                       }
                                   }
        );
        tbrow.addView(button6);

        button2 = new Button(this);
        if (todayRecord == null || todayRecord.getBeforeLunchInsulin() == null) {
            button2.setText("");
        } else {
            button2.setText(getFormattedInsulinDosage(todayRecord.getBeforeLunchInsulin()));
        }
        button2.setTextSize(11.0f);
        button2.setHeight(8);
        button2.setWidth(80);
        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View arg0) {
                                           final Dialog dialog = new Dialog(context);
                                           dialog.setContentView(R.layout.activity_pop_up);
                                           dialog.setTitle("Insulin");

                                           Button applyButton = (Button) dialog.findViewById(R.id.applyBtn);
                                           applyButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dosageValue = getDosage(dialog,periodLog,2, new Date());
                                                   dosageCode = "LN";
                                                   button2.setText(dosageValue);
                                                   new SaveInsulinTask().execute();
                                                   dialog.dismiss();
                                               }
                                           });

                                           Button closeButton = (Button) dialog.findViewById(R.id.closBtn);
                                           closeButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dialog.dismiss();
                                               }
                                           });

                                           dialog.show();
                                       }
                                   }
        );
        tbrow.addView(button2);

        button7 = new Button(this);
        if (todayRecord == null || todayRecord.getBeforeDinnerInsulin() == null || todayRecord.getBeforeDinnerInsulin().getGlucoseReading() == 0) {
            button7.setText("");
        } else {
            button7.setText(Double.toString(todayRecord.getBeforeDinnerInsulin().getGlucoseReading()));
        }
        button7.setTextSize(11.0f);
        button7.setHeight(8);
        button7.setWidth(40);

        button7.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View arg0) {
                                           final Dialog dialog = new Dialog(context);

                                           dialog.setContentView(R.layout.activity_pop_up_glucose);
                                           dialog.setTitle("Glucose reading");

                                           Button applyButton = (Button) dialog.findViewById(R.id.applyGlucoseBtn);

                                           applyButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   TextView reading = dialog.findViewById(R.id.glucoseReading);
                                                   button7.setText(reading.getText());
                                                   dosageCode = "DN";
                                                   glucoseReading = Double.parseDouble(reading.getText().toString());
                                                   new SaveGlucoseTask().execute();
                                                   dialog.dismiss();
                                               }
                                           });

                                           Button closeButton = (Button) dialog.findViewById(R.id.cancelGlucoseBtn);

                                           closeButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dialog.dismiss();
                                               }
                                           });

                                           dialog.show();
                                       }
                                   }
        );
        tbrow.addView(button7);

        button3 = new Button(this);
        if (todayRecord == null || todayRecord.getBeforeDinnerInsulin() == null) {
            button3.setText("");
        } else {
            button3.setText(getFormattedInsulinDosage(todayRecord.getBeforeDinnerInsulin()));
        }
        button3.setTextSize(11.0f);
        button3.setHeight(8);
        button3.setWidth(80);
        button3.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View arg0) {
                                           final Dialog dialog = new Dialog(context);
                                           dialog.setContentView(R.layout.activity_pop_up);
                                           dialog.setTitle("Insulin");

                                           Button applyButton = (Button) dialog.findViewById(R.id.applyBtn);
                                           applyButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dosageValue = getDosage(dialog,periodLog,3, new Date());
                                                   dosageCode = "DN";
                                                   button3.setText(dosageValue);
                                                   new SaveInsulinTask().execute();
                                                   dialog.dismiss();
                                               }
                                           });

                                           Button closeButton = (Button) dialog.findViewById(R.id.closBtn);
                                           closeButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dialog.dismiss();
                                               }
                                           });

                                           dialog.show();
                                       }
                                   }
        );
        tbrow.addView(button3);

        button8 = new Button(this);
        if (todayRecord == null || todayRecord.getBeforeBedInsulin() == null  || todayRecord.getBeforeBedInsulin().getGlucoseReading() == 0) {
            button8.setText("");
        } else {
            button8.setText(Double.toString(todayRecord.getBeforeBedInsulin().getGlucoseReading()));
        }
        button8.setTextSize(11.0f);
        button8.setHeight(8);
        button8.setWidth(40);

        button8.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View arg0) {
                                           final Dialog dialog = new Dialog(context);

                                           dialog.setContentView(R.layout.activity_pop_up_glucose);
                                           dialog.setTitle("Glucose reading");

                                           Button applyButton = (Button) dialog.findViewById(R.id.applyGlucoseBtn);

                                           applyButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   TextView reading = dialog.findViewById(R.id.glucoseReading);
                                                   button8.setText(reading.getText());
                                                   dosageCode = "BE";
                                                   glucoseReading = Double.parseDouble(reading.getText().toString());
                                                   new SaveGlucoseTask().execute();
                                                   dialog.dismiss();
                                               }
                                           });

                                           Button closeButton = (Button) dialog.findViewById(R.id.cancelGlucoseBtn);

                                           closeButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dialog.dismiss();
                                               }
                                           });

                                           dialog.show();
                                       }
                                   }
        );
        tbrow.addView(button8);


        button4 = new Button(this);
        if (todayRecord == null || todayRecord.getBeforeBedInsulin() == null) {
            button4.setText("");
        } else {
            button4.setText(getFormattedInsulinDosage(todayRecord.getBeforeBedInsulin()));
        }
        button4.setTextSize(11.0f);
        button4.setHeight(8);
        button4.setWidth(80);
        button4.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View arg0) {
                                           final Dialog dialog = new Dialog(context);
                                           dialog.setContentView(R.layout.activity_pop_up);
                                           dialog.setTitle("Insulin");

                                           Button applyButton = (Button) dialog.findViewById(R.id.applyBtn);
                                           applyButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dosageValue = getDosage(dialog,periodLog,4, new Date());
                                                   dosageCode = "BE";
                                                   button4.setText(dosageValue);
                                                   new SaveInsulinTask().execute();
                                                   dialog.dismiss();
                                               }
                                           });

                                           Button closeButton = (Button) dialog.findViewById(R.id.closBtn);
                                           closeButton.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   dialog.dismiss();
                                               }
                                           });

                                           dialog.show();
                                       }
                                   }
        );
        tbrow.addView(button4);

        tbrow.setBackgroundColor(Color.parseColor("#4e342e"));

        stk.addView(tbrow);
    }

    public void drawLog(TableLayout stk ) {

        TableRow tbrow = null;
        TextView t1v = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

        if (periodLog != null && !periodLog.isEmpty()) {
            String color1 = "#455a64";
            String color2 = "#bdbdbd";
            String rowColor = color1;
            for(PersonDateRecord dayRecord : periodLog) {
                if (dayRecord != null) {
                    if (sdf.format(new Date()).equals(sdf.format(dayRecord.getRecordDate()))) {
                        continue;
                    }
                    tbrow = new TableRow(this);

                    tbrow.setBackgroundColor(Color.parseColor(rowColor));
                    rowColor = rowColor.equals(color1) ? color2 : color1;
                    tbrow.setMinimumHeight(35);

                    addDateLogCell(tbrow,dayRecord.getRecordDate(), true);
                    addDateLogCell(tbrow,dayRecord.getRecordDate(), false);

                    addGlucoseLogCell(tbrow,dayRecord.getBeforeBreakfastInsulin(), 4.0, 10.0);
                    addInsulinLogCell(tbrow, dayRecord.getBeforeBreakfastInsulin());

                    addGlucoseLogCell(tbrow,dayRecord.getBeforeLunchInsulin(), 4.0, 10.0);
                    addInsulinLogCell(tbrow, dayRecord.getBeforeLunchInsulin());

                    addGlucoseLogCell(tbrow,dayRecord.getBeforeDinnerInsulin(), 4.0, 10.0);
                    addInsulinLogCell(tbrow, dayRecord.getBeforeDinnerInsulin());

                    addGlucoseLogCell(tbrow,dayRecord.getBeforeBedInsulin(), 4.0, 10.0);
                    addInsulinLogCell(tbrow, dayRecord.getBeforeBedInsulin());

                    stk.addView(tbrow);
                }
            }
        }

    }

    private void addDateLogCell(TableRow tbrow, Date recordDate, boolean dayName) {
        TextView t1v = new TextView(this);

        SimpleDateFormat sdf = dayName ? new SimpleDateFormat("EEE") : new SimpleDateFormat("yyyy.MM.dd");
        if (recordDate != null) {
            t1v.setText(sdf.format(recordDate));
        }
        t1v.setTextColor(Color.WHITE);
        t1v.setGravity(Gravity.CENTER);
        tbrow.addView(t1v);
    }

    private void addInsulinLogCell(TableRow tbrow, PersonDateInsulinDosage dosage) {
        TextView t1v = new TextView(this);
        t1v.setText(getFormattedInsulinDosage(dosage));
        t1v.setTextColor(Color.WHITE);
        t1v.setGravity(Gravity.CENTER);
        tbrow.addView(t1v);
    }

    private void addGlucoseLogCell(TableRow tbrow, PersonDateInsulinDosage dosage, double minRange, double maxRange) {
        TextView t1v = new TextView(this);
        t1v.setTextColor(Color.WHITE);
        t1v.setGravity(Gravity.CENTER);
        if (dosage == null) {
            t1v.setText("");
        } else {
            t1v.setText(Double.toString(dosage.getGlucoseReading()));
            if (dosage.getGlucoseReading() > 0 && dosage.getGlucoseReading() < minRange) {
                t1v.setBackgroundColor(Color.parseColor("#ff867c"));
            } else if (dosage.getGlucoseReading() > maxRange) {
                t1v.setBackgroundColor(Color.parseColor("#c4001d"));
            }
        }
        tbrow.addView(t1v);
    }

    private String getFormattedInsulinDosage(PersonDateInsulinDosage dosage) {
        if (dosage == null) {
            return "";
        }
        return dosage.getFormattedInsulinDosage();
    }

    private String getInsulinDosageForDate(List<PersonDateRecord> periodLog, int dosageIndex, Date date) {
        if (periodLog == null || periodLog.isEmpty()) {
            return "";
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        Calendar c2 = Calendar.getInstance();
        for (PersonDateRecord r : periodLog) {
            c2.setTime(r.getRecordDate());
            if (c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
                    c1.get(Calendar.DATE) == c2.get(Calendar.DATE)) {
                switch (dosageIndex) {
                    case 1: return r.getBeforeBreakfastInsulin().getFormattedInsulinDosage();
                    case 2: return r.getBeforeLunchInsulin().getFormattedInsulinDosage();
                    case 3: return r.getBeforeDinnerInsulin().getFormattedInsulinDosage();
                    case 4: return r.getBeforeBedInsulin().getFormattedInsulinDosage();
                }
            }
        }
        return "";
    }

    private String getDosage(Dialog dialog, List<PersonDateRecord> periodLog, int dosageIndex, Date date) {
        RadioButton y = dialog.findViewById(R.id.radioUseYesterday);
        RadioButton w = dialog.findViewById(R.id.radioUseLastWeek);
        RadioButton f = dialog.findViewById(R.id.radioUseForecast);
        RadioButton a = dialog.findViewById(R.id.radioUseAverge);
        RadioButton c = dialog.findViewById(R.id.radioUseCustom);
        if (y.isChecked()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            return getInsulinDosageForDate(periodLog,dosageIndex,cal.getTime());
        }
        if (w.isChecked()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -7);
            return getInsulinDosageForDate(periodLog,dosageIndex,cal.getTime());
        }
        if (f.isChecked()) {
            // TODO: Create logic
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            return getInsulinDosageForDate(periodLog,dosageIndex,cal.getTime());
        }
        if (a.isChecked()) {
            // TODO: Create logic
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            return getInsulinDosageForDate(periodLog,dosageIndex,cal.getTime());
        }
        if (c.isChecked()) {

            return getCustomDosage(dialog);
        }
        return null;
    }

    private String getCustomDosage(Dialog dialog) {
        Spinner spinner1 = dialog.findViewById(R.id.spinner);
        Spinner spinner2 = dialog.findViewById(R.id.spinner2);
        Spinner spinner3 = dialog.findViewById(R.id.spinner3);

        String dosageN = spinner1.getSelectedItem().toString();
        String dosageR = spinner2.getSelectedItem().toString();
        String dosageL = spinner3.getSelectedItem().toString();

        String dosage = "";
        String separator = "";
        if (dosageN != null && !dosageN.isEmpty()) {
            dosage += dosageN + "N";
            separator = " + ";
        }
        if (dosageR != null && !dosageR.isEmpty()) {
            dosage += separator + dosageR + "R";
            separator = " + ";
        }
        if (dosageL != null && !dosageL.isEmpty()) {
            dosage += separator + dosageL + "L";
            separator = " + ";
        }
        if (dosage.isEmpty()) {
            dosage = "-";
        }
        return dosage;
    }


    class SaveGlucoseTask  extends AsyncTask<Void, Void, String> {

        protected void onPreExecute() {
            //spinner.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(Void... urls) {
            try {
                String serviceUrl = "https://btcell.herokuapp.com/btcell/savePersonDateGlucoseReading";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String recordDate = sdf.format(new Date());
                String postParameters = "personId=123";
                postParameters += "&recordDate=" + recordDate;
                postParameters += "&dosageCode=" + dosageCode;
                postParameters += "&glucoseReading=" + glucoseReading;

                URL url = new URL(serviceUrl);

                HttpsURLConnection urlConnection =
                        (HttpsURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");

                urlConnection.setFixedLengthStreamingMode(
                        postParameters.getBytes().length);
                PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
                out.print(postParameters);
                out.close();
                int statusCode = urlConnection.getResponseCode();
                return statusCode == 200 ? "success" : "error";
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                //statusView.setText(">" + e.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String response) {
            //spinner.setVisibility(View.GONE);
            //statusView.setText("Saved dosage succesfully");
        }
    }



    class SaveInsulinTask  extends AsyncTask<Void, Void, String> {

        protected void onPreExecute() {
            //spinner.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(Void... urls) {
            try {
                String serviceUrl = "https://btcell.herokuapp.com/btcell/updatePersonDateInsulinUsingString";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String recordDate = sdf.format(new Date());
                String postParameters = "personId=123";
                postParameters += "&recordDate=" + recordDate;
                postParameters += "&dosageCode=" + dosageCode;
                postParameters += "&dosageValues=" + dosageValue.replace("+","%2B");

                URL url = new URL(serviceUrl);

                HttpsURLConnection urlConnection =
                        (HttpsURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");

                urlConnection.setFixedLengthStreamingMode(
                        postParameters.getBytes().length);
                PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
                out.print(postParameters);
                out.close();
                int statusCode = urlConnection.getResponseCode();
                return statusCode == 200 ? "success" : "error";
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
               //statusView.setText(">" + e.getMessage());
                return null;
            }
        }

        protected void onPostExecute(String response) {
            //spinner.setVisibility(View.GONE);
            //statusView.setText("Saved dosage succesfully");
        }
    }

    class RetrieveLogTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {

            try {
                URL url = new URL("https://btcell.herokuapp.com/btcell/getPersonStandardInsulinLog?personId=123&maxRecords=360");
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } catch (Exception e) {
                    return e.getMessage();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            try {

                if (response == null) {
                    //statusView.setText("Step 4 nulli");
                } else {
                    //statusView.setText("Oki");

                    JSONArray jsonArray=new JSONArray(response);
                    if (jsonArray == null) {
                       //statusView.setText("Step 5 no log");
                    } else {
                        //statusView.setText("All greatness:" + jsonArray.length());

                        List<PersonDateRecord> log = new ArrayList<PersonDateRecord>();
                        for (int i =0; i < jsonArray.length() ; i++) {
                            PersonDateRecord dayRecord = new PersonDateRecord();

                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            dayRecord.setRecordDate(getValueDate(jsonObj,"recordDate"));

                            dayRecord.setBeforeBreakfastInsulin(getInsulinDosage(jsonObj, "beforeBreakfastInsulin","BK"));

                            dayRecord.setBeforeLunchInsulin(getInsulinDosage(jsonObj,"beforeLunchInsulin","LN"));

                            dayRecord.setBeforeDinnerInsulin(getInsulinDosage(jsonObj, "beforeDinnerInsulin","DN"));

                            dayRecord.setBeforeBedInsulin(getInsulinDosage(jsonObj,"beforeBedInsulin","BE"));

                            log.add(dayRecord);

                        }

                        Collections.sort(log);

                        periodLog = log;

                        drawTodayRecord(stk);

                        drawLog(stk);
                    }
                }

            } catch (JSONException e) {
                //statusView.setText("Step 6 exception: " + response);
                e.printStackTrace();
            }
        }


        private Date getValueDate(JSONObject object, String key) {
            Object o = null;
            try {
                o = object.get(key);
                if (o instanceof String) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date recordDate = df.parse((String)o);
                        return recordDate;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private String getValueString(JSONObject object, String key) {
            Object o = null;
            try {
                o = object.get(key);
                if (o instanceof String) {
                    return (String)o;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private double getValueDouble(JSONObject object, String key) {
            Object o = null;
            try {
                o = object.get(key);
                if (o instanceof Double) {
                    return (Double)o;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return 0;
        }

        private PersonDateInsulinDosage getInsulinDosage(JSONObject jsonObj, String jsonKey, String dosageCode) {
            PersonDateInsulinDosage dosage = new PersonDateInsulinDosage();
            try {
                Object o = jsonObj.get(jsonKey);
                if (o == null || ! (o instanceof JSONObject)) {
                    return dosage;
                }
                JSONObject jsonDosage = (JSONObject)o;

                if (jsonDosage == null) {
                    return dosage;
                }
                dosage.setGlucoseReading(getValueDouble(jsonDosage,"glucoseReading"));
                dosage.setDosageCode(dosageCode);
                dosage.setInsulinType1(getValueString(jsonDosage,"insulinType1"));
                dosage.setAmount1(getValueDouble(jsonDosage,"amount1"));

                dosage.setInsulinType2(getValueString(jsonDosage,"insulinType2"));
                dosage.setAmount2(getValueDouble(jsonDosage,"amount2"));

                dosage.setInsulinType3(getValueString(jsonDosage,"insulinType3"));
                dosage.setAmount3(getValueDouble(jsonDosage,"amount3"));

                dosage.setInsulinType4(getValueString(jsonDosage,"insulinType4"));
                dosage.setAmount4(getValueDouble(jsonDosage,"amount4"));

                dosage.setInsulinType5(getValueString(jsonDosage,"insulinType5"));
                dosage.setAmount5(getValueDouble(jsonDosage,"amount5"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return dosage;
        }



    }


}
