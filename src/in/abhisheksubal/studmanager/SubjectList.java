package in.abhisheksubal.studmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SubjectList extends Activity {
	
	CursorHelper ch;
	public static TextView tvSub;
	public TextView tvAtt;
	Cursor click;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_list);
		String[] from = { CursorHelper.COLUMN_SUBJECT, CursorHelper.COLUMN_ATT };
		int[] to = { R.id.SubjectName, R.id.SubjectAttendance };
		ListView subListView = (ListView) findViewById(R.id.subList);
		ch = new CursorHelper(this);
		SQLiteDatabase dBase = ch.getWritableDatabase();
		Cursor cr = dBase.rawQuery("select * from attendance", null);
		cr.moveToFirst();
		// SimpleCursorAdapter sca = new SimpleCursorAdapter(this,
		// R.layout.sub_list_item, cr, from, to, 0);

		DatabaseAdapter dBA = new DatabaseAdapter(this, R.layout.sub_list_item,
				cr, from, to, 0);
		subListView.setAdapter(dBA);

		subListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getBaseContext(), EditDialog.class);
				tvSub = (TextView) view.findViewById(R.id.SubjectName);
				i.putExtra("Subject", (String) tvSub.getText());
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);

				return true;
			}

		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		this.onCreate(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent i = new Intent(this, SettingActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	
}
