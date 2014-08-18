package in.abhisheksubal.studmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends Activity implements View.OnClickListener {
   


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.setFinishOnTouchOutside(false);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.settings_dialog);
	
		Button Att = (Button)findViewById(R.id.AddSubject);
		Att.setText("Add Subject");
		
		Att.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.AddSubject :
			Intent i = new Intent(this,AddSubject.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			break;
	
		}
	}

	
	
	

}
