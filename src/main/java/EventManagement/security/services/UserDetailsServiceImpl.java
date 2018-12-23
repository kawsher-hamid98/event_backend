package EventManagement.security.services;

import EventManagement.model.User;
import EventManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    UserRepository userRepository;

	@Autowired
	JavaMailSender javaMailSender;


	public void sendMail(User user) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("eventmanageteam@gmail.com");
		helper.setTo(user.getEmail());
		helper.setSubject("Event Management");
		helper.setText("Here is your attachment.");

		FileSystemResource file = new FileSystemResource("E:\\ticket.pdf");
		helper.addAttachment(file.getFilename(), file);

		javaMailSender.send(mimeMessage);
	}


	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		return UserPrinciple.build(user);
	}
}