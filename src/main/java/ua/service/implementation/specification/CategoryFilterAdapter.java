package ua.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Category;
import ua.form.filter.CategoryFilterForm;

public class CategoryFilterAdapter implements Specification<Category>{

	private String search = "";

	public CategoryFilterAdapter(CategoryFilterForm form) {
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(
				Root<Category> root, 
				CriteriaQuery<?> query, 
				CriteriaBuilder cb) {
		if (query.getResultType() != Long.class && query.getResultType() != long.class) {
//			root.fetch("ingredient");
//			root.fetch("measuringSystem");
//			query.distinct(true);
		}
		
		return cb.like(cb.upper(root.get("name")), search.toUpperCase()+"%");
	}	
}