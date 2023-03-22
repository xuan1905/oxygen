package com.spring.oxygen.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pool")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping("/v1/update")
    public ResponseEntity<PoolSaveResponse> savePool(@RequestBody PoolSaveRequest  request){
    	PoolSaveResponse res = resourceService.savePool(request);
    	return ResponseEntity.ok(res);
    }

    @GetMapping("/v1/query/{id}")
    public int getTeacherDetails(@PathVariable("id") int id){
        return resourceService.getTeacherDetails(id);
    }
}
