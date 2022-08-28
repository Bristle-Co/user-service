package com.bristle.userservice.converter;

import com.bristle.proto.user.User;
import com.bristle.userservice.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter {

    public User entityToProto(UserEntity entity) {
        return User.newBuilder()
                .setId(entity.getUserId())
                .setName(entity.getName())
                .build();
    }

    public UserEntity protoToEntity(User proto) {
        return new UserEntity(proto.getId(), proto.getName());
    }
}
