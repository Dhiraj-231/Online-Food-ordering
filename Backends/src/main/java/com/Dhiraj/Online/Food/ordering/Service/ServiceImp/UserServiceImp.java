package com.Dhiraj.Online.Food.ordering.Service.ServiceImp;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.Dhiraj.Online.Food.ordering.Config.JwtProvider;
import com.Dhiraj.Online.Food.ordering.Exceptions.UserException;
import com.Dhiraj.Online.Food.ordering.Model.PasswordResetToken;
import com.Dhiraj.Online.Food.ordering.Model.User;
import com.Dhiraj.Online.Food.ordering.Repository.PasswordResetTokenRepository;
import com.Dhiraj.Online.Food.ordering.Repository.UserRepository;
import com.Dhiraj.Online.Food.ordering.Service.userService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImp implements userService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final JavaMailSender javaMailSender;

    /**
     * Retrieves the user profile based on the provided JWT token.
     *
     * @param jwt the JSON Web Token containing user information.
     * @return the User object associated with the email extracted from the token.
     * @throws UserException if no user exists with the extracted email.
     */

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("user not exist with email " + email);
        }
        // System.out.println("email user "+user.get().getEmail());
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getPenddingRestaurantOwner() {

        return userRepository.getPenddingRestaurantOwners();
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void sendPasswordResetEmail(User user) {

        // Generate a random token (you might want to use a library for this)
        String resetToken = generateRandomToken();

        // Calculate expiry date
        Date expiryDate = calculateExpiryDate();

        // Save the token in the database
        PasswordResetToken passwordResetToken = new PasswordResetToken(resetToken, user, expiryDate);
        passwordResetTokenRepository.save(passwordResetToken);

        // Send an email containing the reset link
        sendEmail(user.getEmail(), "Password Reset",
                "Click the following link to reset your password: http://localhost:3000/account/reset-password?token="
                        + resetToken);
    }

    private void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }

    private String generateRandomToken() {
        return UUID.randomUUID().toString();
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 10);
        return cal.getTime();
    }

    @Override
    public User findUserByEmail(String username) throws UserException {

        User user = userRepository.findByEmail(username);

        if (user != null) {

            return user;
        }

        throw new UserException("user not exist with username " + username);
    }
}
