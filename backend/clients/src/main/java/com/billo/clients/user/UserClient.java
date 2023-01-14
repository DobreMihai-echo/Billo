package com.billo.clients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("user")
public interface UserClient {

//    @GetMapping(path = "api/v1/user/{id}")
//    HttpResponse exampleResponse();
}
