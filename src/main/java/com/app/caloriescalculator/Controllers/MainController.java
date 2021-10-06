package com.app.caloriescalculator.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import com.app.caloriescalculator.Model.Products;
import com.app.caloriescalculator.Model.Temp;
import com.app.caloriescalculator.Model.User;
import com.app.caloriescalculator.Model.Calendar;
import com.app.caloriescalculator.Repository.CalendarRepository;
import com.app.caloriescalculator.Repository.ProductsRepository;
import com.app.caloriescalculator.Repository.TempRepository;
import com.app.caloriescalculator.Repository.UserRepository;
import com.app.caloriescalculator.Service.UserService;
import com.app.caloriescalculator.Service.ProductsService;
import com.app.caloriescalculator.Service.TempService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.app.caloriescalculator.Validator.ProductsDto;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TempRepository tempRepository;

    @Autowired
    private TempService tempService;

    @Autowired
    CalendarRepository calendarRepository;

    @ModelAttribute("productsDto")
    public ProductsDto productsDto() {
        return new ProductsDto();
    }

    //ADMIN HOMEPAGE
    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.setViewName("adminHome");
        return modelAndView;
    }

    //ADMIN - ALL USERS
    @RequestMapping(value = "/admin/allUsers", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        Collection <User> users = userService.findAllUsers();
        modelAndView.addObject("listOfUsers", users);
        modelAndView.setViewName("/adminUsers");
        return modelAndView;
    }

    //ADMIN - DELETE USER
    @RequestMapping(value="admin/allUsers/delete",method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("email") String email)
    {
        ModelAndView modelAndView=new ModelAndView();
        User user = userService.findByEmail(email);
        userService.deleteUser(user);
        modelAndView.setViewName("redirect:/admin/allUsers");
        return modelAndView;
    }
    //ADMIN - ALL PRODUCTS
    @GetMapping(value="/admin/allProducts")
    public ModelAndView allProducts(Model model) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("adminProducts");   
        modelAndView.addObject("products", productsRepository.findAll());
        return modelAndView;
    }
   
    //ADMIN - ADD PRODUCT
    @RequestMapping(value = "/admin/addProduct", method = RequestMethod.GET)
    public String displayAddProductForm(Model model ){
        return "adminAddProduct";
    }
    
    @PostMapping("/admin/addProduct")
    public ModelAndView addnewProduct(@ModelAttribute("productsDto") @Valid ProductsDto productsDto){
        ModelAndView modelAndView = new ModelAndView();
        productsService.add(productsDto);
        modelAndView.setViewName("redirect:/admin/allProducts");
        return modelAndView;
    }

    //ADMIN - DELETE PRODUCT
    @RequestMapping(value ="/admin/allProducts/delete", method = RequestMethod.GET )
    public ModelAndView deleteProduct(@RequestParam("name") String name)
    {
        Products product = productsService.ProductsfindByName(name);
        productsService.deleteProduct(product);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/admin/allProducts");
        return modelAndView;
    }

    //ADMIN - EDIT PRODUCT
    @RequestMapping(value = "/admin/allProducts/edit", method = RequestMethod.GET)
    public ModelAndView editProduct(@RequestParam("name") String name)
    {
        Products product = productsService.ProductsfindByName(name);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("adminEditProduct");
        return modelAndView;
    }

    @PostMapping("/admin/allProducts/edit")
    public ModelAndView editProductSave(@ModelAttribute("productsDto") @Valid ProductsDto productsDto,@RequestParam("name") String name){
        ModelAndView modelAndView = new ModelAndView();
        Products product = productsService.ProductsfindByName(name);
        productsService.editPoduct(product, productsDto);
        modelAndView.setViewName("redirect:/admin/allProducts");
        return modelAndView;
    }


    //USER HOME
    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView userHome() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (this.checkIfAdmin(auth)) {
            modelAndView.setViewName("redirect:/admin/home");
        }
        else{
            User user = userService.findByEmail(auth.getName());
            modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            modelAndView.setViewName("userHome");
        }
        return modelAndView;
    }

    //USER - CALORIES CHECK
    @RequestMapping(value="/user/home/check", method=RequestMethod.POST)
    public ModelAndView calorieCheck(@RequestParam("age") int age,@RequestParam("gender") String gender,
    @RequestParam("height") int height, @RequestParam("weight") int weight,@RequestParam("activity") String activity) {
        ModelAndView modelAndView=new ModelAndView();
        Float result= (float) 0;

        if(gender.equals("option1"))
        {
            result = (float) (result + (66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age)));
            if(activity.equals("act1")){
                result = (float) (result * 1.3);
            }
            else if(activity.equals("act2")){
                result = (float) (result * 1.5);
            }
            else if(activity.equals("act3")){
                result = (float) (result * 1.7);
            }
            else if(activity.equals("act4")){
                result = (float) (result * 2);
            }
        }
        else if(gender.equals("option2"))
        {
            result = (float) (result + (655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age)));
            if(activity.equals("act1")){
                result = (float) (result * 1.3);
            }
            else if(activity.equals("act2")){
                result = (float) (result * 1.5);
            }
            else if(activity.equals("act3")){
                result = (float) (result * 1.7);
            }
            else if(activity.equals("act4")){
                result = (float) (result * 2);
            }
        }
        modelAndView.addObject("result", result);
        modelAndView.setViewName("userCalorieCheck");
        return modelAndView;
    }

    //USER - CALORIE CHECK SAVE
    @RequestMapping(value = "/user/home/check/save",method = RequestMethod.POST)
    public ModelAndView calorieCheckSave(@RequestParam("result") Float result)
    {   
        ModelAndView modelAndView =new ModelAndView();
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        userService.updateCalorie(auth.getName(), result);
        modelAndView.setViewName("redirect:/user/home");
        return modelAndView;

    }

    //USER - FOOD DIARY
    @RequestMapping(value = "/user/food",method = RequestMethod.GET)
    public ModelAndView foodDiary()
    {
        ModelAndView modelAndView=new ModelAndView();

        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());

        Collection<Calendar> calendars=user.getCalendars();

        String date = LocalDate.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(date, formatter);
        Boolean tmp=false;

        for (Calendar var : calendars) {
            if(var.getDate().equals(now))
            {
                tmp=true;
                break;
            }
        }

        if(tmp==true)
        {
            modelAndView.setViewName("userFoodBan");
        }
        else
        {
            Collection <Products> products = productsService.findAllProducts();
            modelAndView.addObject("listOfProducts", products);

            Collection<Temp> temp = tempService.findAllTemp();

            float calories=0,fats=0,carbohydrates=0,proteins=0;
            for (Temp var : temp) {
                calories=calories+var.getCalories();
                fats=fats+var.getFats();
                carbohydrates=carbohydrates+var.getCarbohydrates();
                proteins=proteins+var.getProteins();
            }
            modelAndView.addObject("calories", calories);
            modelAndView.addObject("proteins", proteins);
            modelAndView.addObject("carbohydrates", carbohydrates);
            modelAndView.addObject("fats", fats);
            modelAndView.addObject("temp",temp);
            modelAndView.setViewName("userFood");
        }
        return modelAndView;
    }

    //USER - FOOD DIARY ADD FOOD
    @RequestMapping(value = "/user/food/add",method = RequestMethod.POST)
    public ModelAndView foodDiarySave(@RequestParam("food") String food,@RequestParam("quantity") int quantity,
    @RequestParam("add") String add)
    {
        ModelAndView modelAndView = new ModelAndView();
        Products product= productsService.ProductsfindByName(food);
        Temp temp=new Temp();
        temp.setProduct(food);
        temp.setQuantity((float) quantity);

        if(add.equals("add1"))
        {
            temp.setMeal("breakfast");
        }
        else if(add.equals("add2"))
        {
            temp.setMeal("lunch");

        }
        else if(add.equals("add3"))
        {
            temp.setMeal("dinner");
        }
        temp.setCalories((float) (quantity / 100.0 * (float) product.getCalories()));
        temp.setCarbohydrates((float) (quantity / 100.0 * (float)product.getCarbohydrates()));
        temp.setProteins((float) (quantity / 100.0 * (float)product.getProteins()));
        temp.setFats((float) (quantity / 100.0 * (float)product.getFats()));
        tempRepository.save(temp);

        modelAndView.setViewName("redirect:/user/food");
        return modelAndView;
    }

    //USER - FOOD DIARY DELETE FOOD
   @RequestMapping(value ="/user/food/delete", method = RequestMethod.GET )
    public ModelAndView foodDiaryDelete(@RequestParam("id") Integer id)
    {
        Temp temp = tempService.TempfindById(id);
        tempService.deleteTemp(temp);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect:/user/food");
        return modelAndView;
    }

    //USER - ADD TO CALENDAR
    @RequestMapping(value = "/user/food/calendarAdd", method = RequestMethod.POST)
    public ModelAndView calendarAdd()
    {
        ModelAndView modelAndView = new ModelAndView();
        Collection<Temp> temp = tempService.findAllTemp();

        float calories=0,fats=0,carbohydrates=0,proteins=0;
        for (Temp var : temp) {
            calories=calories+var.getCalories();
            fats=fats+var.getFats();
            carbohydrates=carbohydrates+var.getCarbohydrates();
            proteins=proteins+var.getProteins();
        }

        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());

        String date = LocalDate.now().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(date, formatter);

        Calendar calendar = new Calendar();
        calendar.setUsers(user);
        calendar.setDate(now);
        calendar.setCalories(calories);
        calendar.setProteins(proteins);
        calendar.setCarbohydrates(carbohydrates);
        calendar.setFats(fats);

        calendarRepository.save(calendar);

        for (Temp var : temp) {
            tempRepository.delete(var);
        }

        modelAndView.setViewName("redirect:/user/home");
        return modelAndView;
    }

    @RequestMapping(value="user/food/alreadyAdd", method = RequestMethod.GET)
    public ModelAndView foodAlreadyAdd()
    {
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("userFoodBan");
        return modelAndView;
    }


    @RequestMapping(value = "user/graph",method = RequestMethod.GET)
    public ModelAndView calendarGraph()
    {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        List <Calendar> calendar = user.getCalendars();

        List<Float> calories = new ArrayList<>();
        List<LocalDate> dates = new ArrayList<>();

        for (Calendar var : calendar){
            calories.add(var.getCalories());
            dates.add(var.getDate());
        }

        calories.subList(0, calories.size()-7).clear();
        dates.subList(0, dates.size()-7).clear();

        modelAndView.addObject("calendar",calendar);
        modelAndView.addObject("dates", dates);
        modelAndView.addObject("calories", calories);
        modelAndView.addObject("avg", user.getCalories());
        modelAndView.setViewName("userGraph");
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }


    private boolean checkIfAdmin(Authentication auth){
        return auth.getAuthorities().toString().equals("[ROLE_ADMIN]");
    }

}