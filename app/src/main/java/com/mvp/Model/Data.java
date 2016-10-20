package com.mvp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private String title;
    private String description;
    private String image;
    private String link;
    private String pubDate;
	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.title);
		dest.writeString(this.description);
		dest.writeString(this.image);
		dest.writeString(this.link);
		dest.writeString(this.pubDate);
		dest.writeString(this.domain);
	}

	public Data() {
	}

	protected Data(Parcel in) {
		this.title = in.readString();
		this.description = in.readString();
		this.image = in.readString();
		this.link = in.readString();
		this.pubDate = in.readString();
		this.domain = in.readString();
	}

	public static final Creator<Data> CREATOR = new Creator<Data>() {
		@Override
		public Data createFromParcel(Parcel source) {
			return new Data(source);
		}

		@Override
		public Data[] newArray(int size) {
			return new Data[size];
		}
	};
}
