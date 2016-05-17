package in.hexcod.dayapp.data;

import in.hexcod.dayapp.models.LedgerEntry;

import java.util.List;

/**
 * Created by sidhant on 17-05-2016.
 */
public interface LedgerEntryRepository {
    List<LedgerEntry> findByAccountId(String accountId);
    List<LedgerEntry> findByDate(String date);
}
