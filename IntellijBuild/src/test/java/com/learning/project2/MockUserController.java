package com.learning.project2;

import com.learning.project2.web.user.UserController;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockUserController {

    @InjectMocks
    UserController controller = new UserController();

}
