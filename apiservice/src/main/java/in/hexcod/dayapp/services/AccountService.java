package in.hexcod.dayapp.services;

import in.hexcod.dayapp.data.AccountRepository;
import in.hexcod.dayapp.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sidhant on 17-05-2016.
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account get(String id) {
        return accountRepository.findOne(id);
    }

    public Account getByName(String name) {
        return accountRepository.findByName(name);
    }

    public Account getOrCreate(String name) {
        Account account = getByName(name);
        if(account != null)
            return account;
        account = new Account();
        account.setName(name);
        return accountRepository.insert(account);
    }
}
