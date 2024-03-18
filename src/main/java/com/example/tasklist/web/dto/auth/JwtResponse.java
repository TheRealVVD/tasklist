package com.example.tasklist.web.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "JWT Response")
public class JwtResponse {

    @Schema(description = "JWT Response id", example = "1")
    private Long id;

    @Schema(description = "JWT Response username", example = "johndoe@gmail.com")
    private String username;

    @Schema(description = "JWT Response accessToken", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhc2QiLCJpZCI6MTgsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MTA1MTcwOTMsImV4cCI6MTcxMDUyMDY5M30.nOcipBlxsiLAjUXxRVWfa-C-oDuGK0eDhskdtLG5ZPrr2tlaGnW7Ljo4Arqkk2eZ_1PBUNOKjh8ydgr1OrLTNA")
    private String accessToken;

    @Schema(description = "JWT Response refreshToken", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhc2QiLCJpZCI6MTgsImlhdCI6MTcxMDUxNzA5MywiZXhwIjoxNzEzMTA5MDkzfQ.syzicRVKP-gaF9Ul0c2eOvJnUd5LmRzynU22MsfJmYbhNKwGN33Ayx0jP5EsTxA-b_G6EiQGbPG18lNsrbIzRQ")
    private String refreshToken;

}
