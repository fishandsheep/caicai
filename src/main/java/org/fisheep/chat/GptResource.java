package org.fisheep.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.fisheep.chat.entity.Choice;
import org.fisheep.chat.entity.GptRequest;
import org.fisheep.chat.entity.GptResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/gpt")
public class GptResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String generateMessage(GptRequest request) throws JsonProcessingException {
        Client client = ClientBuilder.newClient();
        //{"model": "text-davinci-003", "prompt": "Say this is a test", "temperature": 0, "max_tokens": 7}
        request.setModel("text-davinci-003");
        request.setTemperature(0);
        request.setMax_tokens(2048);
        client.property("jersey.config.client.connectTimeout", 60 * 1000L);
        client.property("jersey.config.client.readTimeout", 60 * 1000L);

        Response response = client.target("https://api.openai.com/v1/completions")
                .request(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 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") // 替换为您的 ChatGPT API 密钥
                .post(Entity.entity(request, MediaType.APPLICATION_JSON));
        StringBuffer responseTxt = new StringBuffer();
        try {
            GptResponse gptResponse = response.readEntity(GptResponse.class);
            List<Choice> choices = gptResponse.getChoices();
            for (Choice choice : choices) {
                responseTxt.append(choice.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseTxt.append("未知错误,\uD83D\uDE33");
        }
        //TODO 添加表情
        return responseTxt.toString();
    }
}
