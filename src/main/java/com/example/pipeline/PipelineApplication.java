package com.example.pipeline;

//import com.hazelcast.client.config.ClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableCaching
public class PipelineApplication {
	private static final String CONSTANT = "constant";
	public static void main(String[] args) {
		SpringApplication.run(PipelineApplication.class, args);
	}

	/**@Bean
	public ClientConfig hazelcastClientConfig() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getNetworkConfig().addAddress("hazelcast");
		return clientConfig;
	}**/
}
