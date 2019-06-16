package org.tpokora.blog.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.blog.model.Entry;

import java.util.Arrays;
import java.util.List;

@RestController
public class BlogController {

    @RequestMapping("/blog")
    public ResponseEntity<List<Entry>> getAllEntries() {
        Entry entry1 = new Entry("title1", "content");
        Entry entry2 = new Entry("title2", "content");
        List<Entry> entries = Arrays.asList(entry1, entry2);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }
}
