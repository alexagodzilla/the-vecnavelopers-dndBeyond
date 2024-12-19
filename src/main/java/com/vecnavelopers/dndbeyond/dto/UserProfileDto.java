package com.vecnavelopers.dndbeyond.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserProfileDto {
    private final Long id;
    private final String picUrl;
}
