package com.tbn.duan2.model.Latest;

public class Category {
    String id,cat_name,post;

    public Category() {
    }

    public Category(String id, String cat_name, String post) {
        this.id = id;
        this.cat_name = cat_name;
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
