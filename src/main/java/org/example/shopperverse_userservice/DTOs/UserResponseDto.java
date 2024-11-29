package org.example.shopperverse_userservice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.shopperverse_userservice.Models.Role;
import org.example.shopperverse_userservice.Models.User;

import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private List<Role> roles;

    public static UserResponseDto from(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRoles(user.getRoles());
        return userResponseDto;
    }
}
