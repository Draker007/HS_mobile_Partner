package service.com.surebot.info.serviceperson.DataFiles;

import android.graphics.Bitmap;

public class awardsData {
    String text;
    Bitmap Image;

    public awardsData(String text) {
        this.text = text;
    }

    public awardsData(Bitmap image) {
        Image = image;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
