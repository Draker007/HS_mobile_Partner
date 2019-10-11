package service.com.surebot.info.serviceperson.DataFiles;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class awardsData {
    String text;
    Bitmap Image;

    ArrayList<String> imagefile = new ArrayList<>();

    public awardsData(String text) {
        this.text = text;
    }

    public awardsData(Bitmap image) {
        Image = image;
    }

    public awardsData(ArrayList<String> imagefile) {
        this.imagefile = imagefile;
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

    public ArrayList<String> getImagefile() {
        return imagefile;
    }

    public void setImagefile(ArrayList<String> imagefile) {
        this.imagefile = imagefile;
    }
}
