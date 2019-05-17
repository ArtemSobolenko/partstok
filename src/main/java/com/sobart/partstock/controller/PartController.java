package com.sobart.partstock.controller;

import com.sobart.partstock.domain.Part;
import com.sobart.partstock.domain.User;
import com.sobart.partstock.repository.PartRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class PartController {

    private PartRepository partRepository;

    public PartController(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Value("${upload.path}")
    private String uploadPath;

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
            @RequestParam String amount, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        Part part = new Part(name, need, Long.parseLong(amount), user);


        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();

            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            part.setFilename(resultFileName);
        }

        partRepository.save(part);

        Iterable<Part> parts = partRepository.findAll();

        model.put("parts", parts);

        return "part";
    }

}
