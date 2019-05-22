package com.sobart.partstock.controller;

import com.sobart.partstock.domain.Part;
import com.sobart.partstock.domain.User;
import com.sobart.partstock.repository.PartRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.math.BigDecimal;
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
            @Valid Part part,

            BindingResult bindingResult,

            @RequestParam(required = false, defaultValue = "all") String filter,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,

            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        part.setOwner(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("part", part);

        } else {
            fileAdd(file, part);

            model.addAttribute("part", null);

            partRepository.save(part);
        }


        Page<Part> page;

        if (filter.equals("all")) {
            page = partRepository.findAll(pageable);
        } else {
            page = partRepository.findByNeed(Boolean.parseBoolean(filter), pageable);
        }

        model.addAttribute("page", page);

        Iterable<Part> parts = partRepository.findAll();

        model.addAttribute("url", "/part");
        model.addAttribute("parts", parts);

        return "part";
    }

    @GetMapping("/parts/partEdit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editPart() {
        return "/parts/partEdit";
    }

    @PostMapping("/parts/partEdit")
    public String edit(
            @RequestParam String partId,
            @RequestParam String name,
            @RequestParam(value = "need", required = false) boolean need,
            @RequestParam String amount,
            @RequestParam String price,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Part part = partRepository.findById(Long.parseLong(partId));
        part.setName(name);
        part.setNeed(need);
        part.setAmount(Long.parseLong(amount));
        double d = Double.valueOf(price);
        part.setPrice(new BigDecimal(d));
        fileAdd(file, part);
        partRepository.save(part);
        return "redirect:/part";
    }

    @GetMapping("/parts/partDelete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String delete(@RequestParam String partId) {
        partRepository.delete(partRepository.findById(Long.parseLong(partId)));
        return "redirect:/part";
    }

    @GetMapping("search")
    public String search(@RequestParam String search,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                         Model model) {
        Page<Part> page;

        page = partRepository.findByNameIsContaining(search.trim(), pageable);

        model.addAttribute("page", page);
        model.addAttribute("url", "/part");
        return "part";
    }

    private void fileAdd(@RequestParam("file") MultipartFile file, Part part) throws IOException {
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
    }
}
