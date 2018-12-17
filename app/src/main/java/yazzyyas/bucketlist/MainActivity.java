package yazzyyas.bucketlist;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
	private AddBucketViewModel mAddBucketViewModel;

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

		buckets = new ArrayList<>();

		mAddBucketViewModel = new AddBucketViewModel(getApplicationContext());
		mAddBucketViewModel.getBuckets().observe(this, new Observer<List<Bucket>>() {
			@Override
			public void onChanged(@Nullable List<Bucket> onBuckets) {
				buckets = onBuckets;
				updateUI();
			}
		});

		addBucket = findViewById(R.id.FabAddBucket);
		addBucket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), AddBucketActivity.class);
				startActivityForResult(intent, REQUESTCODE);
			}
		});

		ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
				new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
					@Override
					public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
							target) {
						return false;
					}

					//Called when a user swipes left or right on a ViewHolder
					@Override
					public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
						//Get the index corresponding to the selected position
						int position = (viewHolder.getAdapterPosition());
						mAddBucketViewModel.delete(buckets.get(position));
					}
				};
	}

	private void updateUI() {
		if (bucketAdapter == null) {
			bucketAdapter = new BucketAdapter(buckets, this);
			mRecyclerView.setAdapter(bucketAdapter);
		} else {
			bucketAdapter.swapList(buckets);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUESTCODE) {
			if (resultCode == RESULT_OK) {
				Bucket insertBucket = data.getParcelableExtra(MainActivity.EXTRA_BUCKET);
				mAddBucketViewModel.insert(insertBucket);
			}
		}
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}

	@Override
	public void bucketItemOnClick(int i) {
//		buckets.get(i).
	}
}
