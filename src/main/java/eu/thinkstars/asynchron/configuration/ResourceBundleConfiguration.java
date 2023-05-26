package eu.thinkstars.asynchron.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;

@Configuration
public class ResourceBundleConfiguration {

	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/labels");
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());

		return messageSource;
	}
}
