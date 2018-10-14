package com.tbn.duan2.model.Latest;

public class Post {
    String cat_id,title,content,image,desperation,create_at;

    public Post() {
    }

    public Post(String cat_id, String title, String content, String image, String desperation, String create_at) {
        this.cat_id = cat_id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.desperation = desperation;
        this.create_at = create_at;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesperation() {
        return desperation;
    }

    public void setDesperation(String desperation) {
        this.desperation = desperation;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}
