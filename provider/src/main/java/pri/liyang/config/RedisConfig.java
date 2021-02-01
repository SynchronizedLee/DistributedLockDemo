package pri.liyang.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RedisConfig {

    /**
     * 启动三台Redis来做分布式锁（红锁）
     * 注：最少可以只有1台，建议奇数台
     */
    @Bean(name = "redissonRed6379")
    @Primary
    public RedissonClient redissonRed6379() {
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379").setDatabase(0);
        return Redisson.create(config);
    }

    @Bean(name = "redissonRed6380")
    public RedissonClient redissonRed6380() {
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6380").setDatabase(0);
        return Redisson.create(config);
    }

    @Bean(name = "redissonRed6381")
    public RedissonClient redissonRed6381() {
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6381").setDatabase(0);
        return Redisson.create(config);
    }

}
