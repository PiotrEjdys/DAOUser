package pl.coderslab;

import pl.coderslab.entity.UserDao;

public class MainDao {
    public static void main(String[] args) {
       User user1 = new User();

       user1.setUserName("Piotr");
       user1.setEmail("piotr.ejdys95@gmail.com");
       user1.setPassword("kuleczki");

       UserDao userDao1 = new UserDao();
//       userDao1.create(user1);

//        User userToUpdate = userDao1.read(1);
//        userToUpdate.setUserName("Patryk");
//        userToUpdate.setEmail("patryk.ejdys95@gmail.com");
//        userToUpdate.setPassword("kuleczkiii");
//        userDao1.update(userToUpdate);
//        System.out.println(userDao1.read(1));

       User user2 =new User();

       user2.setUserName("Wojtek");
       user2.setEmail("wojtek.malak90@gmail.com");
       user2.setPassword("eluwina");

        UserDao userDao2 = new UserDao();

//        userDao2.create(user2);
//        userDao2.delete(2);
        System.out.println(userDao2.read(2));



    }
}
