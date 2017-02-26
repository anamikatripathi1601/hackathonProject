package com.example.anamika.letstry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anamika on 2/2/17.
 */

public class Model implements Parcelable {
    private String titleName,description;
    private String thumbnailImage, urlLink;

    public Model()
    {

    }

    private Model(Parcel in) {
        titleName = in.readString();
        description = in.readString();
        thumbnailImage = in.readString();
        urlLink = in.readString();

    }


    public Model(String titleName , String thumbnailImage , String description, String urlLink)
    {
        this.titleName = titleName;
        this.thumbnailImage = thumbnailImage;
        this.description = description;
        this.urlLink = urlLink;

    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(titleName);
        dest.writeString(thumbnailImage);
        dest.writeString(urlLink);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };


}
