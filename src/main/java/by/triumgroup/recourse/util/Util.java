package by.triumgroup.recourse.util;

import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Util {

    private static final PageRequest ALL_ITERMS_PAGE_REQUEST = new PageRequest(0, Integer.MAX_VALUE);

    public static boolean ifExistsWithRole(UserRepository repository, Integer id, User.Role role) {
        User user = repository.findOne(id);
        return (user != null) && (user.getRole() == role);
    }

    public static Pageable allItemsPage() {
        return ALL_ITERMS_PAGE_REQUEST;
    }

    public static <T> T ifNullDefault(T toTest, T defaultValue) {
        return toTest == null ? defaultValue : toTest;
    }

}
