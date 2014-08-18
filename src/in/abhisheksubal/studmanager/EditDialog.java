package in.abhisheksubal.studmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditDialog extends Activity {
	Intent data;
	CursorHelper ch;
	Cursor click;
	String oldSub;
    Intent x ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		data = getIntent();
		x = new Intent (this , SubjectList.class);
		x.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		setContentView(R.layout.edit_dialog);
		final EditText edit_dialog_et = (EditText) findViewById(R.id.edit_dialog_et);
		oldSub = data.getStringExtra("Subject");
		edit_dialog_et.setHint("Enter New Subject Name");
		Button edit_dialog_ok = (Button) findViewById(R.id.edit_dialog_ok);
		Button delete_this=(Button)findViewById(R.id.delete_this);
		Button edit_dialog_reset = (Button) findViewById(R.id.edit_dialog_reset);
		delete_this.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursorHelper ch = new CursorHelper(getBaseContext());
               SQLiteDatabase db = ch.getWritableDatabase();
               db.delete(CursorHelper.TABLE_NAME, CursorHelper.COLUMN_SUBJECT+" = ?", new String[]{oldSub});
               startActivity(x);
			}
			
		});
		edit_dialog_ok.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ch = new CursorHelper(getBaseContext());
				SQLiteDatabase dBase = ch.getWritableDatabase();
//				click = dBase.rawQuery("select * from "
//						+ CursorHelper.TABLE_NAME + " where "
//						+ CursorHelper.COLUMN_SUBJECT + " = ?",
//						new String[] { oldSub });
//				click.moveToFirst();
				ContentValues ct = new ContentValues();
				ct.put(CursorHelper.COLUMN_SUBJECT, edit_dialog_et.getText().toString());
				dBase.update(CursorHelper.TABLE_NAME,ct, CursorHelper.COLUMN_SUBJECT+" = ?", new String[]{oldSub});
				startActivity(x);
			}

		});
		super.onCreate(savedInstanceState);
		edit_dialog_reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ch = new CursorHelper(getBaseContext());
				SQLiteDatabase dBase = ch.getWritableDatabase();
//				click = dBase.rawQuery("select * from "
//						+ CursorHelper.TABLE_NAME + " where "
//						+ CursorHelper.COLUMN_SUBJECT + " = ?",
//						new String[] { oldSub });
//				click.moveToFirst();
				ContentValues ct = new ContentValues();
				ct.put(CursorHelper.COLUMN_ATT, 0);
				dBase.update(CursorHelper.TABLE_NAME,ct, CursorHelper.COLUMN_SUBJECT+" = ? ", new String[]{oldSub});
				
				startActivity(x);
			}

		});

	}

}
