package ru.example.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.example.testtask.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserDetailsService {
    Optional<UserEntity> findByusername(String userName);

    Optional<UserEntity> findByrefreshToken(String refreshToken);

    @Override
    default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByusername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s not found", username)));
    }
}
