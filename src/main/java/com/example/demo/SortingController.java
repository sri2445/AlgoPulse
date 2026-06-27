package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") 
@SuppressWarnings("unchecked")
public class SortingController {

    private final SortingService sortingService = new SortingService();

    @PostMapping("/sort")
    public List<SortingStep> getSortingSteps(@RequestBody Map<String, Object> payload) {
        List<Integer> list = (List<Integer>) payload.get("array");
        String algo = (String) payload.get("algorithm");
        
        int[] array = list.stream().mapToInt(i -> i).toArray();

        if ("selection".equalsIgnoreCase(algo)) {
            return sortingService.selectionSort(array);
        } else if ("insertion".equalsIgnoreCase(algo)) {
            return sortingService.insertionSort(array);
        } else if ("merge".equalsIgnoreCase(algo)) {
            return sortingService.mergeSort(array);
        }
        return sortingService.bubbleSort(array);
    }
}
