package android.hod.datepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvResult;
    private EditText edtStart;
    private EditText edtEnd;
    private Button btnShow;

    private DatePickerDialog datePickerDialogStart;
    private DatePickerDialog datePickerDialogEnd;

    private SimpleDateFormat dateFormat;
    private Calendar calendarStart;
    private Calendar calendarEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        dateFormat  = new SimpleDateFormat("dd/MM/yyyy");

        createDatePickerDialog();
    }

    private void createDatePickerDialog() {
        Calendar now = Calendar.getInstance();

        datePickerDialogStart = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendarStart.set(year, monthOfYear, dayOfMonth);
                edtStart.setText(dateFormat.format(calendarStart.getTime()).toString()); // 07/10/2015
            }
        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

        datePickerDialogEnd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendarEnd.set(year, monthOfYear, dayOfMonth);
                edtEnd.setText(dateFormat.format(calendarEnd.getTime()).toString());
            }
        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));


    }
    private void init() {
        edtStart = (EditText) findViewById(R.id.edtStart);
        edtEnd = (EditText) findViewById(R.id.edtEnd);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnShow = (Button) findViewById(R.id.btnShow);

        btnShow.setOnClickListener(this);
        edtStart.setOnClickListener(this);
        edtEnd.setOnClickListener(this);
        edtStart.requestFocus();

        calendarStart = Calendar.getInstance();
        calendarEnd = Calendar.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtStart:
                datePickerDialogStart.show();
//                tvResult.setText("edtStart");
                break;
            case R.id.edtEnd:
                datePickerDialogEnd.show();
//                tvResult.setText("edtEnd");
                break;
            case R.id.btnShow:
//                tvResult.setText("btnShow");
                showDistance();
                break;
        }
    }

    public void showDistance () {
//        tvResult.setText("");
        String strResult = null;

        if (TextUtils.isEmpty(edtStart.getText()) || TextUtils.isEmpty(edtEnd.getText())) {
            strResult = "Pick date start and end";
        } else {
            int dayCount = (int) ((calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis())/ (24 * 60 * 60 * 1000));
            if(dayCount < 0) {
                strResult    = "Date End will after start date";
            }else{
                strResult    = "Distance of 2 time is " + dayCount + " Day";
            }
        }

        tvResult.setText(strResult);
    }

    public void studyCalendar() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");

        tvResult.setText(now.get(Calendar.YEAR) + "");
        tvResult.setText(now.get(Calendar.MONTH) + 1 + "");
        tvResult.setText(simpleDateFormat.format(now.getTime()).toString());
    }
}