package in.hexcod.dayapp.controllers.api;

import in.hexcod.dayapp.models.ApiResponse;
import in.hexcod.dayapp.models.EntryRequest;
import in.hexcod.dayapp.models.LedgerEntry;
import in.hexcod.dayapp.models.SummaryEntry;
import in.hexcod.dayapp.services.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by sidhant on 17-05-2016.
 */
@RestController
@RequestMapping(value = "/ledger")
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public ApiResponse<LedgerEntry> entry(@Valid @RequestBody EntryRequest entryRequest) {
        LedgerEntry ledgerEntry = ledgerService.entry(entryRequest);
        if(ledgerEntry == null) {
            return ApiResponse.failure().build();
        }
        return ApiResponse.success().object(ledgerEntry);
    }

    @RequestMapping(value = "/by-date/{date}", method = RequestMethod.GET)
    public ApiResponse<List<LedgerEntry>> byDate(@PathVariable("date") String date) {
        List<LedgerEntry> ledgerEntries = ledgerService.getByDate(date);
        if(ledgerEntries == null) {
            return ApiResponse.failure().build();
        }
        return ApiResponse.success().object(ledgerEntries);
    }

    @RequestMapping(value = "/by-account/{name}", method = RequestMethod.GET)
    public ApiResponse<List<LedgerEntry>> byName(@PathVariable("name") String name) {
        List<LedgerEntry> ledgerEntries = ledgerService.getByName(name);
        if(ledgerEntries == null) {
            return ApiResponse.failure().build();
        }
        return ApiResponse.success().object(ledgerEntries);
    }

    @RequestMapping(value = "/summary/by-date", method = RequestMethod.GET)
    public ApiResponse<List<SummaryEntry>> summaryByDate() {
        List<SummaryEntry> summaryEntries = ledgerService.summaryByDate();
        if(summaryEntries == null) {
            return ApiResponse.failure().build();
        }
        return ApiResponse.success().object(summaryEntries);
    }

    @RequestMapping(value = "/summary/by-name", method = RequestMethod.GET)
    public ApiResponse<List<SummaryEntry>> summaryByName() {
        List<SummaryEntry> summaryEntries = ledgerService.summaryByName();
        if(summaryEntries == null) {
            return ApiResponse.failure().build();
        }
        return ApiResponse.success().object(summaryEntries);
    }
}
