package unitTestfadili.mostafa.unitTest.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import unitTestfadili.mostafa.unitTest.io.UsersDatabase;
import unitTestfadili.mostafa.unitTest.io.UsersDatabaseMapImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {
	
	private UsersDatabase usersDatabase;
	private UserService userService;
	private String createdUserId = "";

    @BeforeAll
    void setup() {
        // Create & initialize database
    	usersDatabase = new UsersDatabaseMapImpl();
    	usersDatabase.init();
    	
    	userService = new UserServiceImpl(usersDatabase);
    }

    @AfterAll
    void cleanup() {
        // Close connection
    	usersDatabase.close();
        // Delete database
    	usersDatabase = null;
    	
    	userService = null;
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {

    	//given
    	Map<String, String> user = new HashMap<>();
    	user.put("firstName", "mostafa");
    	user.put("lastName", "fadili");
    	
    	
    	//when
    	createdUserId = userService.createUser(user);
    	
    	//then
    	assertNotNull(createdUserId);
    }


    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {

    	//given
    	Map<String, String> newUser = new HashMap<>();
    	newUser.put("firstName", "bilal");
    	newUser.put("lastName", "fadili");
    	
    	//when
    	Map<String, String> updatedUser = userService.updateUser(createdUserId, newUser);
    	
    	//then
    	assertEquals(newUser.get("firstName"), updatedUser.get("firstName"),
    			"returned value of user's firstName is incorrect");
    	
    	assertEquals(newUser.get("lastName"), updatedUser.get("lastName"),
    			"returned value of user's lastName is incorrect");
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {

    	
    	//when
    	Map<String, String> userDetails = userService.getUserDetails(createdUserId);
    	
    	//then
    	assertNotNull(userDetails, "user shouldn't be null");
    	assertEquals(createdUserId, userDetails.get("userId"),
    			"returned user contains incorect user id");
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {

    	//when
    	userService.deleteUser(createdUserId);
    	
    	//then
    	assertNull(userService.getUserDetails(createdUserId),
    			"user should not be found");
    }

}
