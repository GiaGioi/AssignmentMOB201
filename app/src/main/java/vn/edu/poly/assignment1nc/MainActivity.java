package vn.edu.poly.assignment1nc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Map(View view) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void Course(View view) {
        Intent intent = new Intent(MainActivity.this, CourseRegistrationActivity.class);
        startActivity(intent);
    }

    public void New(View view) {
        Intent intent = new Intent(MainActivity.this, DailyNewActivity.class);
        startActivity(intent);
    }

    public void Social(View view) {
        Intent intent = new Intent(MainActivity.this, SocialActivity.class);
        startActivity(intent);
    }
}
