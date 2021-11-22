package com.lab3.converter;

import com.lab3.bean.StudentBean;
import com.lab3.model.Student;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.NamingException;
import java.sql.SQLException;

/**
 * Converts from student to string and otherwise back
 */
@FacesConverter(value = "studentConverter")
public class StudentConverter implements Converter {

    private final StudentBean studentService;

    public StudentConverter() throws NamingException {

        studentService = new StudentBean();
    }

    @Override
    public Student getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return studentService.getStudentsAsMap().get(Long.parseLong(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Student."));
            } catch (SQLException | NamingException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Student student = (Student) value;

        if (value != null) {
            return String.valueOf(student.getId());
        } else {
            return null;
        }
    }
}
