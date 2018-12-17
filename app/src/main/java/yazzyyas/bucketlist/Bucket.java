package yazzyyas.bucketlist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "bucket")
public class Bucket implements Parcelable {
	@PrimaryKey(autoGenerate = true)
	private Long id;

	@ColumnInfo(name = "bucketTitle")
	private String bucketTitle;

	@ColumnInfo(name = "bucketText")
	private String bucketText;

	public Bucket(String bucketTitle, String bucketText) {
		this.bucketTitle = bucketTitle;
		this.bucketText = bucketText;
	}

	protected Bucket(Parcel in) {
		if (in.readByte() == 0) {
			id = null;
		} else {
			id = in.readLong();
		}
		bucketTitle = in.readString();
		bucketText = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (id == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeLong(id);
		}
		dest.writeString(bucketTitle);
		dest.writeString(bucketText);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Bucket> CREATOR = new Creator<Bucket>() {
		@Override
		public Bucket createFromParcel(Parcel in) {
			return new Bucket(in);
		}

		@Override
		public Bucket[] newArray(int size) {
			return new Bucket[size];
		}
	};

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBucketTitle() {
		return bucketTitle;
	}

	public void setBucketTitle(String bucketTitle) {
		this.bucketTitle = bucketTitle;
	}

	public String getBucketText() {
		return bucketText;
	}

	public void setBucketText(String bucketText) {
		this.bucketText = bucketText;
	}
}
