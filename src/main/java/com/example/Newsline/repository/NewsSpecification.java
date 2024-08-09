package com.example.Newsline.repository;

import com.example.Newsline.model.News;
import com.example.Newsline.web.model.filters.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter newsFilter){
        return Specification.where(byAuthorName(newsFilter.getAuthor()))
                .and(byCategoty(newsFilter.getCategory()))
                .and(byCreateAtBefore(newsFilter.getCreatedBefore()))
                .and(byUpdateAtBefore(newsFilter.getUpdatedBefore()));
    }

    static Specification<News> byAuthorName(String authorName) {
        return ((root, query, criteriaBuilder) -> {
            if (authorName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("newsAuthor").get("fullName"), authorName);
        });
    }

    static Specification<News> byCategoty(String category) {
        return ((root, query, criteriaBuilder) -> {
            if (category == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("category"), category);
        });
    }

    static Specification<News> byCreateAtBefore(Instant createdBefore) {
        return ((root, query, criteriaBuilder) -> {
            if (createdBefore == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdBefore);
        });
    }

    static Specification<News> byUpdateAtBefore(Instant updatedBefore) {
        return ((root, query, criteriaBuilder) -> {
            if (updatedBefore == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("updatedAt"), updatedBefore);
        });
    }
}
