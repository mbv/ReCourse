package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GUEST')")
    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



}
