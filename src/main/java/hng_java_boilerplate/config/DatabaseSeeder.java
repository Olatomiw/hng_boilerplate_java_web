package hng_java_boilerplate.config;

import hng_java_boilerplate.organisation.entity.Organisation;
import hng_java_boilerplate.organisation.repository.OrganisationRepository;
import hng_java_boilerplate.product.entity.Product;
import hng_java_boilerplate.product.repository.ProductRepository;
import hng_java_boilerplate.profile.entity.Profile;
import hng_java_boilerplate.user.entity.User;
import hng_java_boilerplate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final OrganisationRepository organisationRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (isDatabaseEmpty()) {
            seedDatabase();
        } else {
            System.out.println("Database is not empty, skipping seeding.");
        }
    }

    @Transactional(readOnly = true)
    public boolean isDatabaseEmpty() {
        return userRepository.count() == 0 &&
                organisationRepository.count() == 0 &&
                productRepository.count() == 0;
    }

    @Transactional
    public void seedDatabase() {
        // Seed users
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setName("John Doe");
        user1.setEmail("johndoe@example.com");

        Profile profile1 = new Profile();
        profile1.setId(UUID.randomUUID().toString());
        profile1.setFirstName("John");
        profile1.setLastName("Doe");
        profile1.setPhone("1234567890");
        profile1.setAvatarUrl("http://example.com/avatar.jpg");
        user1.setProfile(profile1);
        profile1.setUser(user1);

        User user2 = new User();
        user2.setId(UUID.randomUUID().toString());
        user2.setName("Jane Smith");
        user2.setEmail("janesmith@example.com");

        Profile profile2 = new Profile();
        profile2.setId(UUID.randomUUID().toString());
        profile2.setFirstName("Jane");
        profile2.setLastName("Smith");
        profile2.setAvatarUrl("http://example.com/avatar2.jpg");
        user2.setProfile(profile2);
        profile2.setUser(user2);

        User user4 = new User();
        user4.setId(UUID.randomUUID().toString());
        user4.setName("Alice Johnson");
        user4.setEmail("alicejohnson@example.com");

        Profile profile4 = new Profile();
        profile4.setId(UUID.randomUUID().toString());
        profile4.setFirstName("Alice");
        profile4.setLastName("Johnson");
        profile4.setAvatarUrl("http://example.com/avatar4.jpg");
        user4.setProfile(profile4);
        profile4.setUser(user4);

        User user5 = new User();
        user5.setId(UUID.randomUUID().toString());
        user5.setName("Bob Brown");
        user5.setEmail("bobbrown@example.com");

        Profile profile5 = new Profile();
        profile5.setId(UUID.randomUUID().toString());
        profile5.setFirstName("Bob");
        profile5.setLastName("Brown");
        profile5.setAvatarUrl("http://example.com/avatar5.jpg");
        user5.setProfile(profile5);
        profile5.setUser(user5);


        userRepository.saveAll(Arrays.asList(user1, user2, user4, user5));

        // Seed organisations
        Organisation org1 = new Organisation();
        org1.setId(UUID.randomUUID().toString());
        org1.setName("Some Org");
        org1.setDescription("Some Org Description");

        Organisation org2 = new Organisation();
        org2.setId(UUID.randomUUID().toString());
        org2.setName("Some Other Org");
        org2.setDescription("Some Other Org Description");

        Organisation org3 = new Organisation();
        org3.setId(UUID.randomUUID().toString());
        org3.setName("Yet Another Org");
        org3.setDescription("Yet Another Org Description");

        organisationRepository.saveAll(Arrays.asList(org1, org2, org3));

        // Assign organisations to users
        user1.setOrganisations(Arrays.asList(org1, org2));
        user2.setOrganisations(Arrays.asList(org1, org2, org3));
        userRepository.saveAll(Arrays.asList(user1, user2));

        // Seed products
        Product product1 = new Product();
        product1.setId(UUID.randomUUID().toString());
        product1.setName("Product One");
        product1.setDescription("Description for Product One");
        product1.setUser(user1);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID().toString());
        product2.setName("Product Two");
        product2.setDescription("Description for Product Two");
        product2.setUser(user1);

        Product product3 = new Product();
        product3.setId(UUID.randomUUID().toString());
        product3.setName("Product Three");
        product3.setDescription("Description for Product Three");
        product3.setUser(user2);

        Product product4 = new Product();
        product4.setId(UUID.randomUUID().toString());
        product4.setName("Product Four");
        product4.setDescription("Description for Product Four");
        product4.setUser(user2);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));
    }
}
