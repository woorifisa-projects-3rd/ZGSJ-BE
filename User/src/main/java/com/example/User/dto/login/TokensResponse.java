package com.example.User.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokensResponse {
    private String accessToken;
    private String refreshToken;

    public static TokensResponse of(String accessToken, String refreshToken) {
        return new TokensResponse(accessToken,refreshToken);
    }
}

