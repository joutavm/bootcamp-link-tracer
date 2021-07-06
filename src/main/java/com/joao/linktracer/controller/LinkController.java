package com.joao.linktracer.controller;

import com.joao.linktracer.config.exceptions.InvalidLinkException;
import com.joao.linktracer.controller.form.LinkForm;
import com.joao.linktracer.models.Link;
import com.joao.linktracer.models.Status;
import com.joao.linktracer.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class LinkController {

    @Autowired
    LinkRepository linkRepository;

    @GetMapping("/link")
    public List<Link> getAll(){
        return linkRepository.findAll();
    }

    @RequestMapping("/link/{id}")
    @Transactional
    public RedirectView redirec(@PathVariable Long id, HttpServletResponse response) {
        Optional<Link> optionalLink = linkRepository.findById(id);
        if(optionalLink.isEmpty())
            throw new NoSuchElementException();
        Link link = optionalLink.get();
        if (link.getStatus() == Status.INVALIDO)
            throw new InvalidLinkException("Invalid link");
        return new RedirectView(optionalLink.get().openLink());
    }


    @PostMapping("/link")
    @Transactional
    public ResponseEntity<Link> add(@RequestBody @Validated LinkForm form, UriComponentsBuilder uriBuilder){
        Link link = form.convert();
        linkRepository.save(link);

        URI uri = uriBuilder.path("/link/{id}").buildAndExpand(link).toUri();
        return ResponseEntity.created(uri).body(link);
    }

    @Transactional
    @GetMapping("/invalidate/{id}")
    public ResponseEntity<Link> invalidate(@PathVariable Long id){
        Optional<Link> optionalLink = linkRepository.findById(id);
        if(optionalLink.isEmpty())
            return ResponseEntity.notFound().build();

        Link link = optionalLink.get();
        link.setStatus(Status.INVALIDO);

        return ResponseEntity.ok(link);

    }




}
