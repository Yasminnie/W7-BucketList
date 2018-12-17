package yazzyyas.bucketlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

public class AddBucketActivity extends AppCompatActivity {

	private TextInputEditText titleView;
	private TextInputEditText textView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addbucket);

		titleView = findViewById(R.id.bucketTitle);
		textView = findViewById(R.id.bucketText);

		FloatingActionButton fab = findViewById(R.id.fabSaveBucket);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String title = titleView.getText().toString();
				String text = textView.getText().toString();

				Bucket newBucket = new Bucket(title, text);

				if (!TextUtils.isEmpty(text)) {
					//Prepare the return parameter and return
					Intent resultIntent = new Intent();
					resultIntent.putExtra(MainActivity.EXTRA_BUCKET, newBucket);
					setResult(Activity.RESULT_OK, resultIntent);
					finish();
				} else {
					Snackbar.make(v, "Enter some data", Snackbar.LENGTH_LONG);
				}
			}
		});
	}
}
