package springdata.technest22.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import springdata.technest22.model.Product;

import java.time.Duration;

@Configuration
public class RedisConfiguration {

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private Integer port;


    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
    @Bean
    public CacheManager cacheManager(){
        RedisCacheConfiguration days4 = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(1));
        return RedisCacheManager.builder(lettuceConnectionFactory())
                .withCacheConfiguration("student",days4)
                .build();
    }

    @Bean
    public RedisTemplate<Long, Product> longObjectRedisTemplate(){
        final RedisTemplate<Long, Product> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory());
        var jacksonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setKeySerializer(jacksonRedisSerializer);
        template.setValueSerializer(jacksonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}


