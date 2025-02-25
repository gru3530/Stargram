package com.flab.stargram.domain.cache.Service;

import com.flab.stargram.entity.model.Post;

public interface CacheService {
    void cachePostForInfluencer(Post post);
}
