package vn.edu.poly.assignment1nc;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import vn.edu.poly.assignment1nc.database.DatabaseManager;

public class ClassManagement extends AppCompatActivity {

    private EditText EDMaLop;
    private EditText EDTenLop;
    private Button Them;
    private Button Huy;
    private ListView lvLop;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    private DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_management);

        manager = new DatabaseManager(this);

        //Khỏi tạo
        EDMaLop = findViewById(R.id.EdMaLop);
        EDTenLop = findViewById(R.id.EdTenLop);
        Them = findViewById(R.id.btAdd);
        Huy = findViewById(R.id.btCancel);
        lvLop = findViewById(R.id.LvLop);

        // Xử lý
        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Thêm Lớp vào database
                String Malop = EDMaLop.getText().toString();
                String Tenlop = EDTenLop.getText().toString();
                if (Malop.equals("") || Tenlop.equals("")) {
                    Toast.makeText(ClassManagement.this, "Nhập vào!", Toast.LENGTH_SHORT).show();
                } else {
                    manager.insertLop(Malop, Tenlop);
                }
                getLop();
                //Hiển thị luôn trên ListView

            }
        });

        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EDMaLop.setText("");
                EDTenLop.setText("");
            }
        });
        getLop();
    }

    public void getLop() {
        cursor = manager.getLop();
        if (cursor != null) {
            adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.item_class, cursor,
                    new String[]{"_id"},
                    new int[]{R.id.TvID, R.id.TvMaLop, R.id.TvTenLop});
            lvLop.setAdapter(adapter);
        }
    }
}
