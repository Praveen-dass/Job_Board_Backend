package com.example.demo.Controller.Skills;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Skills.FormData;
import com.example.demo.Service.Skills.FormDataService;

@RestController
@RequestMapping("/api/formData")
public class FormDataController {
    @Autowired
    private FormDataService formDataService;

    @PostMapping
    public FormData postFormData(@RequestBody FormData formData) {
        return formDataService.postData(formData);
    }

    @GetMapping
    public List<FormData> getFormData() {
        return formDataService.getData();
    }
}
