package belaquaa.project.service.user;

import belaquaa.project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userDao;

    @Transactional
    public void addUser(User user) {
        userDao.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByCarModelAndSeries(String model, int series) {
        return userDao.findByCarModelAndSeries(model, series);
    }
}