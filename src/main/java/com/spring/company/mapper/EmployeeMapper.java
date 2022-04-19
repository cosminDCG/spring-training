package com.spring.company.mapper;

import com.spring.company.dto.EmployeeDto;
import com.spring.company.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "projectId", source = "project.uuid")
    @Mapping(target = "password", expression = "java(null)")
    EmployeeDto mapToDto(Employee employee);

    @Mapping(target = "project.uuid", source = "projectId")
    Employee mapToEntity(EmployeeDto employeeDto);
}
