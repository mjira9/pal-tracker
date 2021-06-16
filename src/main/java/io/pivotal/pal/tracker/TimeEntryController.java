package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.timeEntriesRepo = timeEntriesRepo;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return ResponseEntity.created(null)
                .body(timeEntriesRepo.create(timeEntryToCreate));
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        var timeEntryFound = timeEntriesRepo.find(timeEntryId);
        return timeEntryFound == null ? notFound().build() : ok(timeEntryFound);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = this.timeEntriesRepo.list();
        return ResponseEntity.ok(timeEntries);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        var timeEntryUpdated = timeEntriesRepo.update(timeEntryId, timeEntryToUpdate);

        return timeEntryUpdated == null ? notFound().build() : ok(timeEntryUpdated);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntriesRepo.delete(timeEntryId);
        return noContent().build();
    }
}
