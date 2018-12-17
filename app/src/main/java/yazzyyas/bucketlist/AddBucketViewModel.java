package yazzyyas.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class AddBucketViewModel extends ViewModel {

	private BucketRepository bucketRepository;
	private LiveData<List<Bucket>> buckets;

	public AddBucketViewModel(Context context) {
		bucketRepository = new BucketRepository(context);
		buckets = bucketRepository.getAllBuckets();
	}

	public LiveData<List<Bucket>> getBuckets() {
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
