package in.abhisheksubal.studmanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DatabaseAdapter extends SimpleCursorAdapter {
	private LayoutInflater mInflater;
	private Cursor cr, click;
	TextView subjectName, subjectAttendance;
	Button plus, minus;
	CursorHelper ch;
	ContentValues ct ;

	public DatabaseAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		cr = c;

		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = mInflater.inflate(R.layout.sub_list_item, parent, false);
		cr.moveToPosition(position);
		subjectName = (TextView) view.findViewById(R.id.SubjectName);

		subjectName.setText(cr.getString(1));

		subjectAttendance = (TextView) view
				.findViewById(R.id.SubjectAttendance);
		subjectAttendance.setText(cr.getString(2));
		plus = (Button) view.findViewById(R.id.SubjectPlus);
		minus = (Button) view.findViewById(R.id.SubjectMinus);
		plus.setFocusable(false);
		minus.setFocusable(false);
		ch = new CursorHelper(parent.getContext());
		plus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				SQLiteDatabase dBase = ch.getWritableDatabase();

				View view = (View) v.getParent();
				subjectAttendance = (TextView) view
						.findViewById(R.id.SubjectAttendance);
				subjectName = (TextView) view.findViewById(R.id.SubjectName);
				click = dBase.rawQuery("select * from "
						+ CursorHelper.TABLE_NAME + " where "
						+ CursorHelper.COLUMN_SUBJECT + " = ? ",
						new String[] { (String) subjectName.getText() });
				click.moveToFirst();
				Integer temp = Integer.parseInt(click.getString(2));
				temp++;
				ct = new ContentValues();
				ct.put(CursorHelper.COLUMN_ATT, temp);
				dBase.update(CursorHelper.TABLE_NAME, ct,
						CursorHelper.COLUMN_SUBJECT + " = ?", new String[] { (String) subjectName.getText() });
				subjectAttendance.setText(temp.toString());
				
			}

		});
		minus.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase dBase = ch.getWritableDatabase();

				View view = (View) v.getParent();
				subjectAttendance = (TextView) view
						.findViewById(R.id.SubjectAttendance);
				subjectName = (TextView) view.findViewById(R.id.SubjectName);
				click = dBase.rawQuery("select * from "
						+ CursorHelper.TABLE_NAME + " where "
						+ CursorHelper.COLUMN_SUBJECT + " = ? ",
						new String[] { (String) subjectName.getText() });
				click.moveToFirst();
				Integer temp = Integer.parseInt(click.getString(2));
				temp--;
				ct = new ContentValues();
				ct.put(CursorHelper.COLUMN_ATT, temp);
				dBase.update(CursorHelper.TABLE_NAME, ct,
						CursorHelper.COLUMN_SUBJECT + " = ?", new String[] { (String) subjectName.getText() });
				subjectAttendance.setText(temp.toString());
				
			}
			
		});
	
		return view;
		// return super.getView(position, convertView, parent);
	}

}
