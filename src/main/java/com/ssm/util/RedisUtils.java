package com.ssm.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by bobby.peng on 2016/6/29.
 */
@Component
public class RedisUtils {

    public static final int DEFAULT_INCREMENT = 1;

    private static final int ZSET_DEFAULT_START_INDEX = 0;
    private static final int ZSET_DEFAULT_END_INDEX = -1;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public boolean checkKeyExists(String key) {
        return redisTemplate.hasKey(key);
    }

    public List<Object> checkKeysExists(final List<String> keys) {
        final List<Object> keyExists = redisTemplate.execute(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();

                for (String key : keys) {
                    byte[] keyByte = rawKey(key);

                    connection.exists(keyByte);
                }

                return connection.closePipeline();
            }
        });

        return keyExists;
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public String getValueByKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List<String> multiGetValueByKey(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public void setValueByKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void multiSetValuesByKeys(Map<String, String> keyValueMap) {
        redisTemplate.opsForValue().multiSet(keyValueMap);
    }

    public void incrementValueByKey(String key) {
        redisTemplate.opsForValue().increment(key, DEFAULT_INCREMENT);
    }

    public void incrementValueByKey(String key, double value) {
        redisTemplate.opsForValue().increment(key, value);
    }

    public Map<String, String> getAllHashValueByKey(String key) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    public String getHashValueByKeyAndField(String key, String field) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, field);
    }

    public Map<String, String> getHashValueMapByKey(String key) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        return hashOperations.entries(key);
    }

    public List<String> getHashValuesByKeyAndFields(String key, List<String> fields) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        return hashOperations.multiGet(key, fields);
    }

    public void incrHashValueByKeyAndField(String key, String field) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.increment(key, field, DEFAULT_INCREMENT);
    }

    public void putHashValueByKeyAndField(String key, String field, String value) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, field, value);
    }

    public void deleteHashValueByKeyAndField(String key, String field) {
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, field);
    }

    public void deleteHashValueByKeyAndFields(String key, List<String> fields) {
        if (null == fields || fields.isEmpty()) {
            return;
        }
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, fields.toArray());
    }

    public void addZSetValue(String key, String member, long score) {
        redisTemplate.opsForZSet().add(key, member, score);
    }

    public Double getZSetScoreByKeyAndMember(String key, String member) {
        return redisTemplate.opsForZSet().score(key, member);
    }

    public void deleteZSetMember(String key, String member) {
        redisTemplate.opsForZSet().remove(key, member);
    }

    public boolean checkZSetMemberExists(String key, String member) {
        if (getZSetRank(key, member) != null) {
            return true;
        }
        return false;
    }

    public Long getZSetRank(String key, String member) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.rank(key, member);
    }

    public List<Integer> getZSetTotalCountByKeys(final List<String> keys) {
        final List<Object> countObjs = redisTemplate.execute(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for(String key : keys) {
                    byte[] keyByte = rawKey(key);
                    connection.zCard(keyByte);
                }

                return connection.closePipeline();
            }
        });

        List<Integer> counts = new ArrayList<Integer>(countObjs.size());

        for(Object count : countObjs) {
            counts.add(((Long)count).intValue());
        }

        return counts;
    }

    public void zaddScoreByKeyAndMember(String key, Object member, long score) {
        redisTemplate.boundZSetOps(key).add(member.toString(), score);
    }

    public Set<String> getZsetMembersByScore(String key, int start, int end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple<String>> getZSetMembersAndScoreOrderByScoreDesc(String key) {
        return getZSetMembersAndScoreOrderByScoreDesc(key, ZSET_DEFAULT_START_INDEX, ZSET_DEFAULT_END_INDEX);
    }

    public Set<ZSetOperations.TypedTuple<String>> getZSetMembersAndScoreOrderByScoreDesc(String key, int startIndex, int endIndex) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(
                key, startIndex, endIndex);
    }

    public Set<String> getReverseZsetMembersAndScoreByScoreRange(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    public List<Double> getZsetTopRankScoreByKeys(final List<String> keys) {
        final List<Object> zsets = redisTemplate.execute(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();

                for (String key : keys) {
                    byte[] keyByte = rawKey(key);
                    connection.zRevRangeWithScores(keyByte, 0, 0);
                }

                return connection.closePipeline();
            }
        });
        List<Double> scores = new ArrayList<Double>();

        for (Object zset : zsets) {
            if (((Set<RedisZSetCommands.Tuple>) zset).isEmpty()) {
                scores.add(0d);
                continue;
            }

            for (RedisZSetCommands.Tuple tuple : (Set<RedisZSetCommands.Tuple>) zset) {
                scores.add(tuple.getScore());
            }
        }

        return scores;
    }

    public void addZSetValues(String key, Set<ZSetOperations.TypedTuple<String>> values) {
        redisTemplate.opsForZSet().add(key, values);
    }

    public void removeZSetMember(final Map<String, String[]> keyAndMembersMap) {
        redisTemplate.execute(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();

                for (String key : keyAndMembersMap.keySet()) {

                    String[] members = keyAndMembersMap.get(key);
                    connection.zRem(rawKey(key), rawValues(members));
                }

                return connection.closePipeline();
            }
        });
    }

    public void moveZSetValuesBeyondKeys(final List<Map<String, String>> sourceAndTargetZSetKeysAndMembers) {
        final RedisSerializer keySerializer = redisTemplate.getKeySerializer();
        final RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
        final List scores = redisTemplate.execute(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();

                for (Map<String, String> map : sourceAndTargetZSetKeysAndMembers) {
                    byte[] keyByte = keySerializer.serialize(map.get("sourceKey"));
                    byte[] valueByte = valueSerializer.serialize(map.get("sourceMember"));

                    connection.zScore(keyByte, valueByte);
                }

                return connection.closePipeline();
            }
        });

        redisTemplate.execute(new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();

                int i = 0;
                for (Map<String, String> map : sourceAndTargetZSetKeysAndMembers) {

                    Object scoreObj = scores.get(i++);

                    if (scoreObj != null) {
                        Double score = Double.parseDouble(scoreObj.toString());

                        byte[] sourceKeyByte = keySerializer.serialize(map.get("sourceKey"));
                        byte[] sourceValueByte = keySerializer.serialize(map.get("sourceMember"));
                        String targetKey = map.get("targetKey");
                        if (!StringUtils.isEmpty(targetKey)) {
                            byte[] targetKeyByte = keySerializer.serialize(targetKey);
                            byte[] targetValueByte = keySerializer.serialize(map.get("targetMember"));
                            connection.zAdd(targetKeyByte, score, targetValueByte);
                        }

                        connection.zRem(sourceKeyByte, sourceValueByte);
                    }
                }

                return connection.closePipeline();
            }
        });
    }

    public void flushDb() {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return true;
            }
        });
    }

    public void setKeyExpiredTime(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
    }

    public void addSetValue(String key, String val) {
        redisTemplate.boundSetOps(key).add(val);
    }

    public Set<String> getSetValue(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    public Long getSetSize(String key) {
        return redisTemplate.boundSetOps(key).size();
    }

    public Boolean checkSetMemberExists(String key, String val) {
        return redisTemplate.boundSetOps(key).isMember(val);
    }

    private String keyFormat(String key, String date, Object... args) {
        Object[] objects = new Object[args.length + 1];
        for (int i = 0; i < objects.length - 1; i++) {
            objects[i] = args[i];
        }
        objects[objects.length - 1] = date;

        return String.format(key, objects);
    }

    private byte[] rawKey(String key) {
        return ((StringRedisSerializer) redisTemplate.getKeySerializer()).serialize(key);
    }

    private byte[][] rawValues(String... values) {
        final byte[][] rawValues = new byte[values.length][];
        int i = 0;
        for (String value : values) {
            rawValues[i++] = rawValue(value);
        }
        return rawValues;
    }

    private byte[] rawValue(String value) {
        return ((StringRedisSerializer) redisTemplate.getValueSerializer()).serialize(value);
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
