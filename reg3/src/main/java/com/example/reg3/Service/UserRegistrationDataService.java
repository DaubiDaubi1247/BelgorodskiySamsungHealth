package com.example.reg3.Service;

import com.example.reg3.LogBot.TelegramBot;
import com.example.reg3.dao.User;
import com.example.reg3.dao.UserRegistrationData;
import com.example.reg3.repository.UserRegistrationDataRepository;
import com.example.reg3.requastion.UserRegistrationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserRegistrationDataService {
    @Autowired
    TelegramBot bot;
    private final SecretKeySpec key;
    String algorithm;

    {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream
                (Paths.get("reg3/src/main/resources/encryptionAlgorithm.properties"))) {
            props.load(in);

            String keyOfCipher = props.getProperty("key");
            algorithm = props.getProperty("algorithm");

            key = new SecretKeySpec
                    (keyOfCipher.getBytes(), algorithm);


        } catch (IOException e) {
            throw new RuntimeException("Файл с переменными окружения не найден\n" + e.getMessage());
        }
    }

    Cipher cipher;

    {
        try {
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


    private final UserRegistrationDataRepository userRepository;


    @Autowired
    public UserRegistrationDataService(UserRegistrationDataRepository
                                               userRepository) {
        this.userRepository = userRepository;
    }

    public String cipherStrWithLocalKey(String password) {
        try {
            return new String(cipher.doFinal(password.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserRegistrationData> getUsers() {
        return userRepository.findAll();
    }


    public UserRegistrationDataRequest addNewUser(UserRegistrationData usersOfApp) {
        Optional<UserRegistrationData> userOptional =
                userRepository.findUserRegistrationDataByEmail(usersOfApp.getEmail());

        if (userOptional.isPresent()) {

            return new UserRegistrationDataRequest(1, "email занят", usersOfApp);
        } else {
            try {
                if (usersOfApp.getIsAdmin() == null) usersOfApp.setIsAdmin(false);

                usersOfApp = saveNewUser(usersOfApp);

                return new UserRegistrationDataRequest(0, "регистрация прошлва успешно", usersOfApp);
            } catch (IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private UserRegistrationData saveNewUser(UserRegistrationData userOfApp)
            throws IllegalBlockSizeException, BadPaddingException {

        String userPass = userOfApp.getPassword();
        userOfApp.setPassword(new String(cipher.doFinal(userPass.getBytes())));

        if (userOfApp.getUser() == null) userOfApp.setUser(new User());

        userOfApp = userRepository.save(userOfApp);
        userOfApp.setId(userOfApp.getUser().getId());
        userOfApp.setPassword(userPass);

        return userOfApp;
    }

    public UserRegistrationDataRequest checkUser(UserRegistrationData usersOfApp) {
        Optional<UserRegistrationData> userOptional = userRepository.findUserRegistrationDataByEmail(usersOfApp.getEmail());

        if (userOptional.isEmpty()) {
            return new UserRegistrationDataRequest(1, "user with email " +
                    usersOfApp.getEmail() + " doesn't exist", usersOfApp);
        }
        UserRegistrationData usersOfAppOnBD = userOptional.get();

        String userPass = usersOfApp.getPassword();
        try {
            String HashPass = new String(cipher.doFinal(userPass.getBytes()));
            if (!usersOfAppOnBD.getPassword().equals(HashPass)) {
                return new UserRegistrationDataRequest(2, "wrong password", usersOfApp);
            }
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        usersOfAppOnBD.setPassword(userPass);
        return new UserRegistrationDataRequest(0, "authentication was successful", usersOfAppOnBD);
    }


}
