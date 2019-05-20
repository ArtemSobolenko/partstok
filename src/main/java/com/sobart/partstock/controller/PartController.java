package com.sobart.partstock.controller;

import com.sobart.partstock.domain.Part;
import com.sobart.partstock.domain.User;
import com.sobart.partstock.repository.PartRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
    public String main(@RequestParam(required = false, defaultValue = "all") String filter,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                       Model model) {
        Page<Part> page;

        if (filter.equals("all")) {
            page = partRepository.findAll(pageable);
        } else {
            page = partRepository.findByNeed(Boolean.parseBoolean(filter), pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/part");
        model.addAttribute("filter", filter);

        return "part";
    }

    @PostMapping("/part")
    public String add(
            @AuthenticationPrincipal User user,
    //        @Valid Part part,
            @RequestParam String name,
            @RequestParam(value = "need", required = false) boolean need,
            @RequestParam String amount,
//            BindingResult bindingResult,
//            Model model,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
     //   part.setOwner(user);

           Part part = new Part(name, need, Long.parseLong(amount), user);

//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//
//            model.mergeAttributes(errorsMap);
//
//            model.addAttribute("part", part);
//        } else {

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
   //     }

        Iterable<Part> parts = partRepository.findAll();

          model.put("parts", parts);
    //    model.addAttribute("parts", parts);

        return "part";
    }

}
