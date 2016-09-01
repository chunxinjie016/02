package cxjlight.com.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jsuser on 2016/9/1.
 */
public class AddContent extends Activity implements View.OnClickListener {
    private String val;
    private Button saveBtn,backBtn;
    private EditText ettext;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontent);
        val = getIntent().getStringExtra("flag");
        saveBtn = (Button)findViewById(R.id.save);
        backBtn = (Button)findViewById(R.id.back);
        ettext = (EditText)findViewById(R.id.ettext);

        saveBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save:
                addDB();
                finish();
                break;
            case R.id.back:
                finish();
                break;
        }
    }
    public void addDB(){
        ettext = (EditText)findViewById(R.id.ettext);
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.CONTENT,ettext.getText().toString());
        cv.put(NotesDB.TIME, getTime());
        dbWriter.insert(NotesDB.TABLE_NAME,null,cv);
    }
    private String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH：mm：ss");
        Date date = new Date();
        String str = format.format(date);
        return str;
    }
}


