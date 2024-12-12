//package com.vecnavelopers.dndbeyond.controller;
//
//import com.vecnavelopers.dndbeyond.model.ClassDetails;
//import com.vecnavelopers.dndbeyond.service.DndApiService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class DndApiController {
//
//    private final DndApiService dndApiService;
//
//    public DndApiController(DndApiService dndApiService) {
//        this.dndApiService = dndApiService;
//    }
//
//    @GetMapping("/class-details")
//    public ClassDetails getClassDetails(@RequestParam String className) {
//        return dndApiService.getClassDetails(className.toLowerCase());
//    }
//}
