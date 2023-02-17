package org.fisheep.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fisheep.chat.entity.GptRequest;
import org.fisheep.chat.entity.GptResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/gpt")
public class GptResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GptResponse generateMessage(GptRequest request) throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        Response response = client.target("https://api.openai.com/v1/engine/davinci-codex/completions")
                .request(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer API_KEY") // 替换为您的 ChatGPT API 密钥
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));
        String message = response.readEntity(String.class);

        ObjectMapper mapper = new ObjectMapper();
        GptResponse gptResponse = mapper.readValue(message, GptResponse.class);

        return gptResponse;
    }
}
