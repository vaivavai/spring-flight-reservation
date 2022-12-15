package com.example.FlightReservations.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import com.example.FlightReservations.exceptions.alreadyExists.AppUserAlreadyExistsException;
import com.example.FlightReservations.exceptions.notFound.AppUserNotFoundException;
import com.example.FlightReservations.models.AppUser;
import com.example.FlightReservations.repositories.AppUserRepository;
import com.example.FlightReservations.utils.AppUserRole;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

  @Mock
  private AppUserRepository appUserRepository;
  private AppUserService underTest;

  @BeforeEach
  void setUp() {
    underTest = new AppUserService(appUserRepository);
  }

  @Test
  void should_Get_All_Users() {
    //when
    underTest.getUsers();
    //then
    verify(appUserRepository).findAll(); //we know that repository works, our test is faster
  }

  @Test
  void should_Get_User_By_Id_If_Exists() {
    //given
    AppUser user1 = new AppUser("Tom", "tom123", "tom123@gmail.com", AppUserRole.USER);

    UUID uuid = user1.getId();
    given(appUserRepository.findById(uuid)).willReturn(Optional.of(user1));
    //when
    //then
    underTest.getAppUsersById(uuid);
    verify(appUserRepository).findById(uuid);
  }

  @Test
  void should_Throw_If_User_Does_Not_Exist() {

    UUID uuid = UUID.randomUUID();
    given(appUserRepository.findById(uuid)).willReturn(Optional.empty());

    assertThatThrownBy(() -> underTest.getAppUsersById(uuid)).isInstanceOf(
        AppUserNotFoundException.class);
  }

  @Test
  void addNewAppUser() {
    //given
    AppUser user1 = new AppUser("Tom", "tom123", "tom123@gmail.com", AppUserRole.USER);
    //when
    underTest.addNewAppUser(user1);
    ArgumentCaptor<AppUser> appUserArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);

    verify(appUserRepository).save(appUserArgumentCaptor.capture());
    //we verify that repository was invoked with the save method, and we also captured the
    // value to make sure it's the same that was invoked with the underTest

    AppUser appUserArgumentCaptorValue = appUserArgumentCaptor.getValue();

    assertThat(appUserArgumentCaptorValue).isEqualTo(user1);
    // we capture the student that is given to the repository
  }

  @Test
  void should_Throw_Exception_When_EmailIsTaken() {
    //given
    String email = "tom123@gmail.com";
    AppUser user1 = new AppUser("Tom", "tom123", email, AppUserRole.USER);

    given(appUserRepository.findAppUserByEmail(email)).willReturn(Optional.of(user1));

    assertThatThrownBy(() -> underTest.addNewAppUser(user1)).isInstanceOf(
        AppUserAlreadyExistsException.class);

    verify(appUserRepository, never()).save(user1);

  }

  @Test
  void should_Delete_AppUser_When_User_Exists() {
    //given
    UUID uuid = UUID.randomUUID();
    given(appUserRepository.existsById(uuid)).willReturn(true);
    //when
    underTest.deleteAppUser(uuid);
//then
    verify(appUserRepository).deleteById(uuid);
  }

  @Test
  void should_Throw_Exception_When_User_Does_Not_Exist() {
    //given
    UUID uuid = UUID.randomUUID();
    given(appUserRepository.existsById(uuid)).willReturn(false);
    //when
    //then
    assertThatThrownBy(() -> underTest.deleteAppUser(uuid)).isInstanceOf(
        AppUserNotFoundException.class).hasMessage("User not found");

    verify(appUserRepository, never()).deleteById(any());
  }

  @Test
  @Disabled
  void updateAppUser() {

  }
}