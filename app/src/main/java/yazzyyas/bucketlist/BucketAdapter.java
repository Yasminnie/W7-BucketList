package yazzyyas.bucketlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.ViewHolder> {
	private List<Bucket> bucketData;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	public BucketAdapter(Context context, List<Bucket> bucketData, ItemClickListener mClickListener) {
		this.mInflater = LayoutInflater.from(context);
		this.bucketData = bucketData;
		this.mClickListener = mClickListener;
	}

	@NonNull
	@Override
	public BucketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		View view = mInflater.inflate(R.layout.list_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull BucketAdapter.ViewHolder holder, int position) {
		Bucket bucket = bucketData.get(position);

		holder.title.setText(bucket.getBucketTitle());
		holder.text.setText(bucket.getBucketText());
	}

	@Override
	public int getItemCount() {
		return bucketData.size();
	}

	public void setBucketData(List<Bucket> bucketData) {
		this.bucketData = bucketData;
		notifyDataSetChanged(); // zegt tegen UI dat je items kan updaten in recyclerview
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView title;
		TextView text;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			title = itemView.findViewById(R.id.bucketTitle);
			text = itemView.findViewById(R.id.bucketText);
		}

		@Override
		public void onClick(View v) {
//			hier komt iets voor de checkbox
		}
	}
	
	public interface ItemClickListener {
		void onItemClick(View view, int position) throws ExecutionException, InterruptedException;
	}
}
