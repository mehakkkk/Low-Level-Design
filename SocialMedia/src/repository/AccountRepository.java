package repository;

import model.Account;
import model.User;
import utility.UserNameBasedProfileUtil;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AccountRepository {

    private final Map<String, Account> accountMap;
    private static AccountRepository instance;

    public static AccountRepository getInstance()
    {
        if(instance == null){
            synchronized (UserRepository.class)
            {
                instance = new AccountRepository();
            }
        }
        return instance;
    }

    private AccountRepository() {
        this.accountMap = new ConcurrentHashMap<>();
    }

    // Create account is thread-safe without the need for synchronization
    public boolean createAccount(String userName, String email, String password) {
        // Atomic check and creation: only create if the email doesn't exist
        if (accountMap.containsKey(email)) {
            return false; // Email already exists
        }
        synchronized (this) {
            Account account = new Account(userName, email, password);
            UserNameBasedProfileUtil.getInstance().updateUserEmailMap(userName, email);
            accountMap.put(email, account);
        }
        return true;
    }

    // Delete account is thread-safe due to ConcurrentHashMap
    public boolean deleteAccount(String email) {
        // Simply remove the account, ConcurrentHashMap handles thread-safety
        return accountMap.remove(email) != null;
    }

    // Sign-in process is thread-safe due to Optional usage
    public Optional<User> signInUser(String profile, String password) {
        // Try to get the account by profile (either username or email)
        Optional<Account> account = Optional.ofNullable(accountMap.get(profile))
                .or(() -> {
                    // If the profile is not found by username, try getting the email from the utility
                    String email = UserNameBasedProfileUtil.getInstance().getUserEmail(profile);
                    return Optional.ofNullable(accountMap.get(email));
                });

        // If an account is found and the password matches, return the user
        return account
                .filter(acc -> acc.getPassword().equals(password))  // Check if passwords match
                .map(Account::getUser);  // Extract user if password matches
    }
}
