package yazzyyas.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class MainViewModel extends ViewModel {

	private BucketRepository bucketRepository;
	private LiveData<List<Bucket>> buckets;

	public MainViewModel(Context context) {
		bucketRepository = new BucketRepository(context);
		buckets = bucketRepository.getAllBuckets();
	}

	public LiveData<List<Bucket>> getReminders() {
		return buckets;
	}

	public void insert(Bucket bucket) {
		bucketRepository.insert(bucket);
	}

	public void update(Bucket bucket) {
		bucketRepository.update(bucket);
	}

	public void delete(Bucket bucket) {
		bucketRepository.delete(bucket);
	}

}
