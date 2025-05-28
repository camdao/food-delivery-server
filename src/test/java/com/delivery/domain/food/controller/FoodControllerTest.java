package com.delivery.domain.food.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.delivery.domain.food.api.FoodController;
import com.delivery.domain.food.application.FoodService;
import com.delivery.domain.food.domain.FoodStatus;
import com.delivery.domain.food.dto.request.FoodCreateRequest;
import com.delivery.domain.food.dto.response.FoodCreateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(FoodController.class)
@AutoConfigureMockMvc(addFilters = false)
public class FoodControllerTest {
    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    @MockitoBean private FoodService foodService;

    @Test
    void create_food() throws Exception {
        // given
        FoodCreateRequest createRequest = new FoodCreateRequest("name", 1L);

        given(foodService.createFood(any()))
                .willReturn(new FoodCreateResponse(1L, "name", 1L, FoodStatus.AVAILABLE));

        // when, then
        ResultActions perform =
                mockMvc.perform(
                        post("/foods")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                                .content(objectMapper.writeValueAsString(createRequest)));
    }
}
