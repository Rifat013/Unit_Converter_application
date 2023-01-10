package com.example.unitconverter;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Arrays;

public class length_cal extends AppCompatActivity {

    CardView cv_fromUnit, cv_toUnit, cv_convert, history;
    RelativeLayout mCLayout;
    String fromUnit = "Meter";
    String toUnit = "Kilometer";
    TextView tv_fromUnit, tv_toUnit;
    EditText et_fromUnit, et_toUnit;
    SQLiteDatabase db;
    final String[] values = new String[]{
            "Meter",
            "Kilometer",
            "Centimeter",
            "Miles",
            "Feet", "Inches"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_cal);

        cv_fromUnit = findViewById(R.id.fromUnit);
        cv_toUnit = findViewById(R.id.toUnit);
        cv_convert = findViewById(R.id.cv_convert);
        history = findViewById(R.id.history);

        mCLayout = findViewById(R.id.temp_relativeLayout);

        tv_fromUnit = findViewById(R.id.tv_fromUnit);
        tv_toUnit = findViewById(R.id.tv_toUnit);

        tv_fromUnit.setText(values[0]);
        tv_toUnit.setText(values[0]);

        et_fromUnit = findViewById(R.id.et_fromUnit);
        et_toUnit = findViewById(R.id.et_toUnit);

        db = openOrCreateDatabase("mydb",MODE_PRIVATE,null);
        try {
            db.execSQL("create table if not exists records(fromunitname varchar(20), fromunit varchar(20), tounitname varchar(20), tounit varchar(20))");
            Toast.makeText(getApplicationContext(),"table created",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"table creation failed",Toast.LENGTH_LONG).show();
        }

