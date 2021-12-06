package com.hjl.Service.impl;

import com.hjl.Service.UserService;
import com.hjl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    LoadBalancerClient loadBalancerClient;
    public String getServerUrl(){
//        List<ServiceInstance> instanceList = discoveryClient.getInstances("Userprovider001");
        //获取第一个服务
//        ServiceInstance serviceInstance = instanceList.get(0);
        //获取远程服务的ip和接口
//        String ip = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
        ServiceInstance serviceInstance = loadBalancerClient.choose("Userprovider");
        String ip = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url="http://"+ip+":"+port+"/user";
        return url;
    }

    @Override
    public Map getUserMap() {
        Map map = restTemplate.getForObject(getServerUrl() + "/getAll", Map.class);
        return map;
    }

    @Override
    public void createUser(User user) {
        restTemplate.postForObject(getServerUrl()+"/save",user,String.class);
    }

    @Override
    public User getUser(Long id) {
        User user = restTemplate.getForObject(getServerUrl() + "/get/"+id,User.class);
        return user;
    }

    @Override
    public void updateUser(Long id, User user) {
        restTemplate.put(getServerUrl()+"/update/"+id,user);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.delete(getServerUrl()+"/delete/"+id);
    }
}
