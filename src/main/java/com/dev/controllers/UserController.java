package com.dev.controllers;
import com.dev.objects.User;
import com.dev.responses.*;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.dev.utils.Errors.*;

@RestController
public class UserController {

    @Autowired
    private Utils utils;
    @Autowired
    private Persist persist;

    @RequestMapping(value = "get-all-users", method = RequestMethod.GET)
    public BasicResponse getAllUsers() {
        List<User> users = persist.getAllUsers();
        AllUsersResponse allUsersResponse = new AllUsersResponse(true, null, users);
        return allUsersResponse;
    }

    @RequestMapping(value = "getUserByToken")
    public BasicResponse getUserByToken(String token) {
        BasicResponse basicResponse;
        User user = persist.getUserByToken(token);

        if(user!=null){

            basicResponse = new UserResponse(true,null,user);
        }
        else {
            basicResponse = new BasicResponse(false,ERROR_NO_SUCH_TOKEN);
        }
        return basicResponse;
    }

    @RequestMapping(value = "sign-up")
    public BasicResponse signUp(String username, String password) {

        if (username != null) {
            if (password != null) {
                if (utils.validPasswordSize(password)) {
                    if (utils.isPasswordContainsLowerLetter(password)) {
                        if (utils.isPasswordContainsCapitalLetter(password)) {
                            if (utils.isPasswordContainsDigit(password)) {
                                if (utils.validUserNameSize(username)) {
                                    if (utils.isUsernameContainsLetter(username)) {
                                        User fromDb = persist.getUserByUsername(username);
                                        if (fromDb == null) {
                                            User newUser = new User(username, utils.createHash(username, password));
                                            persist.createUser(newUser);
                                            return new SignUpResponse(true, null, newUser);
                                        } else {
                                            return new BasicResponse(false, ERROR_USERNAME_ALREADY_EXISTS);
                                        }
                                    } else {
                                        return new BasicResponse(false, ERROR_USERNAME_WITHOUT_LETTER);
                                    }
                                } else {
                                    return new BasicResponse(false, ERROR_USERNAME_SIZE);
                                }
                            } else {
                                return new BasicResponse(false, ERROR_PASSWORD_WITHOUT_DIGIT);
                            }
                        } else {
                            return new BasicResponse(false, ERROR_PASSWORD_WITHOUT_CAPITAL_LETTER);
                        }
                    } else {
                        return new BasicResponse(false, ERROR_PASSWORD_WITHOUT_LOWER_LETTER);
                    }
                } else {
                    return new BasicResponse(false, ERROR_PASSWORD_SIZE);
                }
            } else {
                return new BasicResponse(false, ERROR_MISSING_PASSWORD);
            }
        }
        else{
            return new BasicResponse(false,ERROR_MISSING_USERNAME);
        }
    }

    @RequestMapping(value = "login")
    public BasicResponse login(String username, String password) {

        BasicResponse basicResponse;
        String token = utils.createHash(username, password);
        User fromDb = persist.getUserByUsernameAndToken(username, token);

        if (fromDb != null) {
            basicResponse = new LoginResponse(true, null, fromDb);
        }
        else {
            basicResponse = new BasicResponse(false, ERROR_WRONG_LOGIN_CREDS);
        }

        return basicResponse;
    }
}


