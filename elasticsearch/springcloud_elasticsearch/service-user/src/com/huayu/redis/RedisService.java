package com.huayu.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void add(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

//    public void add(String key, List<Integer> users) {
//        stringRedisTemplate.
//    }

    public String get(String key) {
        String source = stringRedisTemplate.opsForValue().get(key);
        
        return source;
    }

//    public List<User> getUserList(String key) {
//        String source = stringRedisTemplate.opsForValue().get(key);
//        if (!StringUtils.isEmpty(source)) {
//            return new Gson().fromJson(source, new TypeToken<List<User>>() {
//            }.getType());
//        }
//        return null;
//    }

    public void delete(String key) {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }
}