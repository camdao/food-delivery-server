package com.delivery.domain.food.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.HttpStatus;
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
        FoodCreateRequest createRequest = new FoodCreateRequest("name", "describe");

        given(foodService.createFood(any()))
                .willReturn(new FoodCreateResponse(1L, "name", "describe", FoodStatus.AVAILABLE));

        // when, then
        ResultActions perform =
                mockMvc.perform(
                        post("/foods")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                                .content(objectMapper.writeValueAsString(createRequest)));

        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.name").exists())
                .andExpect(jsonPath("$.data.describe").exists())
                .andDo(print());
    }

    @Test
    void food_creation_name_cannot_be_null() throws Exception {
        // given
        FoodCreateRequest createRequest = new FoodCreateRequest(null, "describe");

        // when, then
        ResultActions perform =
                mockMvc.perform(
                        post("/foods")
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                                .content(objectMapper.writeValueAsString(createRequest)));

        perform.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(
                        jsonPath("$.data.errorClassName").value("MethodArgumentNotValidException"))
                .andDo(print());
    }
}
