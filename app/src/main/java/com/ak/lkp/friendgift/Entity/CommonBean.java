package com.ak.lkp.friendgift.Entity;

import java.io.Serializable;

/**
 * Created by likunpeng on 2017/3/6.
 */

public class CommonBean implements Serializable{
    private String nickname;
    private String introduction;
    private String avatar_url;

    private String cover_image_url;
    private String description;

    private String likes_count;

    public String getNickname() {
        return nickname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getCover_image_url() {
        return cover_image_url;
    }

    public String getDescription() {
        return description;
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }
}
