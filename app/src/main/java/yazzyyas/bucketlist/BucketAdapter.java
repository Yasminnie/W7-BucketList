package yazzyyas.bucketlist;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.ViewHolder> {
	private List<Bucket> bucketData;
	private LayoutInflater mInflater;
	private ItemClickListener mClickListener;

	public BucketAdapter(List<Bucket> bucketData, ItemClickListener mClickListener) {
		this.bucketData = bucketData;
		this.mClickListener = mClickListener;
	}

	@NonNull
	@Override
	public BucketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);

		View view = inflater.inflate(R.layout.list_item, parent, false);
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

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		CheckedTextView mCheckedTextView;
		TextView title;
		TextView text;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			title = itemView.findViewById(R.id.bucketTitle);
			text = itemView.findViewById(R.id.bucketText);
			mCheckedTextView = itemView.findViewById(R.id.bucketCheckbox);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			int adapterPosition = getAdapterPosition();
			if (bucketData.get(adapterPosition).getChecked()) {
				mCheckedTextView.setChecked(false);
				bucketData.get(adapterPosition).setChecked(false);
				title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
				text.setPaintFlags(text.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
			} else {
				mCheckedTextView.setChecked(true);
				bucketData.get(adapterPosition).setChecked(true);
				title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
				text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			}
		}
	}

	public void swapList(List<Bucket> newList) {
		bucketData = newList;
		if (newList != null) {
			this.notifyDataSetChanged();
		}
	}

	public interface ItemClickListener {
		void bucketItemOnClick(int i);
	}
}
