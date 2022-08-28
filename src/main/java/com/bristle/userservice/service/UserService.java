package com.bristle.userservice.service;

import com.bristle.proto.user.User;
import com.bristle.userservice.model.UserEntity;
import com.bristle.userservice.converter.UserEntityConverter;
import com.bristle.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository m_userRepository;

    private final UserEntityConverter m_converter;

    public UserService(UserRepository userRepository, UserEntityConverter m_converter) {
        this.m_userRepository = userRepository;
        this.m_converter = m_converter;
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        List<UserEntity> rs = m_userRepository.findAll();
        return rs.stream().map(m_converter::entityToProto).collect(Collectors.toList());
    }
}
