package com.example.RestAPICrudAppSeriesEvents;

import com.example.RestAPICrudAppSeriesEvents.model.User;
import com.example.RestAPICrudAppSeriesEvents.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserTestUnitaire {

    @Autowired
    public UserService userservice;

    @Test
    public void testCreateUser()
    {
        User user1 = new User();
        user1.setEmail("test.serie@gmail.com");
        user1.setPassword("test");
        user1.setFirstname("testSerie");
        user1.setLastname("testSerie");
        User savedUser = userservice.saveUser(user1);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testdisplayUsers()
    {
        int id = 2;
        List<User> listeUser = userservice.findAllUsers();
        assertThat(listeUser);
        for(int i=0 ; i<listeUser.size();i++)
        {
            System.out.println(listeUser.get(i).getFirstname()+" "+listeUser.get(i).getLastname());
        }
    }

    @Test
    public void testUpdateUser()
    {
        User user = userservice.findUserById(1L);
        user.setFirstname("first name updated !");
        user.setLastname("lastname updated !");
        user.setEmail("email updated !");
        user.setPassword("password updated !");
        assertThat(userservice.saveUser(user));
    }

    @Test
    public void testDeleteUser()
    {
        Long id = 2L;
        User user = userservice.findUserById(id);
        userservice.deleteUserById(id);
        System.out.println("Utilisateur "+user.getFirstname()+" "+user.getLastname()+" supprimé !");
        Optional<User> optionalUser = Optional.ofNullable(userservice.findUserById(id));
        assertThat(optionalUser).isNotPresent();
    }
}
