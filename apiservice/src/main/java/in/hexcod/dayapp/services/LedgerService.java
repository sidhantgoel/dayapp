package in.hexcod.dayapp.services;

import in.hexcod.dayapp.data.LedgerEntryRepository;
import in.hexcod.dayapp.models.Account;
import in.hexcod.dayapp.models.EntryRequest;
import in.hexcod.dayapp.models.LedgerEntry;
import in.hexcod.dayapp.models.SummaryEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

//imports as static
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.List;

/**
 * Created by sidhant on 17-05-2016.
 */
@Service
public class LedgerService {

    @Autowired
    AccountService accountService;

    @Autowired
    LedgerEntryRepository ledgerEntryRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public void delete(String id) {
        ledgerEntryRepository.delete(id);
    }

    public LedgerEntry entry(EntryRequest entryRequest) {
        Account account = accountService.getOrCreate(entryRequest.getName());
        if(account == null) {
            return null;
        }
        LedgerEntry ledgerEntry = new LedgerEntry();
        ledgerEntry.setName(entryRequest.getName());
        ledgerEntry.setDate(entryRequest.getDate());
        ledgerEntry.setGive(entryRequest.getGive());
        ledgerEntry.setTake(entryRequest.getTake());
        return ledgerEntryRepository.insert(ledgerEntry);
    }

    public List<LedgerEntry> getByDate(String date) {
        return ledgerEntryRepository.findByDate(date);
    }

    public List<LedgerEntry> getByName(String name) {
        return ledgerEntryRepository.findByName(name);
    }

    public void deleteByName(String name) {
        ledgerEntryRepository.deleteByName(name);
    }

    public List<SummaryEntry> summaryByDate() {
        Aggregation agg = newAggregation(
                group("date").sum("give").as("give").sum("take").as("take"),
                project("give", "take").and("_id").as("nameOrDate"),
                sort(Sort.Direction.DESC, "nameOrDate")
        );

        //Convert the aggregation result into a List
        AggregationResults<SummaryEntry> groupResults
                = mongoTemplate.aggregate(agg, LedgerEntry.class, SummaryEntry.class);
        List<SummaryEntry> result = groupResults.getMappedResults();

        return result;
    }

    public List<SummaryEntry> summaryByName() {
        Aggregation agg = newAggregation(
                group("name").sum("give").as("give").sum("take").as("take"),
                project("give", "take").and("_id").as("nameOrDate"),
                sort(Sort.Direction.DESC, "nameOrDate")
        );

        //Convert the aggregation result into a List
        AggregationResults<SummaryEntry> groupResults
                = mongoTemplate.aggregate(agg, LedgerEntry.class, SummaryEntry.class);
        List<SummaryEntry> result = groupResults.getMappedResults();

        return result;
    }
}
