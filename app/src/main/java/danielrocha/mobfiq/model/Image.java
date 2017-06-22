package danielrocha.mobfiq.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielrocha on 22/06/17.
 */

public class Image {
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("ImageTag")
    @Expose
    private String imageTag;
    @SerializedName("Label")
    @Expose
    private String label;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTag() {
        return imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
