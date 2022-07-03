package ru.gb.gbshopmay.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.gb.gbapimay.security.UserDto;
import ru.gb.gbshopmay.entity.security.AccountUser;

/**
 * @author Artem Kropotov
 * created at 01.06.2022
 **/
@Mapper
public interface UserMapper {
    AccountUser toAccountUser(UserDto userDto);
    UserDto toUserDto(AccountUser accountUser);
}
