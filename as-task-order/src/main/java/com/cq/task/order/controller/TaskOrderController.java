package com.cq.task.order.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("taskOrder")
public class TaskOrderController {

    @GetMapping("order")
    public String order () {
        return "this is order!";
    }
}
