package com.flab.stargram.domain.cache.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.flab.stargram.entity.dto.PostRequestDto;
import com.flab.stargram.entity.model.Post;
import com.flab.stargram.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
class RedisCacheServiceTest {


    @Test
    @DisplayName("Redis에 인플루언서의 게시물을 캐시에 저장하고 조회한다")
    void cachePostForInfluencer() {
        // Given
        PostRequestDto dto = PostRequestDto.builder()
            .userId(1L)
            .content("REDIS 테스트 content")
            .build();
        Post post = Post.writePostOf(dto);

        String key = "Influencer";
    }
}