package br.com.rh.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import br.com.rh.controller.HomeController;
/*
import br.com.rh.controller.HomeController;
import br.com.rh.dao.CadastroDAO;
import br.com.rh.interceptor.AutorizadorInterceptor;
import br.com.rh.pdf.ItextPdfView;

import br.com.rh.pdf.LowagiePdfView;

*/

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses={HomeController.class,/*CadastroDAO.class*/})
public class AppWebConfiguration  extends WebMvcConfigurerAdapter{
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		//resolver.setExposedContextBeanNames("carrinhoCompras");
		return resolver;
	}
	
	 @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	   }
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		//messageSource.setBasename("/WEB-INF/messages");
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService(){
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	 @Override
	    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	        configurer
	                .defaultContentType(MediaType.TEXT_HTML)
	                .parameterName("type")
	                .favorParameter(true)
	                .ignoreUnknownPathExtensions(false)
	                .ignoreAcceptHeader(false)
	                .useJaf(true);
	    }

	    @Override
	    public void configureViewResolvers(ViewResolverRegistry registry) {
	        registry.jsp("/WEB-INF/views/", ".jsp");
	        registry.enableContentNegotiation(
	               // new ItextPdfView()
	                // Use either ItextPdfView or LowagiePdfView
	                //new LowagiePdfView()
	        );
	    }
	 /*
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new AutorizadorInterceptor());
	    }
	    codigo de login para da tela 
	    
	    */
	
	
}
