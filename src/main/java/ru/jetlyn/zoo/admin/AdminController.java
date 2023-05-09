package ru.jetlyn.zoo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для заполнения БД тестовыми данными
 *
 *  GET:{{baseUrl}}/zoo/diets
 */

@RestController
@RequestMapping(path = "/zoo")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/admin/start")
    public String addTestDb() {

        return adminService.addTestDb();
    }

}