        cv_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempInput = et_fromUnit.getText().toString();
                if (tempInput.equals("") || tempInput == null) {
                    et_fromUnit.setError("Please enter some value");
                } else {
                    if (tv_fromUnit.getText().toString().equals(values[0])) {
                        if (tv_toUnit.getText().toString().equals(values[0])) {
                            et_toUnit.setText(tempInput);
                        } else if (tv_toUnit.getText().toString().equals(values[1])) {
                            et_toUnit.setText(meterToCentimeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[2])) {
                            et_toUnit.setText(meterToKilometer(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[3])) {
                            et_toUnit.setText(meterToMiles(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[4])) {
                            et_toUnit.setText(meterToFeet(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[5])) {
                            et_toUnit.setText(meterToInches(Double.parseDouble(tempInput)));
                        }
                    } else if (tv_fromUnit.getText().toString().equals(values[1])) {
                        if (tv_toUnit.getText().toString().equals(values[0])) {
                            et_toUnit.setText(KilometerToMeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[1])) {
                            et_toUnit.setText(tempInput);
                        } else if (tv_toUnit.getText().toString().equals(values[2])) {
                            et_toUnit.setText(KilometerToCentimeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[3])) {
                            et_toUnit.setText(KilometerToMiles(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[4])) {
                            et_toUnit.setText(KilometerToFeet(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[5])) {
                            et_toUnit.setText(KilometerToInches(Double.parseDouble(tempInput)));
                        }
                    } else if (tv_fromUnit.getText().toString().equals(values[2])) {
                        if (tv_toUnit.getText().toString().equals(values[0])) {
                            et_toUnit.setText(CentimeterToMeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[1])) {
                            et_toUnit.setText(CentimeterToKilometer(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[2])) {
                            et_toUnit.setText(tempInput);
                        } else if (tv_toUnit.getText().toString().equals(values[3])) {
                            et_toUnit.setText(CentimeterToMiles(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[4])) {
                            et_toUnit.setText(CentimeterToFeet(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[5])) {
                            et_toUnit.setText(CentimeterToInches(Double.parseDouble(tempInput)));
                        }
                    } else if (tv_fromUnit.getText().toString().equals(values[3])) {
                        if (tv_toUnit.getText().toString().equals(values[0])) {
                            et_toUnit.setText(MilesToMeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[1])) {
                            et_toUnit.setText(MilesToKilometer(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[2])) {
                            et_toUnit.setText(MilesToCentimeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[3])) {
                            et_toUnit.setText(tempInput);
                        } else if (tv_toUnit.getText().toString().equals(values[4])) {
                            et_toUnit.setText(MilesToFeet(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[5])) {
                            et_toUnit.setText(MilesToInches(Double.parseDouble(tempInput)));
                        }
                    } else if (tv_fromUnit.getText().toString().equals(values[4])) {
                        if (tv_toUnit.getText().toString().equals(values[0])) {
                            et_toUnit.setText(FeetToMeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[1])) {
                            et_toUnit.setText(FeetToKilometer(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[2])) {
                            et_toUnit.setText(FeetToCentimeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[3])) {
                            et_toUnit.setText(FeetToMiles(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[4])) {
                            et_toUnit.setText(tempInput);
                        } else if (tv_toUnit.getText().toString().equals(values[5])) {
                            et_toUnit.setText(FeetToInches(Double.parseDouble(tempInput)));
                        }
                    } else if (tv_fromUnit.getText().toString().equals(values[5])) {
                        if (tv_toUnit.getText().toString().equals(values[0])) {
                            et_toUnit.setText(InchesToMeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[1])) {
                            et_toUnit.setText(InchesToKilometer(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[2])) {
                            et_toUnit.setText(InchesToCentimeter(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[3])) {
                            et_toUnit.setText(InchesToMiles(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[4])) {
                            et_toUnit.setText(InchesToFeet(Double.parseDouble(tempInput)));
                        } else if (tv_toUnit.getText().toString().equals(values[5])) {
                            et_toUnit.setText(tempInput);
                        }
                    }
                }
                String fun = tv_fromUnit.getText().toString();
                String fu = et_fromUnit.getText().toString();
                String tun = tv_toUnit.getText().toString();
                String tu = et_toUnit.getText().toString();
                String insertQuery = "insert into records values('"+fun+"','"+fu+"','"+tun+"','"+tu+"');";

                try {
                    db.execSQL(insertQuery);
                    Toast.makeText(getApplicationContext(),"row inserted",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"insertion failed",Toast.LENGTH_LONG).show();
                }


            }
        });

        cv_toUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(length_cal.this);
                builder.setTitle("choose Unit");

                final String[] flowers = new String[]{
                        "Meter",
                        "Kilometer",
                        "Centimeter",
                        "Miles",
                        "Feet", "Inches"
                };

                builder.setSingleChoiceItems(
                        flowers, // Items list
                        -1, // Index of checked item (-1 = no selection)
                        new DialogInterface.OnClickListener() // Item click listener
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
// Get the alert dialog selected item's text
                                String selectedItem = Arrays.asList(flowers).get(i);
                                toUnit = selectedItem;
                                tv_toUnit.setText(toUnit);

                            }
                        });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
// Just dismiss the alert dialog after selection
// Or do something now
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();

// Finally, display the alert dialog
                dialog.show();

            }
        });

        cv_fromUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(length_cal.this);
                builder.setTitle("choose Unit");

                final String[] flowers = new String[]{
                        "Meter",
                        "Kilometer",
                        "Centimeter",
                        "Miles",
                        "Feet", "Inches"
                };

                builder.setSingleChoiceItems(
                        flowers, // Items list
                        -1, // Index of checked item (-1 = no selection)
                        new DialogInterface.OnClickListener() // Item click listener
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


// Get the alert dialog selected item's text
                                String selectedItem = Arrays.asList(flowers).get(i);
                                fromUnit = selectedItem;
                                tv_fromUnit.setText(fromUnit);

                            }
                        });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
// Just dismiss the alert dialog after selection
// Or do something now
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();

// Finally, display the alert dialog
                dialog.show();

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),history_records.class);
                startActivity(intent);
                finish();
            }
        });


    }

    //meter
    private String meterToKilometer(double meter) {
        double Kilometer = meter / 1000;
        return String.valueOf(Kilometer);
    }

    private String meterToMiles(double meter) {
        double Miles = meter / 1609;
        return String.valueOf(Miles);
    }

    private String meterToFeet(double meter) {
        double Feet = meter * 3.281;
        return String.valueOf(Feet);
    }

    private String meterToInches(double meter) {
        double Inches = meter * 39.37;
        return String.valueOf(Inches);
    }

    private String meterToCentimeter(double meter) {
        double Centimeter = meter * 100;
        return String.valueOf(Centimeter);
    }

    //Kilometer
    private String KilometerToMeter(double Kilometer) {
        double Meter = Kilometer * 1000;
        return String.valueOf(Meter);
    }

    private String KilometerToCentimeter(double Kilometer) {
        double Centimeter = Kilometer * 100000;
        return String.valueOf(Centimeter);
    }

    private String KilometerToMiles(double Kilometer) {
        double Miles = Kilometer / 1.609;
        return String.valueOf(Miles);
    }

    private String KilometerToFeet(double Kilometer) {
        double Feet = Kilometer * 3281;
        return String.valueOf(Feet);
    }

    private String KilometerToInches(double Kilometer) {
        double Inches = Kilometer * 39370;
        return String.valueOf(Inches);
    }

    //Centimeter
    private String CentimeterToMeter(double Centimeter) {
        double Meter = Centimeter / 100;
        return String.valueOf(Meter);
    }

    private String CentimeterToKilometer(double Centimeter) {
        double Kilometer = Centimeter / 100000;
        return String.valueOf(Kilometer);
    }

    private String CentimeterToMiles(double Centimeter) {
        double Miles = Centimeter / 160900;
        return String.valueOf(Miles);
    }

    private String CentimeterToFeet(double Centimeter) {
        double Feet = Centimeter * 0.032808399;
        return String.valueOf(Feet);
    }

    private String CentimeterToInches(double Centimeter) {
        double Inches = Centimeter * 0.393700787;
        return String.valueOf(Inches);
    }

    //Miles
    private String MilesToMeter(double Miles) {
        double Meter = Miles * 1609.344;
        return String.valueOf(Meter);
    }

    private String MilesToKilometer(double Miles) {
        double Kilometer = Miles * 1.609344;
        return String.valueOf(Kilometer);
    }

    private String MilesToCentimeter(double Miles) {
        double Centimeter = Miles * 160934.4;
        return String.valueOf(Centimeter);
    }

    private String MilesToFeet(double Miles) {
        double Feet = Miles * 5280;
        return String.valueOf(Feet);
    }

    private String MilesToInches(double Miles) {
        double Inches = Miles * 63360;
        return String.valueOf(Inches);
    }

    //Feet
    private String FeetToMeter(double Feet) {
        double Meter = Feet * 0.3048;
        return String.valueOf(Meter);
    }

    private String FeetToKilometer(double Feet) {
        double Kilometer = Feet * 0.0003048;
        return String.valueOf(Kilometer);
    }

    private String FeetToCentimeter(double Feet) {
        double Centimeter = Feet * 30.48;
        return String.valueOf(Centimeter);
    }

    private String FeetToMiles(double Feet) {
        double Miles = Feet * 0.0001893939393;
        return String.valueOf(Miles);
    }

    private String FeetToInches(double Feet) {
        double Inches = Feet * 12;
        return String.valueOf(Inches);
    }

    //Inches
    private String InchesToMeter(double Inches) {
        double Meter = Inches * 0.0254;
        return String.valueOf(Meter);
    }

    private String InchesToKilometer(double Inches) {
        double Kilometer = Inches / 39370;
        return String.valueOf(Kilometer);
    }

    private String InchesToCentimeter(double Inches) {
        double Centimeter = Inches * 2.54;
        return String.valueOf(Centimeter);
    }

    private String InchesToMiles(double Inches) {
        double Miles = Inches / 63360;
        return String.valueOf(Miles);
    }

    private String InchesToFeet(double Inches) {
        double Feet = Inches * 0.0833333333;
        return String.valueOf(Feet);
    }

}