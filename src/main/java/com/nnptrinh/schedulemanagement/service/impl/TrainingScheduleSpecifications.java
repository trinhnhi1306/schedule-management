package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import com.nnptrinh.schedulemanagement.model.entity.User;
import com.nnptrinh.schedulemanagement.model.enums.EClazzType;
import com.nnptrinh.schedulemanagement.model.enums.ETrainingType;
import com.nnptrinh.schedulemanagement.model.model.UserModel;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

public final class TrainingScheduleSpecifications {
    public static Specification<TrainingSchedule> sessionNameContains(String expression) {
        return (root, query, builder) -> builder.like(root.get("sessionName"), contains(expression));
    }

    public static Specification<TrainingSchedule> trainingTypeContains(ETrainingType trainingType) {
        return (root, query, builder) -> builder.equal(root.get("trainingType"), trainingType);
    }

    public static Specification<TrainingSchedule> clazzTypeContains(EClazzType clazzType) {
        return (root, query, builder) -> builder.equal(root.get("clazzType"), clazzType);
    }

    public static Specification<TrainingSchedule> isClazz(Clazz clazz) {
        return (root, query, builder) -> builder.equal(root.join("clazz").get("id"), clazz.getId());
    }

    public static Specification<TrainingSchedule> timeBetween(LocalDateTime from, LocalDateTime to) {
        if (to == null)
            return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("startTime"), from);
        if (from == null)
            return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("startTime"), to);

        return (root, query, builder) -> builder.between(root.get("startTime"), from, to);
    }

    public static Specification<TrainingSchedule> trainersIn(List<UserModel> trainers) {
        return (root, query, builder) -> {
            Join<TrainingSchedule, User> trainer = root.join("trainers", JoinType.LEFT);
            List<String> list = trainers.stream().map(UserModel::getUsername).toList();
            query.distinct(true);
            return trainer.get("username").in(list);
        };
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }
}
