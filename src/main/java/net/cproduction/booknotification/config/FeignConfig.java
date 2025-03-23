package net.cproduction.booknotification.config;

import feign.RequestInterceptor;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.cproduction.booknotification.exception.NotFoundException;
import net.cproduction.booknotification.exception.InternalServerException;

@Configuration
public class FeignConfig {
    @Value("${naver.api.client.id}")
    private String clientId;

    @Value("${naver.api.client.secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", clientId);
            requestTemplate.header("X-Naver-Client-Secret", clientSecret);
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    class CustomErrorDecoder implements ErrorDecoder {
        @Override
        public Exception decode(String s, Response response) {
            switch (response.status()) {
                case 400:
                    return new BadRequestException("잘못된 요청입니다.");
                case 404:
                    return new NotFoundException("리소스를 찾을 수 없습니다.");
                case 500:
                    return new InternalServerException("서버 오류가 발생했습니다.");
                default:
                    return new Exception("API 호출 중 오류가 발생했습니다.");
            }
        }
    }
}
