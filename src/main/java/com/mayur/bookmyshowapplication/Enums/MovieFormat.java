package com.mayur.bookmyshowapplication.Enums;

import lombok.Getter;

@Getter
public enum MovieFormat {
    _2D("2D"),
    _3D("3D");

    private final String displayName;

    MovieFormat(String displayName) {
        this.displayName = displayName;
    }

}
