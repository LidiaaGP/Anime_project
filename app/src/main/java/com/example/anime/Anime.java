package com.example.anime;

import java.io.Serializable;
import java.util.List;

public class Anime implements Serializable {
    private String title;
    private float score;
    private int year;
    private String synopsis;
    private String type;
    private Integer episodes;
    private String status;
    private String title_japanese;
    private String source;
    private String image_url;
    private Images images;
    private List<Studios> studios;
    private Integer mal_id;

    public class Images implements Serializable{
        private Jpg jpg;

        public Jpg getJpg() {
            return jpg;
        }
    }
    public class Jpg implements Serializable{
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

    public class Studios implements Serializable {
        private String name;

        public String getName() {
            return name;
        }
    }

    public List<Studios> getStudiosList() {
        return studios;
    }

    public String getTitle_japanese() {
        return title_japanese;
    }

    public void setTitle_japanese(String title_japanese) {
        this.title_japanese = title_japanese;
    }

    public Integer getMal_id() {
        return mal_id;
    }

    public void setMal_id(Integer mal_id) {
        this.mal_id = mal_id;
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


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

}
