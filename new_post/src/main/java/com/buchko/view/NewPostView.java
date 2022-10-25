package com.buchko.view;

import com.buchko.controller.AccountController;
import com.buchko.controller.CityController;
import com.buchko.controller.CourierController;
import com.buchko.controller.DepartmentController;
import com.buchko.controller.OperatorController;
import com.buchko.controller.OrderController;
import com.buchko.controller.RegionController;
import com.buchko.controller.impl.ClientControllerImpl;
import com.buchko.domain.Account;
import com.buchko.domain.City;
import com.buchko.domain.Client;
import com.buchko.domain.Courier;
import com.buchko.domain.Department;
import com.buchko.domain.Operator;
import com.buchko.domain.Order;
import com.buchko.domain.Region;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewPostView {

    @Autowired
    ClientControllerImpl clientController;
    @Autowired
    AccountController accountController;
    @Autowired
    RegionController regionController;
    @Autowired
    CityController cityController;
    @Autowired
    DepartmentController departmentController;
    @Autowired
    OperatorController operatorController;
    @Autowired
    CourierController courierController;
    @Autowired
    OrderController orderController;

    private final Scanner inputMenu = new Scanner(System.in);
    private final Client emptyClient = new Client(null, null, null, null);
    private final Account emptyAccount = new Account(null, null, null, null);
    private final Region emptyRegion = new Region(null);
    private final City emptyCity = new City(null, null, null);
    private final Department emptyDepartment = new Department(null, null, null, null);
    private final Operator emptyOperator = new Operator(null, null, null, null, null);
    private final Courier emptyCourier = new Courier(null, null, null, null, null);
    private final Order emptyOrder = new Order(null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null);

    private final LinkedHashMap<String, String> menu;
    private final LinkedHashMap<String, Printable> actionsMenu;

    public NewPostView() {
        menu = new LinkedHashMap<>();
        actionsMenu = new LinkedHashMap<>();

        menu.put("1", "1: Clients");
        menu.put("2", "2: Accounts");
        menu.put("3", "3: Regions");
        menu.put("4", "4: Cities");
        menu.put("5", "5: Departments");
        menu.put("6", "6: Operators");
        menu.put("7", "7: Couriers");
        menu.put("8", "8: Orders");

        menu.put("Q", "Q: Quit");

        menu.put("11", "11: Get all clients");
        menu.put("12", "12: Create client");
        menu.put("13", "13: Update client");
        menu.put("14", "14: Delete client");
        menu.put("15", "15: Get client by id");

        actionsMenu.put("11", this::findAllClients);
        actionsMenu.put("12", this::createClient);
        actionsMenu.put("13", this::updateClient);
        actionsMenu.put("14", this::deleteClient);
        actionsMenu.put("15", this::findClientById);

        menu.put("21", "21: Get all accounts");
        menu.put("22", "22: Create account");
        menu.put("23", "23: Update account");
        menu.put("24", "24: Delete account");
        menu.put("25", "25: Get account by client id");

        actionsMenu.put("21", this::findAllAccounts);
        actionsMenu.put("22", this::createAccount);
        actionsMenu.put("23", this::updateAccount);
        actionsMenu.put("24", this::deleteAccount);
        actionsMenu.put("25", this::findAccountByClientId);

        menu.put("31", "31: Get all regions");
        menu.put("32", "32: Create region");
        menu.put("33", "33: Update region");
        menu.put("34", "34: Delete region");
        menu.put("35", "35: Get region");

        actionsMenu.put("31", this::findAllRegions);
        actionsMenu.put("32", this::createRegion);
        actionsMenu.put("33", this::updateRegion);
        actionsMenu.put("34", this::deleteRegion);
        actionsMenu.put("35", this::findRegion);

        menu.put("41", "41: Get all cities");
        menu.put("42", "42: Create city");
        menu.put("43", "43: Update city");
        menu.put("44", "44: Delete city");
        menu.put("45", "45: Get city by id");
        menu.put("46", "46: Get cities by region name");

        actionsMenu.put("41", this::findAllCities);
        actionsMenu.put("42", this::createCity);
        actionsMenu.put("43", this::updateCity);
        actionsMenu.put("44", this::deleteCity);
        actionsMenu.put("45", this::findCityById);
        actionsMenu.put("46", this::findCitiesByRegionName);

        menu.put("51", "51: Get all departments");
        menu.put("52", "52: Create department");
        menu.put("53", "53: Update department");
        menu.put("54", "54: Delete department");
        menu.put("55", "55: Get department by id");
        menu.put("56", "56: Get departments by city id");
        menu.put("57", "57: Get departments by number");

        actionsMenu.put("51", this::findAllDepartments);
        actionsMenu.put("52", this::createDepartment);
        actionsMenu.put("53", this::updateDepartment);
        actionsMenu.put("54", this::deleteDepartment);
        actionsMenu.put("55", this::findDepartmentById);
        actionsMenu.put("56", this::findDepartmentByCityId);
        actionsMenu.put("57", this::findDepartmentByNumber);

        menu.put("61", "61: Get all operators");
        menu.put("62", "62: Create operator");
        menu.put("63", "63: Update operator");
        menu.put("64", "64: Delete operator");
        menu.put("65", "65: Get operator by id");
        menu.put("66", "66: Get operators by department id");

        actionsMenu.put("61", this::findAllOperators);
        actionsMenu.put("62", this::createOperator);
        actionsMenu.put("63", this::updateOperator);
        actionsMenu.put("64", this::deleteOperator);
        actionsMenu.put("65", this::findOperatorById);
        actionsMenu.put("66", this::findOperatorByDepartmentId);

        menu.put("71", "71: Get all couriers");
        menu.put("72", "72: Create courier");
        menu.put("73", "73: Update courier");
        menu.put("74", "74: Delete courier");
        menu.put("75", "75: Get courier by id");
        menu.put("76", "76: Get couriers by department id");

        actionsMenu.put("71", this::findAllCouriers);
        actionsMenu.put("72", this::createCourier);
        actionsMenu.put("73", this::updateCourier);
        actionsMenu.put("74", this::deleteCourier);
        actionsMenu.put("75", this::findCourierById);
        actionsMenu.put("76", this::findCourierByDepartmentId);

        menu.put("81", "81: Get all orders");
        menu.put("82", "82: Create order");
        menu.put("83", "83: Update order");
        menu.put("84", "84: Delete order");
        menu.put("85", "85: Get order by id");
        menu.put("86", "86: Get orders by sender id");
        menu.put("87", "87: Get orders by receiver id");
        menu.put("88", "88: Get orders by receiver department id");

        actionsMenu.put("81", this::findAllOrders);
        actionsMenu.put("82", this::createOrder);
        actionsMenu.put("83", this::updateOrder);
        actionsMenu.put("84", this::deleteOrder);
        actionsMenu.put("85", this::findOrderById);
        actionsMenu.put("86", this::findOrdersBySenderClientId);
        actionsMenu.put("87", this::findOrdersByReceiverClientId);
        actionsMenu.put("88", this::findOrdersByReceiverDepartmentId);

    }

    private void findAllClients() {
        String leftAlignFormat = "| %-4d | %-10s | %-10s | %-20s |%n";
        System.out.println("\nClients:");
        System.out.format("+------+------------+------------+----------------------+%n");
        System.out.format("| ID   |  Name      | Surname    | Phone number         |%n");
        System.out.format("+------+------------+------------+----------------------+%n");
        List<Client> clients = clientController.findAll();
        for (Client client : clients) {
            System.out.format(leftAlignFormat, client.getId(), client.getName(),
                client.getSurname(), client.getPhoneNumber());
        }
        System.out.format("+------+------------+------------+----------------------+%n");
    }

    private void createClient() {
        System.out.println("Input 'name': ");
        String name = inputMenu.nextLine();
        System.out.println("Input 'surname': ");
        String surname = inputMenu.nextLine();
        System.out.println("Input 'phoneNumber': ");
        String phoneNumber = (inputMenu.nextLine());
        Client client = new Client(null, name, surname, phoneNumber);

        int count = clientController.create(client);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateClient() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        System.out.println("Input new 'name': ");
        String name = inputMenu.nextLine();
        System.out.println("Input new 'surname': ");
        String surname = inputMenu.nextLine();
        System.out.println("Input new 'phoneNumber': ");
        String phoneNumber = (inputMenu.nextLine());
        Client client = new Client(null, name, surname, phoneNumber);

        int count = clientController.update(id, client);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteClient() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        int count = clientController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findClientById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        Optional<Client> book = clientController.findById(id);
        System.out.println(book.orElse(emptyClient));
    }

    // account table

    private void findAllAccounts() {
        String leftAlignFormat = "| %-8d | %-15s | %-15s | %-29s |%n";
        System.out.println("\nAccounts:");
        System.out.format(
            "+----------+-----------------+-----------------+-------------------------------+%n");
        System.out.format(
            "| ClientID |  Username       | Password        | Email address                 |%n");
        System.out.format(
            "+----------+-----------------+-----------------+-------------------------------+%n");
        List<Account> accounts = accountController.findAll();
        for (Account account : accounts) {
            System.out.format(leftAlignFormat, account.getClientId(), account.getUsername(),
                account.getPassword(), account.getEmailAddress());
        }
        System.out.format(
            "+----------+-----------------+-----------------+-------------------------------+%n");
    }

    private void createAccount() {
        System.out.println("Input 'client id': ");
        Integer clientId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'username': ");
        String username = inputMenu.nextLine();
        System.out.println("Input 'password': ");
        String password = (inputMenu.nextLine());
        System.out.println("Input 'emailAddress': ");
        String emailAddress = (inputMenu.nextLine());
        Account account = new Account(clientId, username, password, emailAddress);

        int count = accountController.create(account);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateAccount() {
        System.out.println("Input 'client id': ");
        Integer clientId = Integer.valueOf((inputMenu.nextLine()));

        System.out.println("Input 'username': ");
        String username = inputMenu.nextLine();
        System.out.println("Input 'password': ");
        String password = (inputMenu.nextLine());
        System.out.println("Input 'emailAddress': ");
        String emailAddress = (inputMenu.nextLine());
        Account account = new Account(clientId, username, password, emailAddress);

        int count = accountController.update(clientId, account);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteAccount() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        int count = accountController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAccountByClientId() {
        System.out.println("Input 'client id': ");
        Integer clientId = Integer.valueOf((inputMenu.nextLine()));

        Optional<Account> account = accountController.findById(clientId);
        System.out.println(account.orElse(emptyAccount));
    }

    // region table

    private void findAllRegions() {
        String leftAlignFormat = "| %-15s |%n";
        System.out.println("\nRegions:");
        System.out.format("+-----------------+%n");
        System.out.format("| Region          |%n");
        System.out.format("+-----------------+%n");
        List<Region> regions = regionController.findAll();
        for (Region region : regions) {
            System.out.format(leftAlignFormat, region.getRegion());
        }
        System.out.format("+-----------------+%n");
    }

    private void createRegion() {
        System.out.println("Input 'region': ");
        String regionName = inputMenu.nextLine();

        Region region = new Region(regionName);

        int count = regionController.create(region);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateRegion() {
        System.out.println("Input 'region': ");
        String regionName = inputMenu.nextLine();

        Region region = new Region(regionName);

        int count = regionController.update(regionName, region);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteRegion() {
        System.out.println("Input 'regionName': ");
        String regionName = inputMenu.nextLine();

        int count = regionController.delete(regionName);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findRegion() {
        System.out.println("Input 'region': ");
        String regionName = (inputMenu.nextLine());

        Optional<Region> region = regionController.findById(regionName);
        System.out.println(region.orElse(emptyRegion));
    }

    // city table

    private void findAllCities() {
        String leftAlignFormat = "| %-4d | %-15s | %-11s |%n";
        System.out.println("\nAccounts:");
        System.out.format("+------+-----------------+-------------+%n");
        System.out.format("| ID   |  Region name    | City        |%n");
        System.out.format("+------+-----------------+-------------+%n");
        List<City> cities = cityController.findAll();
        for (City city : cities) {
            System.out.format(leftAlignFormat, city.getId(), city.getRegionName(), city.getCity());
        }
        System.out.format("+------+-----------------+-------------+%n");
    }

    private void createCity() {
        System.out.println("Input 'regionName': ");
        String regionName = inputMenu.nextLine();
        System.out.println("Input 'city': ");
        String cityName = inputMenu.nextLine();

        City city = new City(null, regionName, cityName);

        int count = cityController.create(city);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCity() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));
        System.out.println("Input 'regionName': ");
        String regionName = inputMenu.nextLine();
        System.out.println("Input 'city': ");
        String cityName = inputMenu.nextLine();

        City city = new City(id, regionName, cityName);

        int count = cityController.update(id, city);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteCity() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        int count = cityController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findCityById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        Optional<City> city = cityController.findById(id);
        System.out.println(city.orElse(emptyCity));
    }

    private void findCitiesByRegionName() {
        System.out.println("Input 'region name': ");
        String regionName = inputMenu.nextLine();

        String leftAlignFormat = "| %-4d | %-15s | %-11s |%n";
        System.out.println("\nCities:");
        System.out.format("+------+-----------------+-------------+%n");
        System.out.format("| ID   |  Region name    | City        |%n");
        System.out.format("+------+-----------------+-------------+%n");

        List<City> cities = cityController.findCitiesByRegionName(regionName);

        for (City city : cities) {
            System.out.format(leftAlignFormat, city.getId(), city.getRegionName(), city.getCity());
        }
        System.out.format("+------+-----------------+-------------+%n");


    }

    // department table

    private void findAllDepartments() {
        String leftAlignFormat = "| %-4d | %-9d | %-20s | %-6d |%n";
        System.out.println("\nDepartments:");
        System.out.format("+------+-----------+----------------------+--------+%n");
        System.out.format("| ID   |  City ID  | Address              | Number |%n");
        System.out.format("+------+-----------+----------------------+--------+%n");
        List<Department> departments = departmentController.findAll();
        for (Department department : departments) {
            System.out.format(leftAlignFormat, department.getId(), department.getCityId(),
                department.getAddress(), department.getNumber());
        }
        System.out.format("+------+-----------+----------------------+--------+%n");
    }

    private void createDepartment() {
        System.out.println("Input 'city id': ");
        Integer cityId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'address': ");
        String address = inputMenu.nextLine();
        System.out.println("Input 'number': ");
        Integer number = Integer.valueOf(inputMenu.nextLine());

        Department department = new Department(null, cityId, address, number);

        int count = departmentController.create(department);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateDepartment() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));
        System.out.println("Input 'city id': ");
        Integer cityId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'address': ");
        String address = inputMenu.nextLine();
        System.out.println("Input 'number': ");
        Integer number = Integer.valueOf(inputMenu.nextLine());

        Department department = new Department(id, cityId, address, number);

        int count = departmentController.update(id, department);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteDepartment() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        int count = departmentController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findDepartmentById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        Optional<Department> department = departmentController.findById(id);
        System.out.println(department.orElse(emptyDepartment));
    }

    private void findDepartmentByNumber() {
        System.out.println("Input 'number': ");
        Integer number = Integer.valueOf(inputMenu.nextLine());

        String leftAlignFormat = "| %-4d | %-9d | %-20s | %-10d |%n";
        System.out.println("\nDepartments:");
        System.out.format("+------+-----------+----------------------+--------+%n");
        System.out.format("| ID   |  City ID  | Address              | Number |%n");
        System.out.format("+------+-----------+----------------------+--------+%n");
        List<Department> departments = departmentController.findByNumber(number);
        for (Department department : departments) {
            System.out.format(leftAlignFormat, department.getId(), department.getCityId(),
                department.getAddress(), department.getNumber());
        }
        System.out.format("+------+-----------+----------------------+--------+%n");
    }

    private void findDepartmentByCityId() {
        System.out.println("Input 'city id': ");
        Integer cityId = Integer.valueOf(inputMenu.nextLine());

        String leftAlignFormat = "| %-4d | %-9d | %-20s | %-6d |%n";
        System.out.println("\nDepartments:");
        System.out.format("+------+-----------+----------------------+--------+%n");
        System.out.format("| ID   |  City ID  | Address              | Number |%n");
        System.out.format("+------+-----------+----------------------+--------+%n");
        List<Department> departments = departmentController.findByCityId(cityId);
        for (Department department : departments) {
            System.out.format(leftAlignFormat, department.getId(), department.getCityId(),
                department.getAddress(), department.getNumber());
        }
        System.out.format("+------+-----------+----------------------+--------+%n");
    }

    // operator table

    private void findAllOperators() {
        String leftAlignFormat = "| %-4d | %-13d | %-10s | %-10s | %-20s |%n";
        System.out.println("\nOperators:");
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
        System.out.format(
            "| ID   | Department ID | Name       | Surname    | Phone number         |%n");
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
        List<Operator> operators = operatorController.findAll();
        for (Operator operator : operators) {
            System.out.format(leftAlignFormat, operator.getId(), operator.getDepartmentId(),
                operator.getName(), operator.getSurname(), operator.getPhoneNumber());
        }
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
    }

    private void createOperator() {
        System.out.println("Input 'department id': ");
        Integer departmentId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'name': ");
        String name = inputMenu.nextLine();
        System.out.println("Input 'surname': ");
        String surname = inputMenu.nextLine();
        System.out.println("Input 'phoneNumber': ");
        String phoneNumber = inputMenu.nextLine();

        Operator operator = new Operator(null, departmentId, name, surname, phoneNumber);

        int count = operatorController.create(operator);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateOperator() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));
        System.out.println("Input 'department id': ");
        Integer departmentId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'name': ");
        String name = inputMenu.nextLine();
        System.out.println("Input 'surname': ");
        String surname = inputMenu.nextLine();
        System.out.println("Input 'phoneNumber': ");
        String phoneNumber = inputMenu.nextLine();

        Operator operator = new Operator(id, departmentId, name, surname, phoneNumber);

        int count = operatorController.update(id, operator);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteOperator() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        int count = operatorController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findOperatorById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        Optional<Operator> operator = operatorController.findById(id);
        System.out.println(operator.orElse(emptyOperator));
    }

    private void findOperatorByDepartmentId() {
        System.out.println("Input 'department id': ");
        Integer departmentId = Integer.valueOf(inputMenu.nextLine());

        String leftAlignFormat = "| %-4d | %-13d | %-10s | %-10s | %-20s |%n";
        System.out.println("\nOperators:");
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
        System.out.format(
            "| ID   | Department ID | Name       | Surname    | Phone number         |%n");
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
        List<Operator> operators = operatorController.findByDepartmentId(departmentId);
        for (Operator operator : operators) {
            System.out.format(leftAlignFormat, operator.getId(), operator.getDepartmentId(),
                operator.getName(), operator.getSurname(), operator.getPhoneNumber());
        }
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
    }

    // courier table

    private void findAllCouriers() {
        String leftAlignFormat = "| %-4d | %-13d | %-10s | %-10s | %-20s |%n";
        System.out.println("\nCouriers:");
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
        System.out.format(
            "| ID   | Department ID | Name       | Surname    | Phone number         |%n");
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
        List<Courier> couriers = courierController.findAll();
        for (Courier courier : couriers) {
            System.out.format(leftAlignFormat, courier.getId(), courier.getDepartmentId(),
                courier.getName(), courier.getSurname(), courier.getPhoneNumber());
        }
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
    }

    private void createCourier() {
        System.out.println("Input 'city id': ");
        Integer cityId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'name': ");
        String name = inputMenu.nextLine();
        System.out.println("Input 'surname': ");
        String surname = inputMenu.nextLine();
        System.out.println("Input 'phoneNumber': ");
        String phoneNumber = (inputMenu.nextLine());

        Courier courier = new Courier(null, cityId, name, surname, phoneNumber);

        int count = courierController.create(courier);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCourier() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));
        System.out.println("Input 'department id': ");
        Integer departmentId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'name': ");
        String name = inputMenu.nextLine();
        System.out.println("Input 'surname': ");
        String surname = inputMenu.nextLine();
        System.out.println("Input 'phoneNumber': ");
        String phoneNumber = inputMenu.nextLine();

        Courier courier = new Courier(id, departmentId, name, surname, phoneNumber);

        int count = courierController.update(id, courier);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteCourier() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        int count = courierController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findCourierById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        Optional<Courier> courier = courierController.findById(id);
        System.out.println(courier.orElse(emptyCourier));
    }

    private void findCourierByDepartmentId() {
        System.out.println("Input 'department id': ");
        Integer departmentId = Integer.valueOf(inputMenu.nextLine());

        String leftAlignFormat = "| %-4d | %-13d | %-10s | %-10s | %-20s |%n";
        System.out.println("\nCouriers:");
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
        System.out.format(
            "| ID   | Department ID | Name       | Surname    | Phone number         |%n");
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
        List<Courier> couriers = courierController.findByDepartmentId(departmentId);
        for (Courier courier : couriers) {
            System.out.format(leftAlignFormat, courier.getId(), courier.getDepartmentId(),
                courier.getName(), courier.getSurname(), courier.getPhoneNumber());
        }
        System.out.format(
            "+------+---------------+------------+------------+----------------------+%n");
    }

    // order table

    private void findAllOrders() {
        String leftAlignFormat = "| %-4d | %-14d | %-16d | %-18d | %-20d | %-16d | %-18d | %-27d | %-16d | %-11.2f | %-13.2f | %-10.2f |%n";
        System.out.println("\nOrders:");
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
        System.out.format(
            "| ID   | SenderClientID | ReceiverClientID | SenderDepartmentID | ReceiverDepartmentID | SenderOperatorID | ReceiverOperatorID | CourierBetweenDepartmentsID | CourierOnSpotID  | ParcelPrice | DeliveryPrice | TotalPrice |%n");
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
        List<Order> orders = orderController.findAll();
        for (Order order : orders) {
            System.out.format(leftAlignFormat, order.getId(), order.getSenderClientId(),
                order.getReceiverClientId(), order.getSenderDepartmentId(),
                order.getReceiverDepartmentId(), order.getSenderOperatorId(),
                order.getReceiverOperatorId(), order.getCourierBetweenDepartmentsId(),
                order.getCourierOnSpotId(), order.getParcelPrice(), order.getDeliveryPrice(),
                order.getTotalPrice());

        }
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
    }

    private void createOrder() {
        System.out.println("Input 'senderClientId': ");
        Integer senderClientId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'receiverClientId': ");
        Integer receiverClientId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'senderDepartmentId': ");
        Integer senderDepartmentId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'receiverDepartmentId': ");
        Integer receiverDepartmentId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'senderClientId': ");
        Integer senderOperatorId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'receiverClientId': ");
        Integer receiverOperatorId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'courierBetweenDepartmentsId': ");
        Integer courierBetweenDepartmentsId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'courierOnSpotId': ");
        Integer courierOnSpotId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'plannedSendingDatetime': ");
        LocalDateTime plannedSendingDatetime = LocalDateTime.parse(inputMenu.nextLine());
        System.out.println("Input 'plannedReceivingDatetime': ");
        LocalDateTime plannedReceivingDatetime = LocalDateTime.parse(inputMenu.nextLine());
        System.out.println("Input 'actualSendingDatetime': ");
        LocalDateTime actualSendingDatetime = LocalDateTime.parse(inputMenu.nextLine());
        System.out.println("Input 'actualReceivingDatetime': ");
        LocalDateTime actualReceivingDatetime = LocalDateTime.parse(inputMenu.nextLine());
        System.out.println("Input 'parcelPrice': ");
        Double parcelPrice = Double.valueOf(inputMenu.nextLine());
        System.out.println("Input 'deliveryPrice': ");
        Double deliveryPrice = Double.valueOf(inputMenu.nextLine());

        Order order = new Order(null, senderClientId, receiverClientId, senderDepartmentId,
            receiverDepartmentId, senderOperatorId, receiverOperatorId, courierBetweenDepartmentsId,
            courierOnSpotId, plannedSendingDatetime, plannedReceivingDatetime,
            actualSendingDatetime, actualReceivingDatetime, parcelPrice, deliveryPrice, null);

        int count = orderController.create(order);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateOrder() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));
        System.out.println("Input 'senderClientId': ");
        Integer senderClientId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'receiverClientId': ");
        Integer receiverClientId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'senderDepartmentId': ");
        Integer senderDepartmentId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'receiverDepartmentId': ");
        Integer receiverDepartmentId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'senderClientId': ");
        Integer senderOperatorId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'receiverClientId': ");
        Integer receiverOperatorId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'courierBetweenDepartmentsId': ");
        Integer courierBetweenDepartmentsId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'courierOnSpotId': ");
        Integer courierOnSpotId = Integer.valueOf(inputMenu.nextLine());
        System.out.println("Input 'plannedSendingDatetime': ");
        LocalDateTime plannedSendingDatetime = LocalDateTime.parse(inputMenu.nextLine());
        System.out.println("Input 'plannedReceivingDatetime': ");
        LocalDateTime plannedReceivingDatetime = LocalDateTime.parse(inputMenu.nextLine());
        System.out.println("Input 'actualSendingDatetime': ");
        LocalDateTime actualSendingDatetime = LocalDateTime.parse(inputMenu.nextLine());
        System.out.println("Input 'actualReceivingDatetime': ");
        LocalDateTime actualReceivingDatetime = LocalDateTime.parse(inputMenu.nextLine());
        System.out.println("Input 'parcelPrice': ");
        Double parcelPrice = Double.valueOf(inputMenu.nextLine());
        System.out.println("Input 'deliveryPrice': ");
        Double deliveryPrice = Double.valueOf(inputMenu.nextLine());

        Order order = new Order(id, senderClientId, receiverClientId, senderDepartmentId,
            receiverDepartmentId, senderOperatorId, receiverOperatorId, courierBetweenDepartmentsId,
            courierOnSpotId, plannedSendingDatetime, plannedReceivingDatetime,
            actualSendingDatetime, actualReceivingDatetime, parcelPrice, deliveryPrice, null);

        int count = orderController.update(id, order);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteOrder() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        int count = orderController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findOrderById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        Optional<Order> order = orderController.findById(id);
        System.out.println(order.orElse(emptyOrder));
    }

    private void findOrdersBySenderClientId() {
        System.out.println("Input 'sender client ID': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        String leftAlignFormat = "| %-4d | %-14d | %-16d | %-18d | %-20d | %-16d | %-18d | %-27d | %-16d | %-11.2f | %-13.2f | %-10.2f |%n";
        System.out.println("\nOrders:");
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
        System.out.format(
            "| ID   | SenderClientID | ReceiverClientID | SenderDepartmentID | ReceiverDepartmentID | SenderOperatorID | ReceiverOperatorID | CourierBetweenDepartmentsID | CourierOnSpotID  | ParcelPrice | DeliveryPrice | TotalPrice |%n");
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");

        List<Order> orders = orderController.findBySenderClientId(id);

        for (Order order : orders) {
            System.out.format(leftAlignFormat, order.getId(), order.getSenderClientId(),
                order.getReceiverClientId(), order.getSenderDepartmentId(),
                order.getReceiverDepartmentId(), order.getSenderOperatorId(),
                order.getReceiverOperatorId(), order.getCourierBetweenDepartmentsId(),
                order.getCourierOnSpotId(), order.getParcelPrice(), order.getDeliveryPrice(),
                order.getTotalPrice());

        }
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
    }

    private void findOrdersByReceiverClientId() {
        System.out.println("Input 'receiver client ID': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        String leftAlignFormat = "| %-4d | %-14d | %-16d | %-18d | %-20d | %-16d | %-18d | %-27d | %-16d | %-11.2f | %-13.2f | %-10.2f |%n";
        System.out.println("\nOrders:");
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
        System.out.format(
            "| ID   | SenderClientID | ReceiverClientID | SenderDepartmentID | ReceiverDepartmentID | SenderOperatorID | ReceiverOperatorID | CourierBetweenDepartmentsID | CourierOnSpotID  | ParcelPrice | DeliveryPrice | TotalPrice |%n");
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");

        List<Order> orders = orderController.findByReceiverClientId(id);

        for (Order order : orders) {
            System.out.format(leftAlignFormat, order.getId(), order.getSenderClientId(),
                order.getReceiverClientId(), order.getSenderDepartmentId(),
                order.getReceiverDepartmentId(), order.getSenderOperatorId(),
                order.getReceiverOperatorId(), order.getCourierBetweenDepartmentsId(),
                order.getCourierOnSpotId(), order.getParcelPrice(), order.getDeliveryPrice(),
                order.getTotalPrice());

        }
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
    }

    private void findOrdersByReceiverDepartmentId() {
        System.out.println("Input 'receiver department ID': ");
        Integer id = Integer.valueOf((inputMenu.nextLine()));

        String leftAlignFormat = "| %-4d | %-14d | %-16d | %-18d | %-20d | %-16d | %-18d | %-27d | %-16d | %-11.2f | %-13.2f | %-10.2f |%n";
        System.out.println("\nOrders:");
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
        System.out.format(
            "| ID   | SenderClientID | ReceiverClientID | SenderDepartmentID | ReceiverDepartmentID | SenderOperatorID | ReceiverOperatorID | CourierBetweenDepartmentsID | CourierOnSpotID  | ParcelPrice | DeliveryPrice | TotalPrice |%n");
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");

        List<Order> orders = orderController.findByReceiverDepartmentId(id);

        for (Order order : orders) {
            System.out.format(leftAlignFormat, order.getId(), order.getSenderClientId(),
                order.getReceiverClientId(), order.getSenderDepartmentId(),
                order.getReceiverDepartmentId(), order.getSenderOperatorId(),
                order.getReceiverOperatorId(), order.getCourierBetweenDepartmentsId(),
                order.getCourierOnSpotId(), order.getParcelPrice(), order.getDeliveryPrice(),
                order.getTotalPrice());

        }
        System.out.format(
            "+------+----------------+------------------+-------------------+-----------------------+------------------+--------------------+-----------------------------+------------------+-------------+---------------+------------+%n");
    }


    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet()) {
            if (key.length() == 1) {
                System.out.println(menu.get(key));
            }
        }
    }


    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet()) {
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) {
                System.out.println(menu.get(key));
            }
        }
    }

    public void show() {

        String input;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            input = inputMenu.nextLine().toUpperCase();

            if (input.matches("^\\d")) {
                outputSubMenu(input);
                System.out.println("Please, select menu point.");
                input = inputMenu.nextLine().toUpperCase();
            }

            try {
                actionsMenu.get(input).print();
            } catch (Exception e) {
                System.out.println(e);
            }

        } while (!input.equals("Q"));

    }

}
