package org.tpokora.blog.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tpokora.blog.model.Entry;

import java.util.Arrays;
import java.util.List;

@Profile("!nodb")
@Api(value = "Blog Controller", description = "Blog API")
@RestController("/api/blogs")
public class BlogController {

    @ApiOperation(value = "Get blog entries", notes = "Returns list of blog entries")
    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    public ResponseEntity<List<Entry>> getAllEntries() {
        Entry entry1 = new Entry("title1", "content");
        Entry entry2 = new Entry("title2", "content");
        List<Entry> entries = Arrays.asList(entry1, entry2);
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }
}
