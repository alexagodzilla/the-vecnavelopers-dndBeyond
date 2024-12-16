package com.vecnavelopers.dndbeyond.dto;

public class NameAndPicDto {
    private final String name;
    private final String pic_url;

    public NameAndPicDto(String name, String pic_url) {
        this.name = name;
        this.pic_url = pic_url;
    }

    public String getName() {
        return name;
    }

    public String getPic_url() {
        return pic_url;
    }
}
