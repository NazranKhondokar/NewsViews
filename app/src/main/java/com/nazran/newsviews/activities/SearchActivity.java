package com.nazran.newsviews.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nazran.newsviews.R;
import com.nazran.newsviews.models.Date;
import com.nazran.newsviews.models.Number;
import com.nazran.newsviews.network.GetDataServiceNumber;
import com.nazran.newsviews.network.RetrofitClientInstanceNumber;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.numberLT)
    LinearLayout numberLT;
    @BindView(R.id.dateLT)
    LinearLayout dateLT;
    @BindView(R.id.numberBT)
    Button numberBT;
    @BindView(R.id.dateBT)
    Button dateBT;
    @BindView(R.id.numberOutput)
    TextView numberOutput;
    @BindView(R.id.dateOutput)
    TextView dateOutput;
    @BindView(R.id.numberET)
    TextInputEditText numberET;
    @BindView(R.id.dateET)
    TextInputEditText dateET;
    @BindView(R.id.monthET)
    TextInputEditText monthET;
    ProgressDialog progressDialog;
    Context context = SearchActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Number/Date Search");

        dateLT.setVisibility(View.INVISIBLE);
        numberLT.setVisibility(View.VISIBLE);
        radioGroup.setOnCheckedChangeListener(this);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");

        numberBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                /*Create handle for the RetrofitInstance interface*/
                GetDataServiceNumber service = RetrofitClientInstanceNumber.getRetrofitInstance().create(GetDataServiceNumber.class);

                Call<Number> call = service.getNumberDetail(Integer.parseInt(numberET.getText().toString()));
                call.enqueue(new Callback<Number>() {

                    @Override
                    public void onResponse(Call<Number> call, Response<Number> response) {
                        progressDialog.dismiss();
                        if (response.body() != null) {
                            if (response.body().getFound()) {
                                numberOutput.setText(response.body().getText());
                                //Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Number> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        Log.e("Search: ", "" + t.getMessage());
                    }
                });
            }
        });

        dateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                /*Create handle for the RetrofitInstance interface*/
                GetDataServiceNumber service = RetrofitClientInstanceNumber.getRetrofitInstance().create(GetDataServiceNumber.class);

                Call<Date> call = service.getDateDetail(
                        Integer.parseInt(monthET.getText().toString()),
                        Integer.parseInt(dateET.getText().toString()));
                call.enqueue(new Callback<Date>() {

                    @Override
                    public void onResponse(Call<Date> call, Response<Date> response) {
                        progressDialog.dismiss();
                        if (response.body() != null) {
                            if (response.body().getFound()) {
                                dateOutput.setText(response.body().getText());
                                //Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Date> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        Log.e("Search: ", "" + t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // This will get the radiobutton that has changed in its check state
        RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
        // This puts the value (true/false) into the variable
        boolean isChecked = checkedRadioButton.isChecked();
        // If the radiobutton that has changed in check state is now checked...
        if (isChecked) {
            if (checkedRadioButton.getText().equals("Number")) {
                dateLT.setVisibility(View.INVISIBLE);
                numberLT.setVisibility(View.VISIBLE);
            } else {
                dateLT.setVisibility(View.VISIBLE);
                numberLT.setVisibility(View.INVISIBLE);
            }
        }
    }
}
