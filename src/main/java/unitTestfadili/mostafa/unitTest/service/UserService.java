package unitTestfadili.mostafa.unitTest.service;

import java.util.Map;

public interface UserService {
    String createUser(Map<String, String> userDetails);
    Map updateUser(String userId, Map userDetails);
    Map getUserDetails(String userId);
    void deleteUser(String userId);
}
