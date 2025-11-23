package com.Euflausino.infra.security.dto;


import com.Euflausino.infra.security.entity.User;

public class UserMapper {

    public static User mapToEntity(UserCadastroDTO user) {
        return new User(
                null,
                user.username(),
                user.senha(),
                user.email()
        );
    }

    public static UserResponseDTO mapToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public static TokenResponseDTO mapToken(String token){
        return new TokenResponseDTO(token);
    }

}
