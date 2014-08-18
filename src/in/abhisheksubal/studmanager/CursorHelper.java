package in.abhisheksubal.studmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CursorHelper extends SQLiteOpenHelper {
	SQLiteDatabase dBase;
	public static final String DATABASE_NAME = "new.db";

	public static final String KEY_ID1 = "_id";
	public static final String TABLE_NAME = "attendance";
	public static final String COLUMN_ATT = "taken";
	public static final String COLUMN_SUBJECT = "subjectname";

	public CursorHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		dBase = db;

		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
				+ KEY_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMN_SUBJECT + " TEXT, " + COLUMN_ATT + " INTEGER ) ";
		db.execSQL(sql);
		addSubject("Click On");
		addSubject("Subject Name");
		addSubject("To get  ");
		addSubject("Edit Options");
		addSubject("Subject 1");
		addSubject("Subject 2");

	}

	public void addSubject(String string) {
		// TODO Auto-generated method stub
		ContentValues values = new ContentValues();
		// values.put(KEY_ID1,id);
		values.put(COLUMN_SUBJECT, string);
		values.put(COLUMN_ATT, 0);

		// Inserting Row
		dBase.insert(TABLE_NAME, null, values);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
