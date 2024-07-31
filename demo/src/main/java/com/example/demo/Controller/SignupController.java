package com.example.demo.Controller;

import com.example.demo.Entity.Signup;
import com.example.demo.Service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SignupController {
    @Autowired
    private SignupService signService;
    @GetMapping("/get")
    public List<Signup> getmethod()
    {
        return signService.list();
    }

    @GetMapping("/admin/get/{id}")
    public Optional<Signup> getById(@PathVariable String id)
    {
        return signService.getId((id));
    }

    @PostMapping("/admin/post")
    public void postSign(@RequestBody Signup sign)
    {
        signService.postmethod(sign);
    }
    @PutMapping("/admin/put/{id}")
    public Signup putSign(@RequestBody Signup sign ,@PathVariable String id)
    {
        return signService.putmethod(sign,id);
    }
    @DeleteMapping("/admin/del/{id}")
    public void deleteIdSign(@PathVariable String id)
    {
        signService.deldata(id);
    }
}
