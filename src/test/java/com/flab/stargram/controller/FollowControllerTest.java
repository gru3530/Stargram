package com.flab.stargram.controller;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.stargram.entity.dto.FollowDto;
import com.flab.stargram.service.FollowService;

@TestConfiguration
class FollowControllerTestConfig {

    @Bean
    public FollowService followService() {
        return mock(FollowService.class);
    }
}

@WebMvcTest(controllers = FollowController.class)
@Import(FollowControllerTestConfig.class)
class FollowControllerTest extends BaseMockMvcTest{
    @Autowired
    private FollowService followService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void followUser() throws Exception {
        // Given
        FollowDto followDto = FollowDto.builder()
            .followingId(1L)
            .followerId(2L)
            .build();

        doNothing().when(followService).followUser(any(FollowDto.class));

        // When & Then
        mockMvc.perform(post("/users/follow")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(followDto)))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("followingId").description("팔로우하는 사용자 ID"),
                    fieldWithPath("followerId").description("팔로우를 받는 사용자 ID")
                ),
                responseFields(
                    fieldWithPath("code").description("요청 성공 여부")
                )
            ));
    }

    @Test
    void unfollowUser() throws Exception {
        // Given
        FollowDto followDto = FollowDto.builder()
            .followingId(1L)
            .followerId(2L)
            .build();

        doNothing().when(followService).unfollowUser(any(FollowDto.class));

        // When & Then
        mockMvc.perform(post("/users/unfollow")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(followDto)))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("followingId").description("언팔로우하는 사용자 ID"),
                    fieldWithPath("followerId").description("언팔로우를 받는 사용자 ID")
                ),
                responseFields(
                    fieldWithPath("code").description("요청 성공 여부")
                )
            ));
    }

    @Test
    void getFollowers() throws Exception {
        // Given
        Long userId = 1L;
        List<Long> followerIds = List.of(2L, 3L, 4L);

        when(followService.getFollowerIds(userId)).thenReturn(followerIds);

        // When & Then
        mockMvc.perform(get("/users/{inputUserId}/followers", userId))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                pathParameters(
                    parameterWithName("inputUserId").description("팔로워 목록을 조회할 사용자 ID")
                ),
                responseFields(
                    fieldWithPath("code").description("요청 성공 여부"),
                    fieldWithPath("data").description("팔로워 ID 리스트").type(JsonFieldType.ARRAY)
                )
            ));
    }

}