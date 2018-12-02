package vn.edu.poly.assignment1nc;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import vn.edu.poly.assignment1nc.database.DatabaseManager;

public class StudentManagement extends AppCompatActivity {

    private EditText edName;
    private EditText edDiachi;
    private Spinner SPLop;
    private String tenLop;
    private ListView lvLop;
    private Button btThem;
    private Button btHuy;

    private Cursor cursorLop;
    private android.widget.SimpleCursorAdapter adapterLop;
    private DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_management);

        edName = findViewById(R.id.EdTenSv);
        edDiachi = findViewById(R.id.EdDiachi);
        btThem = findViewById(R.id.btThem);
        lvLop = findViewById(R.id.lvLop);
        SPLop = findViewById(R.id.SpLop);
        manager = new DatabaseManager(this);



        cursorLop = manager.getLop();
        if (cursorLop != null) {
            adapterLop = new android.widget.SimpleCursorAdapter(this,
                    android.R.layout.simple_spinner_item,
                    cursorLop,
                    new String[]{},
                    new int[]{android.R.id.text1});
            SPLop.setAdapter(adapterLop);
        }
        SPLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                tenLop = cursor.getString(1);
                tenLop = cursor.getString(2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ten = edName.getText().toString();
                String Ngaysinh = edDiachi.getText().toString();

                if (Ten.equals("") || Ngaysinh.equals("")) {
                    Toast.makeText(StudentManagement.this, "Nhap vao", Toast.LENGTH_SHORT).show();
                } else {
                    manager.insertSinhVien(tenLop, Ten, Ngaysinh);
                    hienthiSinhvien();
                }

            }
        });
        edName.setText("");
        edDiachi.setText("");
        SPLop.setSelected(false);
    }


    public void hienthiSinhvien() {
        cursorLop = manager.getSinhVien();
        if (cursorLop != null) {
            adapterLop = new android.widget.SimpleCursorAdapter(this,
                    R.layout.item_student,
                    cursorLop,
                    new String[]{"_id", "MaLop", "TenLop", "Ngaysinh"},
                    new int[]{R.id.tvID, R.id.tvMaLop, R.id.tvName, R.id.tvNgaysinh});
            lvLop.setAdapter(adapterLop);
        }
    }


}

