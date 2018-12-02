package vn.edu.poly.assignment1nc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CourseRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_registration);
    }

    public void moQuanLyLop(View view) {
        Intent intent = new Intent(CourseRegistrationActivity.this, ClassManagement.class );
        startActivity(intent);
    }

    public void moQuanLySinhVien(View view) {
        Intent intent = new Intent(CourseRegistrationActivity.this, StudentManagement.class );
        startActivity(intent);
    }
}
