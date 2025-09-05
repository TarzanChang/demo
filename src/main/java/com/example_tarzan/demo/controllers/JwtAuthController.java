package com.example_tarzan.demo.controllers;

import com.example_tarzan.demo.requests.LoginRequest;
import com.example_tarzan.demo.requests.RegisterUserRequest;
import com.example_tarzan.demo.responses.AuthResponse;
import com.example_tarzan.demo.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@CrossOrigin("*")
@Tag(name = "JWT驗證", description = "提供使用者登入註冊")
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "註冊用戶",description = "1.username 不得重複。 <br/>" +
            "2.密碼必須 8 個字元以上。 <br/>" +
            "3.必須提供用戶角色，並以 ROLE_開頭(ROLE_USER)。" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "操作成功"),
            @ApiResponse(responseCode = "401", description = "資料格式不正確"),
            @ApiResponse(responseCode = "403", description = "權限不符")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserRequest request){
        if(request.getPassword().length() < 8 || request.getRole().startsWith("ROLE_")){
            //401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //200
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = "jwt 登入用戶",description = "返回 Token")
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.auth(request));
    }
}
