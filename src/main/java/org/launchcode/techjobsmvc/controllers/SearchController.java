package org.launchcode.techjobsmvc.controllers;

import jdk.jfr.Event;
import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    private static List<Job> searchResults = new ArrayList<>();

    @GetMapping (value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping (value = "/search/results")
    @ResponseBody
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        searchResults = JobData.findByColumnAndValue(searchType, searchTerm);

        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        //model.addAttribute("searchResults", searchResults);

        if ((searchTerm == "all".toLowerCase()) || searchTerm == "") {
            String.valueOf(JobData.findAll());
        } else {
            String.valueOf(searchResults);
        }
       return "redirect:/search/results";
    }
}

