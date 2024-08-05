/*package com.example.demo.Service.Skills;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Skills.FormData;
import com.example.demo.Repository.Skills.FormDataRepository;

@Service
public class FormDataService {
    @Autowired
    private FormDataRepository formDataRepository;

    public FormData postData(FormData formData) {
        return formDataRepository.save(formData);
    }

    public List<FormData> getData() {
        return formDataRepository.findAll();
    }
}
*/
package com.example.demo.Service.Skills;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Skills.FormData;
import com.example.demo.Repository.Skills.FormDataRepository;

@Service
public class FormDataService {
    @Autowired
    private FormDataRepository formDataRepository;

    public FormData postData(FormData formData) {
        return formDataRepository.save(formData);
    }

    public List<FormData> getData() {
        return formDataRepository.findAll();
    }
}
