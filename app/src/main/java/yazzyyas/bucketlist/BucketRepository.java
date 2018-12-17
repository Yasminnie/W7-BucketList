package yazzyyas.bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BucketRepository {
	private AppDatabase mAppDatabase;
	private BucketDao bucketDao;
	private LiveData<List<Bucket>> buckets;

	private Executor mExecutor = Executors.newSingleThreadExecutor();

	public BucketRepository(Context context) {
		mAppDatabase = AppDatabase.getInstance(context);
		bucketDao = mAppDatabase.bucketDao();
		buckets = bucketDao.getAllBuckets();
	}

	public LiveData<List<Bucket>> getAllBuckets() {
		return buckets;
	}

	public void insert(final Bucket bucket) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				bucketDao.insertBuckets(bucket);
			}
		});
	}

	public void update(final Bucket bucket) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				bucketDao.updateBuckets(bucket);
			}
		});
	}

	public void delete(final Bucket bucket) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				bucketDao.deleteBuckets(bucket);
			}
		});
	}
}
