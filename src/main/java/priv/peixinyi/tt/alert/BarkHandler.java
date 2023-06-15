package priv.peixinyi.tt.alert;

import cn.hutool.http.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author peixinyi
 */
@Slf4j
public class BarkHandler {

    private static final String BARK_URL = "https://api.day.app/%s/%s";

    public static String getBarkUrl(String deviceToken, String content) {
        return String.format(BARK_URL, deviceToken, content);
    }

    public static void sendBark(String deviceToken, String content) {
        OkHttpClient client = new OkHttpClient();
        String barkUrl = getBarkUrl(deviceToken, content);
        Request request = new Request.Builder()
                .url(barkUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.warn("Bark发送失败!");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                String body = response.body().string();
                BarkResponse barkResponse = objectMapper.readValue(body, BarkResponse.class);
                if (barkResponse.getCode() != 200) {
                    log.info("Bark发送失败:{}", barkResponse.getMessage());
                }
            }
        });
    }

}
