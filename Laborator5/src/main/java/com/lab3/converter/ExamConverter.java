package com.lab3.converter;

import com.lab3.bean.ExamBean;
import com.lab3.model.Exam;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.NamingException;

@FacesConverter(value = "examConverter")
public class ExamConverter implements Converter {

    private final ExamBean examService;

    public ExamConverter() throws NamingException {

        examService = new ExamBean();
    }

    @Override
    public Exam getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return examService.getExamsAsMap().get(Long.parseLong(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Exam."));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Exam exam = (Exam) value;

        if (value != null) {
            return "Hello";
        } else {
            return null;
        }
    }
}
