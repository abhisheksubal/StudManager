package in.abhisheksubal.studmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddSubject extends Activity {
	EditText NewSubjectNameTv ;
Button BOk ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_sub_layout);
		NewSubjectNameTv = (EditText)findViewById(R.id.addSubtv);
		BOk = (Button)findViewById(R.id.BaddSub);
		BOk.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = (String)NewSubjectNameTv.getText().toString();
				if(name != null){
					CursorHelper ch = new CursorHelper(getBaseContext());
					SQLiteDatabase db = ch.getWritableDatabase();
					ContentValues ct = new ContentValues();
					ct.put(CursorHelper.COLUMN_SUBJECT, name); 
					ct.put(CursorHelper.COLUMN_ATT,0);
					db.insert(CursorHelper.TABLE_NAME, null, ct);
					Intent i =new Intent(getBaseContext(), SubjectList.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			}
			
		});
	}

	



}
