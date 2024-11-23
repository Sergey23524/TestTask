package ru.example.testtask.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.example.testtask.model.UserEntity;
import ru.example.testtask.repository.UserRepository;
import ru.example.testtask.security.model.AuthenticationRequestRest;
import ru.example.testtask.security.model.AuthenticationResponseRest;
import ru.example.testtask.security.model.TokenRefreshRequestRest;
import ru.example.testtask.security.service.JwtService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginApi {
    private final AuthenticationProvider authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Transactional
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<AuthenticationResponseRest> login(@RequestBody AuthenticationRequestRest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
            )
        );
        UserEntity user = userRepository
            .findByusername(request.getLogin()).orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s not found", request.getLogin())));
        String refreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);
        userRepository.saveAndFlush(user);
        return ResponseEntity.ok(
            AuthenticationResponseRest.builder()
                .token(jwtService.generateToken(new HashMap<>(Map.of("redirect_url", "/")), user))
                    .refreshToken(refreshToken).build()
        );
    }

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.POST, value = "/refresh")
    public ResponseEntity<String> refresh(@RequestBody TokenRefreshRequestRest request) {
        UserEntity user = userRepository
                .findByrefreshToken(request.getRefreshToken()).orElseThrow(() -> new UsernameNotFoundException(String.format("Token: %s not found", request.getRefreshToken())));
        return ResponseEntity.ok(jwtService.generateToken(new HashMap<>(Map.of("redirect_url", "/")), user));
    }
}
