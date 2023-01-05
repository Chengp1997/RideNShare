package edu.vt.ridenshare.server.enums;

import lombok.Getter;

@Getter
public enum PostStatusEnum {

    ALL(-1),
    IN_PROGRESS(0),
    COMPLETED(1),
    CANCELLED(2),

    ;

    private int code;

    PostStatusEnum(int code) {
        this.code = code;
    }
}
