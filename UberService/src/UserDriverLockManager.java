import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDriverLockManager {

    // Wrapper class to hold the lock object and its usage count
    private static class LockEntry {
        final Object lock = new Object();
        final AtomicInteger usageCount = new AtomicInteger(0);
    }

    private static final ConcurrentHashMap<String, LockEntry> userLocks = new ConcurrentHashMap<>();

    // Get or create a lock for a specific userId
    public static Object acquireLock(String userId) {
        LockEntry entry = userLocks.computeIfAbsent(userId, id -> new LockEntry());
        entry.usageCount.incrementAndGet();
        return entry.lock;
    }

    // To be called after the lock is no longer needed
    public static void releaseLock(String userId) {
        LockEntry entry = userLocks.get(userId);
        if (entry == null) return;

        int count = entry.usageCount.decrementAndGet();
        if (count <= 0) {
            userLocks.remove(userId, entry); // Remove only if entry wasn't replaced
        }
    }
}
