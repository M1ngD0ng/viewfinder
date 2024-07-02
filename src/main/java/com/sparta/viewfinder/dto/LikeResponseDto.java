package com.sparta.viewfinder.dto;


import com.sparta.viewfinder.entity.ContentsTypeEnum;

public class LikeResponseDto {
    public ContentsTypeEnum contentsType;
    public Long contentId;
    public Long likeCount;
    public String statusMessage;

    public LikeResponseDto(ContentsTypeEnum contentsType, Long contentId, Long likeCount, String statusMessage){
        this.contentsType = contentsType;
        this.contentId = contentId;
        this.likeCount = likeCount;
        this.statusMessage = statusMessage;
    }
}
