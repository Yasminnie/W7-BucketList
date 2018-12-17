package yazzyyas.bucketlist;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements BucketAdapter.ItemClickListener {

	//Local variables
	FloatingActionButton addBucket;
	private BucketAdapter bucketAdapter;
	private RecyclerView mRecyclerView;
	private List<Bucket> buckets;
	private EditText bucketTitle, bucketText;
	private MainViewModel mMainViewModel;

	//Constants used when calling the update activity
	public static final String EXTRA_BUCKET = "Bucket";
	public static final int REQUESTCODE = 1234;
	private int mModifyPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRecyclerView = findViewById(R.id.recyclerView);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

		addBucket = findViewById(R.id.FabAddBucket);
		addBucket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), AddBucketActivity.class);
				startActivityForResult(intent, REQUESTCODE);
			}
		});
	}

	@Override
	public void onItemClick(View view, int position) throws ExecutionException, InterruptedException {

	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}
}
