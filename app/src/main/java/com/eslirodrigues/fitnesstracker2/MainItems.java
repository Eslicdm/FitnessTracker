package com.eslirodrigues.fitnesstracker2;

public class MainItems {
    private int idLabel;
    private int textLabel;
    private int imageLabel;
    private int colorLabel;

    public MainItems(int idLabel, int textLabel, int imageLabel, int colorLabel) {
        this.idLabel = idLabel;
        this.textLabel = textLabel;
        this.imageLabel = imageLabel;
        this.colorLabel = colorLabel;
    }

    public int getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(int idLabel) {
        this.idLabel = idLabel;
    }

    public int getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(int textLabel) {
        this.textLabel = textLabel;
    }

    public int getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(int imageLabel) {
        this.imageLabel = imageLabel;
    }

    public int getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(int colorLabel) {
        this.colorLabel = colorLabel;
    }
}
