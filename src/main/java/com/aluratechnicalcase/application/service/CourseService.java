package com.aluratechnicalcase.application.service;

import com.aluratechnicalcase.application.dto.AvaliationDTO;
import com.aluratechnicalcase.application.dto.CourseCreateDTO;
import com.aluratechnicalcase.application.dto.NetPromoterScoreDTO;
import com.aluratechnicalcase.application.dto.NetPromoterScoreView;
import com.aluratechnicalcase.application.repository.AvaliationRespository;
import com.aluratechnicalcase.application.repository.CourseRepository;
import com.aluratechnicalcase.application.repository.RegisterRepository;
import com.aluratechnicalcase.application.repository.UserRepository;
import com.aluratechnicalcase.domain.entity.Avaliation;
import com.aluratechnicalcase.domain.entity.Course;
import com.aluratechnicalcase.domain.entity.Role;
import com.aluratechnicalcase.domain.entity.User;
import com.aluratechnicalcase.domain.exception.CourseAlreadyExistException;
import com.aluratechnicalcase.domain.exception.CourseNotFoundException;
import com.aluratechnicalcase.domain.exception.UnssuportedOperationException;
import com.aluratechnicalcase.domain.usecase.CourseUseCases;
import com.aluratechnicalcase.infrastructure.service.EmailSender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService implements CourseUseCases {

    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private AvaliationRespository avaliationRespository;
    private RegisterRepository registerRepository;

    public CourseService(UserRepository userRepository, CourseRepository courseRepository,
                         AvaliationRespository avaliationRespository, RegisterRepository registerRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.avaliationRespository = avaliationRespository;
        this.registerRepository = registerRepository;
    }

    @Override
    public void createCourse(CourseCreateDTO courseCreateDTO) throws UnssuportedOperationException, CourseAlreadyExistException {
        User user = this.userRepository.findUserByUsername(courseCreateDTO.instructor());
        if (!user.getRole().equals(Role.INSTRUTOR)) throw new UnssuportedOperationException("Non instructor users cannot create courses");

        Boolean courseAlreadyExist = this.courseRepository.existsByCode(courseCreateDTO.code());
        if (courseAlreadyExist) throw new CourseAlreadyExistException("This course code already exist");

        this.courseRepository.save(new Course(courseCreateDTO, user, LocalDate.now()));
    }

    @Override
    public void inactivateCourse(String code) throws CourseNotFoundException {
        Optional<Course> course = this.courseRepository.findById(code);
        if (course.isEmpty()) throw new CourseNotFoundException(
                String.format("No course with code [%s] was not found", course));
        course.get().setIsAvailable(false);
        course.get().setInactivationDate(LocalDate.now());
        this.courseRepository.save(course.get());
    }

    @Override
    public List<Course> findCourses(Boolean isAvailable, int pageNumber, int pageSize) {
        Page<Course> courses = this.courseRepository.findAllByIsAvailable(
                isAvailable, PageRequest.of(pageNumber, pageSize));
        return courses.get().collect(Collectors.toList());
    }

    @Override
    public void avaliate(AvaliationDTO avaliationDTO) throws UnssuportedOperationException {
        Course course = this.courseRepository.findByCode(avaliationDTO.code());
        User apprentice = userRepository.findUserByEmail(avaliationDTO.email());

        validateAvaliation(course, apprentice);

        Avaliation avaliation = new Avaliation(avaliationDTO, course, apprentice);
        this.avaliationRespository.save(avaliation);

        User instructor = course.getInstructor();
        EmailSender.sendEmailWhenAvaliationValueIsUnderSix(
                avaliation.getCourse(), avaliation.getApprentice(), instructor, avaliation.getMessage());
    }

    private void validateAvaliation(Course course, User apprentice) throws UnssuportedOperationException {
        if (!course.getIsAvailable()) throw new UnssuportedOperationException("This course is not available");
        if (apprentice.getRole().equals(Role.INSTRUTOR)) throw new UnssuportedOperationException("Instructor can't avaliate courses");
        Avaliation avaliationAlreadyExist = this.avaliationRespository.findByApprenticeIdAndCourseId(apprentice.getId(), course.getId());
        if (avaliationAlreadyExist != null) throw new UnssuportedOperationException("User can't avaliate a course twice");
    }

    public Course ifCourseIsNotAvailableThrowException(String code) throws UnssuportedOperationException {
        Course course = courseRepository.findByCode(code);
        if (course.getIsAvailable().equals(Boolean.FALSE))
            throw new UnssuportedOperationException("This course is not available");
        else return course;
    }

    public NetPromoterScoreView getAllNetPromoterScore() {
        NetPromoterScoreView npsView = new NetPromoterScoreView();

        List<Course> courses = registerRepository.findAllCoursesWithAtLeastFourUsers();
        List<Avaliation> allAvaliations = avaliationRespository.findAllByCourseIdIn(courses.stream().map(Course::getId).toList());

        Map<Course, List<Avaliation>> mapCoursesAndAvaliations = buildMapAvaliationsByCourse(allAvaliations, courses);

        for (Course c : mapCoursesAndAvaliations.keySet()) {
            List<Avaliation> avaliationsByCourse = mapCoursesAndAvaliations.get(c);

            NetPromoterScoreDTO nps = new NetPromoterScoreDTO(c.getName(), calculateNetPromoterScore(avaliationsByCourse));
            npsView.getNps().add(nps);
        }

        return npsView;
    }

    public Map<Course, List<Avaliation>> buildMapAvaliationsByCourse(List<Avaliation> allAvaliations, List<Course> courses) {
        Map<Course, List<Avaliation>> mapCoursesAndAvaliations = new HashMap<>();

        for (Course c : courses) {
            mapCoursesAndAvaliations.put(c, allAvaliations.stream().filter(a -> a.getCourse().equals(c)).toList());
        }
        return mapCoursesAndAvaliations;
    }

    public int calculateNetPromoterScore(List<Avaliation> avaliationsByCourse) {
        int total = avaliationsByCourse.size();
        List<Integer> promoters = avaliationsByCourse.stream().map(Avaliation::getValue).filter(value -> value >= 9).toList();
        List<Integer> detractors = avaliationsByCourse.stream().map(Avaliation::getValue).filter(value -> value < 6).toList();
        return ((promoters.size() - detractors.size()) / total) * 100;
    }

}
