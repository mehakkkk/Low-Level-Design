public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = UserRepository.getInstance();
    }

    // Helper method to validate userId
    private void validateUserId(String userId) throws InvalidUserData, UserNotFoundException {
        if (userId == null || userId.isEmpty()) {
            throw new InvalidUserData("User ID cannot be null or empty");
        }
        if (!userRepository.userExists(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    // Helper method to validate user data
    private void validateUserData(String name, String number, String email) throws InvalidUserData {
        if (name == null || name.isEmpty() || number == null || number.isEmpty() || number.length() != 10 || email == null || email.isEmpty()) {
            throw new InvalidUserData("User creation failed. Invalid input.");
        }
    }

    // Add user
    public String addUser(String name, String number, String email) throws InvalidUserData {
        validateUserData(name, number, email);
        User user = new User(name, number, email);
        userRepository.addUser(user);
        return user.getId().toString();
    }

    // Update user location
    public boolean updateUserLocation(String userId, Location location) throws InvalidUserData, UserNotFoundException {
        if (location == null) {
            throw new InvalidUserData("Location cannot be null");
        }

        // Validate userId
        validateUserId(userId);

        boolean isUpdated;
        synchronized (UserDriverLockManager.acquireLock(userId)) {
            User user = userRepository.getUser(userId);
            user.setLocation(location);
            isUpdated = userRepository.updateUser(user);
            if (!isUpdated) {
                throw new InvalidUserData("Failed to update user location in the database.");
            }
        }
        UserDriverLockManager.releaseLock(userId);
        return true;
    }

    // Delete user
    public boolean deleteUser(String userId) throws InvalidUserData, UserNotFoundException {
        // Validate userId
        validateUserId(userId);

        boolean isRemoved;
        synchronized (UserDriverLockManager.acquireLock(userId)) {
            isRemoved = userRepository.deleteUser(userId);
            if (!isRemoved) {
                throw new InvalidUserData("Failed to remove user.");
            }
        }
        UserDriverLockManager.releaseLock(userId);
        return true;
    }

    // Get user
    public User getUser(String userId) throws InvalidUserData, UserNotFoundException {
        // Validate userId
        validateUserId(userId);
        return userRepository.getUser(userId);
    }

    // Get user location
    public Location getUserLocation(String userId) throws InvalidUserData, UserNotFoundException {
        // Validate userId
        validateUserId(userId);
        return getUser(userId).getLocation();
    }
}
