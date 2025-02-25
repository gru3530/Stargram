package com.flab.stargram.domain.fanout.service;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.flab.stargram.domain.fanout.strategy.FanoutStrategy;
import com.flab.stargram.entity.common.UserTypeEnum;
import com.flab.stargram.entity.model.Post;
import com.flab.stargram.service.UserService;

@Service
public class FanoutService {
    private final Map<UserTypeEnum, FanoutStrategy> fanoutStrategies = new EnumMap<>(UserTypeEnum.class);
    private final UserService userService;

    @Autowired
    public FanoutService(ApplicationContext applicationContext, UserService userService) {
        for(UserTypeEnum userType : UserTypeEnum.values()) {
            String beanName = userType.name();
            if(applicationContext.containsBean(beanName)) {
                fanoutStrategies.put(userType, (FanoutStrategy)applicationContext.getBean(beanName));
            }
        }
        this.userService = userService;
    }

    public void distributeToFollowers(Post post) {
        UserTypeEnum userTypeEnum = getUserType(post);

        FanoutStrategy fanoutStrategy = fanoutStrategies.get(userTypeEnum);
        fanoutStrategy.distributeToFollowers(post);
    }

    private UserTypeEnum getUserType(Post post) {
        return userService.getUserType(post.getUserId());
    }
}
