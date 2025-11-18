package com.texnoera.specification;

import com.texnoera.dao.entity.User;
import com.texnoera.model.UserFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {

    private final UserFilter userFilter;

    @Override
    public Predicate toPredicate(Root<User> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Long id = userFilter.getId();
        if (!ObjectUtils.isEmpty(id)) {
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }

        String name = userFilter.getName();
        if (!ObjectUtils.isEmpty(name)) {
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }

        String surname = userFilter.getSurname();
        if (!ObjectUtils.isEmpty(surname)) {
            predicates.add(criteriaBuilder.equal(root.get("surname"), surname));
        }

        Integer age = userFilter.getAge();
        if (!ObjectUtils.isEmpty(age)) {
            predicates.add(criteriaBuilder.equal(root.get("age"), age));
        }

        LocalDate from = userFilter.getFrom();
        if (!ObjectUtils.isEmpty(from)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), from.atTime(23, 59, 59)));
        }

        LocalDate to = userFilter.getTo();
        if (!ObjectUtils.isEmpty(to)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), to.atTime(23, 59, 59)));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
