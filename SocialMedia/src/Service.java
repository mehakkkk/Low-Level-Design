import exception.InputValidationException;
import exception.UserNotFoundException;
import model.Post;
import model.User;
import repository.AccountRepository;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class Service {
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public Service()
    {
        userRepository = UserRepository.getInstance();
        accountRepository = AccountRepository.getInstance();
    }

    public boolean createAccount(String userName,String email,String password)
    {
        if(userName==null || userName.isEmpty() || email == null || email.isEmpty() || password == null ||password.isEmpty())
            return false;

        return accountRepository.createAccount(userName,email,password);
    }

    public User userSignIn(String profile,String password) throws InputValidationException,UserNotFoundException
    {
        if(profile == null || profile.isEmpty() || password == null || password.isEmpty())
            throw new InputValidationException("Not a valid username/email or password");
        User user = user = accountRepository.signInUser(profile,password).get();


        if(user == null)
            throw new UserNotFoundException("User not found ");
        userRepository.userCreation(user);
        return user;
    }

    public boolean sendRequest(String fromUser,String toUser) throws UserNotFoundException {
        if(fromUser == null || fromUser.isEmpty() || toUser == null || toUser.isEmpty())
            throw new UserNotFoundException("User not found ");

        return userRepository.acceptFriendRequest(fromUser,toUser);

    }

    public Post addPost(String email, Post post){
        if(email == null || email.isEmpty() || post.getUserId().isEmpty())
            throw new InputValidationException("Sign in to post");

        if(post == null || post.getUrl().isEmpty())
            throw new InputValidationException("Add something to continue posting");

        return userRepository.putUpAPost(email,post);

    }

    public List<Post> getMyPosts(String profile)
    {
        return userRepository.getUserPosts(profile);
    }
}
