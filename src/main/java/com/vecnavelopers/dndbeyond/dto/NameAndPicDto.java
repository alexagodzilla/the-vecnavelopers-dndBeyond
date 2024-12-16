package com.vecnavelopers.dndbeyond.dto;

public class NameAndPicDto {
    private final String name;
    private final String picUrl;

    public NameAndPicDto(String name, String picUrl) {
        this.name = name;
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public String getPicUrl() {
        return picUrl;
    }
}
