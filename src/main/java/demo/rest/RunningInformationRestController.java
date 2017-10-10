package demo.rest;

import demo.domain.RunningInformation;
import demo.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunningInformationRestController {
    private RunningInformationService runningInformationService;

    @Autowired
    public RunningInformationRestController(RunningInformationService runningInformationService) {
        this.runningInformationService = runningInformationService;
    }

    @RequestMapping(value = "/runningInfo", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED) //status code:201
    public void upload(@RequestBody List<RunningInformation> runningInformations) {
        this.runningInformationService.saveRunningInformation(runningInformations);
    }

    @RequestMapping(value = "/runningInfo/{runningId}", method = RequestMethod.DELETE)
    public void purge(@PathVariable String runningId) {
        this.runningInformationService.deleteByRunningId(runningId);
    }

    @RequestMapping(value = "/runningInfo", method = RequestMethod.GET)
    public Page<RunningInformation> findAllByOrderByHealthWarningLevel(
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "2") int size) {
        //Sort sort = new Sort(Sort.Direction.DESC, "healthWarningLevel");
        Pageable pageable = new PageRequest(page, size);
        return this.runningInformationService.findAllByOrderByHealthWarningLevel(pageable);
    }
}
