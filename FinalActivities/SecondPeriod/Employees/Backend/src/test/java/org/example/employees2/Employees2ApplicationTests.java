package org.example.employees2;

import org.example.employees2.controllers.employeesController;
import org.example.employees2.models.dao.EmployeeEntityDAO;
import org.example.employees2.models.entities.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest (classes = Employees2Application.class)
class Employees2ApplicationTests {

    @Autowired
    @Mock
    private EmployeeEntityDAO employeeEntityDAO;

    @InjectMocks
    private employeesController employeesController;

    @Test
    void findAllEmployees() {
        //Arrange
        List<EmployeeEntity> employees = new ArrayList<>();
        employees.add(new EmployeeEntity(1000, "Nombre1", "Puesto1, 10"));
        employees.add(new EmployeeEntity(1001, "Nombre2", "Puesto2, 20"));

        when(employeeEntityDAO.findAll()).thenReturn(employees);

        //Petición
        List<EmployeeEntity> result = (List<EmployeeEntity>) employeesController.findAllUsers();

        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void findEmployeeById() {
        int id = 1000;
        EmployeeEntity employee = new EmployeeEntity(id, "Nombre1", "Puesto1, 10");
        when(employeeEntityDAO.findById(id)).thenReturn(Optional.empty());

        //Request
        ResponseEntity<EmployeeEntity> result = (ResponseEntity<EmployeeEntity>) employeesController.findUserById(id);

        //Assert
        assertEquals(200, result.getStatusCode().value());
        assertEquals(employee, result.getBody());
    }

    @Test
    void findEmployeeByIdNotFound() {
        int id = 1099;

        when(employeeEntityDAO.findById(id)).thenReturn(Optional.empty());

        //Request
        ResponseEntity<EmployeeEntity> result = (ResponseEntity<EmployeeEntity>) employeesController.findUserById(id);

        assertEquals(404, result.getStatusCode().value());
    }

    /*@Test
    void saveEmployee() {
        //Arrange
        EmployeeEntity employee = new EmployeeEntity(1000, "Nombre1", "Puesto1, 10");
        when(employeeEntityDAO.save(any(EmployeeEntity.class))).thenReturn(employee);
        // Act
        EmployeeEntity result =
                employeesController.saveUser(employee);
        // Assert
        assertEquals(employee, result);

    }

    @Test
    void updateEmployee() {
        // Arrange
        int id = 1000;
        EmployeeEntity nuevoEmpleado = new EmployeeEntity(id, "NuevoNombre", "NuevoPuesto", 10);
        EmployeeEntity employeeExistente = new EmployeeEntity(id, "NombreAntiguo", "PuestoAntiguo", 20);
        when(employeeEntityDAO.findById(id)).thenReturn(Optional.of(employeeExistente));
        when(employeeEntityDAO.save(any(EmployeeEntity.class))).thenReturn(employeeExistente);
        // Act
        ResponseEntity<?> result =
                employeesController.updateUser(nuevoEmpleado, id);
        // Assert
        assertEquals(200, result.getStatusCode().value());
        assertEquals("Updated", result.getBody());
        assertEquals("NuevoNombre", employeeExistente.getEname();
    }
    @Test
    void updateEmployeeNotExists() {
        // Arrange
        int id = 1099;
        EmployeeEntity newEmployee = new EmployeeEntity(1000,
                "NuevoNombre", "NuevoPuesto", 10);
        when(employeeEntityDAO.findById(id)).thenReturn(Optional.empty());
        // Act
        ResponseEntity<?> result =
               employeesController.updateUser(newEmployee, id);
        // Assert
        assertEquals(404, result.getStatusCode().value());
        }

        @Test
        void deleteExistingEmployee() {
            // Arrange
            int id = 1000;
            EmployeeEntity existingEmployee = new
            EmployeeEntity(id,"NombreAntiguo", "PuestoAntiguo", 10);
            when(employeeEntityDAO.findById(id)).thenReturn(Optional.of(existingEmployee
        ));
        // Act
        ResponseEntity<?> result =
        employeesController.deleteUser(id);
        // Assert
        assertEquals(200, result.getStatusCode().value());
        assertEquals("Borrado", result.getBody());
        verify(employeeEntityDAO, times(1)).deleteById(id);

        }*/
        @Test
        void deleteNonExistingEmployee() {
        // Arrange
        int id = 1099;
        when(employeeEntityDAO.findById(id)).thenReturn(Optional.empty());
        // Act
        ResponseEntity<?> result =
        employeesController.deleteUser(id);

        assertEquals(404, result.getStatusCode().value());
    }
}
