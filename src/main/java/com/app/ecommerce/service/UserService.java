package com.app.ecommerce.service;

import com.app.ecommerce.model.UserModel;
import com.app.ecommerce.model.UserRedis;
import com.app.ecommerce.repository.interfaces.RedisRepository;
import com.app.ecommerce.repository.interfaces.UserRepository;
import com.app.ecommerce.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisRepository<UserRedis> redisRepository;
    private final JwtProvider jwtProvider;

    public String login(String email, String password){

        //Busca el usuario en la DB
        UserModel user = this.userRepository.findByEmail(email);

        //Si las contraseñas no coinciden lo informo
        if (!(user.getPassword().equals(password)))
            return "Contraseña incorrecta. El token no pudo ser generado.";

        //Si las constraseñas coinciden lo busco en caché
        UserRedis userRedis = redisRepository.get(email,UserRedis.class);
        if(!(userRedis == null || (userRedis.getTime().getMinute() - LocalDateTime.now().getMinute()) > 30))
            return userRedis.getPassword();

        // Si pasaron mas de 30 min o el ususario no existe en caché, lo creo
        String passwordJwt = jwtProvider.getJWTToken(email);
        LocalDateTime time = LocalDateTime.now();
        redisRepository.save(email,UserRedis.builder().email(email).password(passwordJwt).time(time).build());

        return passwordJwt;
    }

    public UserModel signup(String email, String pass, String pass2) {

        if (!Objects.equals(pass, pass2)) {
//            throw new ExceptionAutentication("Las contaseñas no coinciden, el usuario no fue creado");
            System.out.println("Las contraseñas no coinciden");
            return null;
        }

        if (this.userRepository.findByEmail(email) != null)
//            throw new ExceptionAutentication("El email ya está siendo usado");
            return null;

        UserModel newUser = this.userRepository.save(UserModel.builder().email(email).password(pass).build());

        return newUser;
    }

}
