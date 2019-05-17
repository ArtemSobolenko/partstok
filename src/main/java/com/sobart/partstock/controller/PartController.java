package com.sobart.partstock.controller;

import com.sobart.partstock.domain.Part;
import com.sobart.partstock.domain.User;
import com.sobart.partstock.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PartController {

    @Autowired
    private PartRepository partRepository;

    @GetMapping("/home")
    public String home(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/part")
    public String main(@RequestParam(required = false, defaultValue = "all") String filter, Model model) {
        Iterable<Part> parts = partRepository.findAll();

        if (filter.equals("all")) {
            parts = partRepository.findAll();
        } else {
            parts = partRepository.findByNeed(Boolean.parseBoolean(filter));
        }

        model.addAttribute("parts", parts);
        model.addAttribute("filter", filter);

        return "part";
    }

    @PostMapping("/part")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam(value = "need", required = false) boolean need,
            @RequestParam String amount, Map<String, Object> model) {
        partRepository.save(new Part(name, need, Long.parseLong(amount), user));

        Iterable<Part> parts = partRepository.findAll();

        model.put("parts", parts);

        return "part";
    }

//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model) {
//
//        Iterable<Part> parts;
//
//        if (filter.equals("all")) {
//            parts = partRepository.findAll();
//        } else {
//            parts = partRepository.findByNeed(Boolean.parseBoolean(filter));
//        }
//
//        model.put("parts", parts);
//
//        return "part";
//    }

}
