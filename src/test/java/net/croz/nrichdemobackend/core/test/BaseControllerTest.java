package net.croz.nrichdemobackend.core.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
public abstract class BaseControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    protected ResultActions performFormPostRequest(String requestUrl) throws Exception {
        return performFormPostRequest(requestUrl, Collections.emptyMap());
    }

    protected ResultActions performFormPostRequest(String requestUrl, Map<String, String> requestData) throws Exception {
        return mockMvc.perform(post(requestUrl)
            .params(createFromMap(requestData))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performPostRequest(String requestUrl) throws Exception {
        return performPostRequest(requestUrl, Collections.emptyMap());
    }

    protected ResultActions performPostRequest(String requestUrl, Object requestData) throws Exception {
        return mockMvc.perform(post(requestUrl)
            .content(objectMapper.writeValueAsString(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performGetRequest(String requestUrl) throws Exception {
        return performGetRequest(requestUrl, Collections.emptyMap());
    }

    protected ResultActions performGetRequest(String requestUrl, Map<String, String> requestData) throws Exception {
        return mockMvc.perform(get(requestUrl)
            .params(createFromMap(requestData))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performMultipartRequest(String requestUrl, MockMultipartFile file) throws Exception {
        return mockMvc.perform(multipart(requestUrl)
            .file(file)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
    }

    private MultiValueMap<String, String> createFromMap(Map<String, String> requestData) {
        MultiValueMap<String, String> resultMap = new LinkedMultiValueMap<>();

        requestData.forEach((key, value) -> resultMap.put(key, List.of(value)));

        return resultMap;
    }
}
