package com.example.reg3.Service;

import com.example.reg3.dao.UserRegistrationData;
import com.example.reg3.repository.UserRegistrationDataRepository;
import com.example.reg3.requastion.UserRegistrationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //@Transactional
    public UserRegistrationDataRequest addNewUser(UserRegistrationData usersOfApp) {
        Optional<UserRegistrationData> userOptional =
                userRepository.findUserRegistrationDataByEmail(usersOfApp.getEmail());

        if (userOptional.isPresent()) {

            return new UserRegistrationDataRequest
                    (1, "email taken", usersOfApp);
        } else {
            try {
                String userPass = usersOfApp.getPassword();
                usersOfApp.setPassword
                        (new String(cipher.doFinal(userPass.getBytes())));
                usersOfApp = userRepository.save(usersOfApp);
                usersOfApp.setPassword(userPass);

                return new UserRegistrationDataRequest
                        (0, "registration was successful",
                                usersOfApp);

            } catch (IllegalBlockSizeException | BadPaddingException e) {
                throw new RuntimeException(e);
            }
        }///agbdfgadfhg
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
        }catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }

        usersOfAppOnBD.setPassword(userPass);
        usersOfAppOnBD.setId(usersOfAppOnBD.getUser().getId());
        return new UserRegistrationDataRequest(0, "authentication was successful", usersOfAppOnBD);
    }


}
