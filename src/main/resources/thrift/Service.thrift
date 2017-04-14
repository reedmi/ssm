namespace java com.ssm.thrift.service

include "Bean.thrift"

service ThriftService {
    bool send(1:Bean.ThriftBean bean),
}