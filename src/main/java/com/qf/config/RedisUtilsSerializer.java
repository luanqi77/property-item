package com.qf.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class RedisUtilsSerializer implements RedisSerializer {

    //获得序列转换器
    Converter<Object, byte[]> serializingConverter =new SerializingConverter();
    //获取反序列化转换器
    Converter<byte[], Object> deserializingConverter = new DeserializingConverter();
    //声明一个数组常量
    private static final byte[] EMPTY_BYTE_ARRAY  = new byte[0];
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o==null){
            return EMPTY_BYTE_ARRAY;
        }
        return serializingConverter.convert(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes==null){
            return null;
        }
        return deserializingConverter.convert(bytes);
    }
}
