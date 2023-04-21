package com.openclassrooms.payMyBuddy;

import com.openclassrooms.payMyBuddy.service.FriendServiceImpl;
import com.openclassrooms.payMyBuddy.service.CompteService;
import com.openclassrooms.payMyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	@Autowired
	private CompteService compteService;

	@Autowired
	private FriendServiceImpl friendService;

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// test User
		/*User user = new User();
		user.setAdresseEmail("user1gd@gmail.com");
		user.setMotDePasse("useruser");
		user.setUsername("user5");
		userService.save(user);
		userService.getUsers().forEach(user1 -> System.out.println(user1.getUsername()));
		// test Compte

		Compte compte = new Compte();
		compte.setUser(user);
		compte.setSolde(1000);
		compteService.addCompte(compte);
		compteService.getComptes().forEach(compte1 -> System.out.println(compte1.getSolde()));

		User user2 = new User();
		user2.setAdresseEmail("user32@gmail.com");
		user2.setMotDePasse("useruser");
		user2.setUsername("user2");
		userService.save(user2);**/


		/*compteService.envoyerArgent(1, 2, 100);
		compteService.getComptes().forEach(compte1 -> System.out.println(compte1.getSolde()));*/




		/*Friend friend = new Friend();
		friend.setUser(user);
		friend.setAdresseEmailFriend("user7@gmail.com");
		friendService.addFriend(friend);
		friendService.getFriends().forEach(friend1 -> System.out.println(friend1.getAdresseEmailFriend()));
*/
		//TODO : service transaction
		//TODO : JPA repository a la place de

	}


}
