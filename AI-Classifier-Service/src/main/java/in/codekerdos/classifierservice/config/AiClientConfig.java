package in.codekerdos.classifierservice.config;

import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration 
public class AiClientConfig { //This class is to set Resilient4j timeout 

    @Bean
    public RestClientCustomizer groqClientCustomizer(){
        return 
         builder->{
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(3_000); //Allows up to 3 seconds to establish a connection. & In Java, underscores (_) are used in numeric literals to improve readability. They don't change the actual numeric value.
            factory.setReadTimeout(8_000); //Allows up to 8 seconds waiting for response data.
            builder.requestFactory(factory);
         };
    }

    //RestClientCustomizer: Allows you to customize Spring's RestClient.Builder.
    //SimpleClientHttpRequestFactory: Creates the underlying HTTP request factory.
    
}
