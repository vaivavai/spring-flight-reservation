package com.example.FlightReservations.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import com.example.FlightReservations.models.AppUser;
import com.example.FlightReservations.utils.AppUserRole;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AppUserRepositoryTest {

  @Autowired
  private AppUserRepository underTest;

  @AfterEach
  void tearDown() {
    underTest.deleteAll();
  }

  @Test
  void should_Check_If_User_Exists_By_Email() {
    //given
    String email = "tom@gmail.com";
    AppUser user1 = new AppUser( "Tom", "tom123", email, AppUserRole.USER);
    underTest.save(user1);
    //when
    AppUser expected = underTest.findAppUserByEmail(email).get();
    //then
    assertThat(expected).isEqualTo(user1);

  }

  @Test
  void should_Return_Empty_If_User_DoesNot_Exists() {

    //given
    AppUser user1 = new AppUser(UUID.randomUUID(), "Tom", "tom123", "tom@gmail.com", AppUserRole.USER);
    underTest.save(user1);
    //when
    Optional<AppUser> expected = underTest.findAppUserByEmail(any());
    //then
    assertThat(expected).isEmpty();
  }
}