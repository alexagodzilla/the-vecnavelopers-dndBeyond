package com.vecnavelopers.dndbeyond.dto;

public class NameAndPicDto {
    private final String name;
    private final String picUrl;
    private final String description;

    public String getDescriprion() {
        return description;
    }

    public NameAndPicDto(String name, String picUrl, String description) {
        this.name = name;
        this.picUrl = picUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPicUrl() {
        return picUrl;
    }
}
