package edu.vt.ridenshare.server.enums;

import lombok.Getter;

@Getter
public enum PostTypeEnum {
    DRIVER(0),
    PASSENGER(1),

    ;

    private int code;

    PostTypeEnum(int code) {
        this.code = code;
    }
}
