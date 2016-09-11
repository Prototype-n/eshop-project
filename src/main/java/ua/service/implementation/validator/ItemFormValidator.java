package ua.service.implementation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.form.ItemForm;
import ua.service.ItemService;

public class ItemFormValidator implements Validator{

	public final ItemService itemService;
	
	private static Pattern p = Pattern.compile("^[0-9]{1,5}$");
	
	public ItemFormValidator (ItemService itemService){
		this.itemService = itemService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ItemForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemForm form = (ItemForm) target;
		if(form.getId()==0)if(itemService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Item already exists");
		}
		Matcher m = p.matcher(form.getPrice());
		if(!m.matches()){
			errors.rejectValue("price", "", "Price format is 5 numbers");	
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can`t be empty (name)");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "Can`t be empty (category)");
	}
}
