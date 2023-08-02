package com.example.anime;

public class Anime_extended {
    private String title;
    private float score;
    private int year;
    private String image_url;
    private Anime.Images images;
    private String synopsis;
    private String type;
    private String episodes;
    private String status;


    public class Images {
        private Anime.Jpg jpg;

        public Anime.Jpg getJpg() {
            return jpg;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public class Jpg {
        private String image_url;

        public String getImage_url() {
            return image_url;
        }
    }

    public String getImage_url() {
        return images.getJpg().getImage_url();
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

}
