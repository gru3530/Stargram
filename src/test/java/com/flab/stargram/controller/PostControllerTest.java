package com.flab.stargram.controller;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import com.flab.stargram.entity.dto.PostRequestDto;
import com.flab.stargram.entity.model.Post;
import com.flab.stargram.service.PostService;

@TestConfiguration
class PostControllerTestConfig{
    @Bean
    public PostService postService(){
        return mock(PostService.class);
    }
}

@WebMvcTest(controllers = PostController.class)
@Import(PostControllerTestConfig.class)
class PostControllerTest extends BaseMockMvcTest {
    @Autowired
    private PostService postService;

    @DisplayName("Post Api 게시물 생성을 수행한다")
    @Test
    void createPost() throws Exception {
        // Given
        PostRequestDto requestDto = PostRequestDto.builder()
                .userId(1L)
                .content("test Content")
                .build();

        Post mockPost = Post.writePostOf(requestDto);
        when(postService.postFeed(any(PostRequestDto.class))).thenReturn(mockPost);


        // When & Then
        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("userId").description("게시자 ID"),
                    fieldWithPath("content").description("게시물 내용")
                ),
                responseFields(
                    fieldWithPath("code").description("요청 성공 여부"),
                    fieldWithPath("data.postId").description("게시물 ID"),
                    fieldWithPath("data.userId").description("게시자 ID"),
                    fieldWithPath("data.content").description("게시물 내용")
                )
            ));
    }
}